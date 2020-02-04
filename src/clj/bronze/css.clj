(ns bronze.css
  (:require
   [garden.def :refer [defstyles]]))

(declare screen)

(defstyles screen
  [:body {:font-size "18px"
          :font-family "sans-serif"}]

  [:#main {:display "flex"
           :flex-direction "horizontal" }]

  [:.column {:width "20em"
             :padding "0 1em"}]

  [:.edit-button {:float "right"
                  :padding "0"}]

[:.edit-card {:padding "0.5em 0 0.5em 0"}]

  [:.card
   {:font-size "0.85em"
    :padding "0.5em 0 0.5em 0"}

   [:h1 {:font-size "1.3em"
         :margin "0"}]

   [:.check {:padding-right "0.25em"}]
   [:.value {:padding-right "0.5em"}]

   [:.card-list {:margin-left "1em"}]]

  )
