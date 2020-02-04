(ns bronze.views
  (:require
   [re-frame.core :as re-frame]
   [bronze.subs :as subs]
   ))

(defn NodeCard
  [node-id]
  (let [*node (re-frame/subscribe [::subs/node node-id])]
    (fn []
      (let [{:keys [name desc value checked label link nodes]} @*node]
        [:div.card
         [:h1
          (when (some? checked) [:span.check (if checked "☑" "☐")])
          (when value [:span.value value])
          (when name [:span name])]
         (when label [:small label])
         (when desc [:p desc])
         (when link [:p link])

         (when (not-empty nodes)
           [:div.card-list
            (for [id nodes]
              [NodeCard id])])
         ]))))

(defn main-panel []
  (let [*head (re-frame/subscribe [::subs/head])]
    [:div#main
     [:div.column [NodeCard @*head]]
     [:div.column [NodeCard 2]]]

    ))
