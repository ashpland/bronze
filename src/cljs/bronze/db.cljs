(ns bronze.db)

(def default-db
  {:head 1

   :nodes {
           1 {:name "Tu-Kirno"
              :desc "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut vel congue lacus. Maecenas vitae sem non purus ullamcorper scelerisque nec et justo. Mauris convallis, nunc laoreet rhoncus malesuada, metus ligula sodales erat, in fringilla mauris arcu in nisl. Nulla mi orci, tincidunt id leo non, ultricies sagittis est."
              :nodes [2 5 9]}

           2 {:name "Aspects"
              :nodes [3 4 4.1 4.2 4.3]}

           3 {:name "Light fingered sea elf incendiary enthusiast"
              :label "High Concept"}

           4 {:name "Can't resist shiny things"
              :label "Trouble"}

           4.1 {:name "Gonna do a crime"}
           4.2 {:name "Varis is my BFF"}
           4.3 {:name "Informal economy"
                :nodes [4.31 4.32 4.33]}

           4.31 {:checked false}
           4.32 {:checked false}
           4.33 {:checked false}

           5 {:name "Skills"
              :nodes [6 7 8]}
           6 {:name "Shoot"
              :value "+4"}
           7 {:name "Athletics"
              :value "+3"}
           8 {:name "Craft"
              :value "+2"}

           9 {:name "Stunts"
              :nodes [10]}

           10 {:name "Got a light?"
               :desc "+2 to Craft when making explosives."}

           11 {:name    "Hungry Goat"
               :desc    "A nice description"
               :value   +2
               :checked false
               :label   "High Concept"
               :link    "https://fate-srd.com"}

           }})
