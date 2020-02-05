(ns bronze.views
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [bronze.subs :as subs]
   ))

(defn EditableField
  [id field-key]
  (let [*value (re-frame/subscribe [::subs/node-key id field-key])]
    (fn []
      [:input {:value @*value
               :on-change #(re-frame/dispatch [::subs/update-node-key
                                               id field-key
                                               (-> % .-target .-value)])}])))

(defn NodeCard
  [node-id]
  (let [*node (re-frame/subscribe [::subs/node node-id])
        *editing? (reagent/atom false)
        *collapse? (reagent/atom false)
        *show-actions? (reagent/atom false)]
    (fn []
      (let [{:keys [name desc value checked label link nodes]} @*node]
        (if @*editing?
          [:div.edit-card
           {:on-mouseOver #(.stopPropagation %)}
           [:span.action-buttons
            [:input.edit-button {:type "button" :value "✎"
                                 :on-click  #(swap! *editing? not)}]]
           (for [field-key [:value :name :label :desc :link :checked]]
             [:div (str field-key) [EditableField node-id field-key]])
           [:input {:type "button" :value "Remove"
                    :on-click (fn [] (re-frame/dispatch [::subs/remove-node node-id])
                                (reset! *show-actions? false)
                                (reset! *editing? false))}]
           ]

          [:div.card
           (let [props {:on-mouseOver (fn [e]
                                        (.stopPropagation e)
                                        (reset! *show-actions? true))
                        :on-mouseOut  #(reset! *show-actions? false)}]
             (if (not (or name label desc link (not-empty nodes)))
               (assoc props :class "small-card")
               props))

           (when @*show-actions?
             [:span.action-buttons
              [:input.edit-button {:type "button" :value "✎"
                                   :on-click  #(swap! *editing? not)}]
              [:input.edit-button {:type "button" :value "⤡" }]
              [:input.edit-button {:type "button" :value "➚" }]])

           [:h1
            (when (some? checked) [:span.check (if (= checked "true") "☑" "☐")])
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
                 [NodeCard id])]])
           ])

        ))))

(defn main-panel []
  (let [*head (re-frame/subscribe [::subs/head])]
    [:div#main
     [:div.column [NodeCard @*head]]
     [:div.column [NodeCard 2]]
     [:div.column [NodeCard 11]]
     ]))
