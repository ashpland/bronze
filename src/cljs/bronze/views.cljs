(ns bronze.views
  (:require
   [bronze.subs :as subs]
   [clojure.string :as string]
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   ))

(defn EditableField
  [id field-key]
  (let [*value (re-frame/subscribe [::subs/node-key id field-key])]
    (fn []
      [:input {:value @*value
               :on-change
               (fn [e]
                 (let [value (-> e .-target .-value)
                       new-value (if (string/blank? value)
                                   nil
                                   value)]
                   (re-frame/dispatch [::subs/update-node-key
                                       id field-key new-value
                                       ])))}])))

(defn EditableTextArea
  [id field-key]
  (let [*value (re-frame/subscribe [::subs/node-key id field-key])]
    (fn []
      [:textarea {:value @*value
               :on-change
               (fn [e]
                 (let [value (-> e .-target .-value)
                       new-value (if (string/blank? value)
                                   nil
                                   value)]
                   (re-frame/dispatch [::subs/update-node-key
                                       id field-key new-value
                                       ])))}])))

(defn Checkbox
  [checked]
  (when checked
    [:span.check (case checked
                   "x"  "☑"
                   "X"  "☑"
                   "t"  "☑"
                   "true"  "☑"
                   "☐")]))

(defn NodeShort
  [node-id]
  (let [*node (re-frame/subscribe [::subs/node node-id])]
    (fn []
      (let [{:keys [name value checked]} @*node]
        [:span
         [Checkbox checked]
         (when value [:span.value value])
         (when name [:span name])]))))

(defn EditCard
  [node-id end-edit-handler]
  (let [*node (re-frame/subscribe [::subs/node node-id])
        *editing-child (reagent/atom nil)]
    (fn []
      (let [{:keys [nodes name value checked]} @*node
            short-name (string/join " " (remove nil? [(when checked "☐")
                                                      value
                                                      name]))]
        [:div.edit-card
         {:on-mouseOver #(.stopPropagation %)}
         [:span.action-buttons
          [:input {:type "button" :value "✓"
                   :on-click end-edit-handler}]]
         [:h1 [NodeShort node-id]]
         (for [field-key [:checked :value :name :label :link]]
           ^{:key field-key}
           [:div (str field-key) [EditableField node-id field-key]])
         [:div (str :desc) [EditableTextArea node-id :desc]]
         [:input {:type "button" :value (str "Remove \"" short-name "\"")
                  :on-click (fn [] (re-frame/dispatch [::subs/remove-node node-id])
                              (end-edit-handler))}]

         (if @*editing-child
           [EditCard @*editing-child #(reset! *editing-child nil)]
           [:table
            [:thead
             [:tr [:td [:h3 "Children"]]]]
            [:tbody
             (for [id nodes]
               ^{:key id}
               [:tr
                [:td [NodeShort id]]
                [:td
                 [:input {:type "button" :value "X"
                          :on-click (fn [] (re-frame/dispatch [::subs/remove-node id]))}]]
                [:td
                 [:input {:type "Button" :value "✎"
                          :on-click #(reset! *editing-child id)}]]])]
            [:tfoot
             [:tr
              [:td [:input {:type "button" :value (str "Add child to \"" short-name "\"")
                            :on-click #(re-frame/dispatch [::subs/add-node node-id]) }]]]]])]))))



(defn NodeCard
    [pane node-id]
  (let [*node (re-frame/subscribe [::subs/node node-id])
        *editing? (reagent/atom false)
        *collapse? (reagent/atom true)
        *show-actions? (reagent/atom false)]
    (fn []
      (let [{:keys [name desc value checked label link nodes]} @*node]
        (if @*editing?
          [EditCard node-id (fn []
                              (reset! *show-actions? false)
                              (reset! *editing? false))]

          (if (not (or name label desc link (not-empty nodes)))
            [:div.small-card
             (reset! *show-actions? false)
             (when value [:span.small-value value])
             [Checkbox checked]]

            [:div.card
             {:on-mouseOver (fn [e]
                              (.stopPropagation e)
                              (reset! *show-actions? true))
              :on-mouseOut  #(reset! *show-actions? false)}

             (when @*show-actions?
               [:span.action-buttons
                [:input {:type "button" :value "✎"
                         :on-click  #(swap! *editing? not)}]
                [:input {:type "button" :value "➚"
                         :on-click #(re-frame/dispatch [::subs/new-pane pane node-id])}]])

             [:h1
              (when (not-empty nodes)
                {:style {:cursor "pointer"}
                 :on-click #(swap! *collapse? not)})
              (when (not-empty nodes)
                [:span.collapse-arrow (if @*collapse? "▷" "▽")])
              [Checkbox checked]
              (when value [:span.value value])
              (when name [:span name])]
             (when label [:small label])
             (when desc [:p.desc desc])
             (when link [:a {:href link
                             :target "_blank"}
                         link])

             (when (and (not @*collapse?)
                        (not-empty nodes))
               [:div.card-list
                (for [id nodes]
                  ^{:key id}
                  [NodeCard pane id])])
             ]))))))

(defn get-pane-ids
  [panes {id :id next-id :next-pane}]
  (let [next-pane (get panes next-id)]
    (if (nil? next-pane)
      [id]
      (flatten (conj [id] (get-pane-ids panes next-pane))))))

(defn main-panel []
  (let [*panes (re-frame/subscribe [::subs/panes])]
    (fn []
      (let [panes @*panes
            pane-ids (get-pane-ids panes (get panes (:root panes)))
            ordered-panes (->> pane-ids
                               (map #(get panes %)))]
        [:div#main
         (for [{:keys [id node-id]} ordered-panes]
           ^{:key id}
           [:div.column
            [:input {:type "button" :value "Remove Pane"
                         :on-click #(re-frame/dispatch [::subs/remove-pane id])}]
            [NodeCard id node-id]])]))))
