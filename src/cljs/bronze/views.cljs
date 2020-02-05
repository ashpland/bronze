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
  [node-id end-edit-handler *show-actions?]
  (let [*node (re-frame/subscribe [::subs/node node-id])
        *editing-child (reagent/atom nil)]
    (fn []
      (let [{:keys [nodes]} @*node]
        [:div.edit-card
         {:on-mouseOver #(.stopPropagation %)}
         [:span.action-buttons
          [:input {:type "button" :value "✎"
                   :on-click end-edit-handler}]]
         [:h1 [NodeShort node-id]]
         (for [field-key [:value :name :label :desc :link :checked]]
           ^{:key field-key}
           [:div (str field-key) [EditableField node-id field-key]])
         [:input {:type "button" :value "Remove node"
                  :on-click (fn [] (re-frame/dispatch [::subs/remove-node node-id])
                              (reset! *show-actions? false)
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
                [:td.action-buttons
                 [:input {:type "button" :value "X"
                          :on-click (fn [] (re-frame/dispatch [::subs/remove-node id]))}]]
                [:td.action-buttons
                 [:input {:type "Button" :value "✎"
                          :on-click #(reset! *editing-child id)}]]])]
            [:tfoot
             [:tr
              [:td [:input {:type "button" :value "Add child"
                            :on-click #(re-frame/dispatch [::subs/add-node node-id]) }]]]]])]))))



(defn NodeCard
  [node-id]
  (let [*node (re-frame/subscribe [::subs/node node-id])
        *editing? (reagent/atom false)
        *collapse? (reagent/atom false)
        *show-actions? (reagent/atom false)]
    (fn []
      (let [{:keys [name desc value checked label link nodes]} @*node]
        (if @*editing?
          [EditCard node-id #(reset! *editing? false) *show-actions?]


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
                [:input {:type "button" :value "⤡" }]
                [:input {:type "button" :value "➚" }]])

             [:h1
              [Checkbox checked]
              (when value [:span.value value])
              (when name [:span
                          (when (not-empty nodes)
                            {:style {:cursor "pointer"}
                             :on-click #(swap! *collapse? not)})
                          name])]

             (when label [:small label])
             (when desc [:p.desc desc])
             (when link [:a {:href link
                             :target "_blank"}
                         link])

             (when (and (not @*collapse?)
                        (not-empty nodes))
               [:<>
                [:hr]
                [:div.card-list
                 (for [id nodes]
                   ^{:key id}
                   [NodeCard id])]])
             ]))))))

(defn main-panel []
  (let [*head (re-frame/subscribe [::subs/head])]
    [:div#main
     [:div.column [NodeCard @*head]]
     [:div.column [NodeCard 2]]
     [:div.column [NodeCard 11]]
     ]))
