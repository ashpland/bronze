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
        *collapse? (reagent/atom false)]
    (fn []
      (let [{:keys [name desc value checked label link nodes]} @*node]
        (if @*editing?

          [:div.edit-card
           [:input.edit-button {:type "button" :value "✎"
                                :on-click  #(swap! *editing? not)}]
           (for [field-key [:value :name :label :desc :link :checked]]

             [:div (str field-key) [EditableField node-id field-key]]


             )]

          [:div.card
           (when (not (or name label desc link (not-empty nodes)))
             {:class "small-card"})
           [:input.edit-button {:type "button" :value "✎"
                                :on-click  #(swap! *editing? not)}]
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
