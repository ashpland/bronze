(ns bronze.css
  (:require
   [garden.def :refer [defstyles]]
   [garden.selectors :as s]))

(declare screen)

(def color {:main "#CD7F32"
            :menu-bar "#eee"})

(defstyles screen

  [:body {:font-size "18px"
          :font-family "sans-serif"
          :margin 0 }]

  [:#menu-bar
   {:position "fixed"
    :left 0
    :right 0

    :height "2em"
    :background-color (:menu-bar color)
    :box-shadow "0 0.1em 0.3em 0 rgba(0,0,0,0.75)"
    :display "flex"
    ; :align-items "flex-start"
    :align-items "center"
    }

   [:.logo {:display "block"
            :padding "0.2em 0.2em 0.1em 0.2em"
            :margin "0.3em"
            :border-radius "5px"
            :color (:menu-bar color)
            :background-color (:main color)
            :font-weight "bold"
            :font-size "1.1em"
            :letter-spacing "0.1em"}]
   ]

  [:#main {:display "flex"
           :height "100vh"}]

  [:.column {:min-width "17em"
             :max-width "25em"
             :margin-top "2em"
             :width "17em"

             :padding "1em 1em 0"
             :overflow "scroll"}
   ]
[:.single-column {:min-width "inherit"
                     :width "95vw"}]

  [:.action-buttons
   {:float "right"}
   [:input {:padding "0"}]]

  [:.collapse-arrow {:padding-right "0.2em"
                     :font-size "0.5em"
                     :vertical-align "middle"}]

  [:.card :.edit-card
  {:font-size "0.9em"}

   [:h1 {:font-size "1.1em"
         :margin "0"}]

   [:h3 {:font-size "1em"
         :margin-bottom "0"}]

   [:.check {:padding-right "0.25em"}]
   [:.value {:padding-right "0.25em"}]

   ]

  [:.edit-card {:padding "0.5em 0 0.5em 0"}

   [:table {:width "100%"}
    [:input {:padding "0"}]]]

  [:.small-card {:display "inline"
                 :padding-right "0.5em"}
   [:.small-value {:padding-right "0.1em"}]]

  [:.collapse {:display "none"}]

  [:.card
   {:padding "0.5em 0 1em 0"}


   [:small {:color "#555"
            :font-style "italic"}]

   [:.desc {:margin-top "0.25em"}]

   [:.card-list {:margin-left "0.7em"}]
   ]

  [(s/> :.column :.card :.card-list :.card :hr)
   {:display "block"
    :border "1px solid black"
    :margin-top "0.5em"}

   ]

  )
