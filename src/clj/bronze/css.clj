(ns bronze.css
  (:require
   [garden.def :refer [defstyles]]
   [garden.selectors :as s]))

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

  [:.small-card {:display "inline"}
   [:h1 {:display "inline"}]]

  [:.collapse {:display "none"}]

  [:.card
   {:font-size "0.9em"
    :padding "0.5em 0 0 0"
    :margin-bottom "1em"}



   [:h1 {:font-size "1.1em"
         :margin "0"
         }]

   [:small {:color "#555"
            :font-style "italic"}]

   [:.check {:padding-right "0.25em"}]
   [:.value {:padding-right "0.5em"}]
   [:.desc {:margin-top "0.25em"}]
   [:.name {:cursor "pointer"}]

   [:.card-list {:margin-left "0.7em"}]
   [:hr {:display "none"}]
   ]

  [(s/> :.column :.card :.card-list :.card :hr)
   {:display "block"
    :border "1px solid black"
    :margin-top "0.5em"}

   ]

  )
