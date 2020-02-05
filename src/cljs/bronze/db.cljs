(ns bronze.db)

(def default-db
  {:panes {:root 0
           0 {:id 0
              :node-id 1
              :next-pane nil}}

   :nodes {:root 1

           1 {:name "Tu-Kirno"
              :desc "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut vel congue lacus. Maecenas vitae sem non purus ullamcorper scelerisque nec et justo. Mauris convallis, nunc laoreet rhoncus malesuada, metus ligula sodales erat, in fringilla mauris arcu in nisl. Nulla mi orci, tincidunt id leo non, ultricies sagittis est."
              :parent nil
              :nodes [2 5 9 11]}

           2 {:name "Aspects"
              :parent 1
              :nodes [3 4 4.1 4.2 4.3]}

           3 {:name "Light fingered sea elf incendiary enthusiast"
              :label "High Concept"
              :parent 2}

           4 {:name "Can't resist shiny things"
              :label "Trouble"
              :parent 2}

           4.1 {:name "Gonna do a crime"
                :parent 2}
           4.2 {:name "Varis is my BFF"
                :parent 2}
           4.3 {:name "Informal economy"
                :parent 2
                :nodes [4.31 4.32 4.33]}

           4.31 {:checked "false"
                 :parent 4.3}
           4.32 {:checked "false"
                 :parent 4.3}
           4.33 {:checked "false"
                 :parent 4.3}

           5 {:name "Skills"
              :parent 1
              :nodes [6 7 8]}
           6 {:name "Shoot"
              :value "+4"
              :parent 5}
           7 {:name "Athletics"
              :value "+3"
              :parent 5}
           8 {:name "Craft"
              :value "+2"
              :parent 5}

           9 {:name "Stunts"
              :parent 1
              :nodes [10]}

           10 {:name "Got a light?"
               :desc "+2 to Craft when making explosives."
               :parent 9}

           11 {:name    "Lil' Yachty"
               :desc    "A nice description"
               :value   +2
               :checked "false"
               :label   "High Concept"
               :link    "https://fate-srd.com"
               :parent  nil}

           }})
