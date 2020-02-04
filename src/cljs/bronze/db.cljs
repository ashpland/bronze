(ns bronze.db)

(def default-db
  {:head 1

   :nodes {
           1 {:name "Tu-Kirno"
              :desc "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut vel congue lacus. Maecenas vitae sem non purus ullamcorper scelerisque nec et justo. Mauris convallis, nunc laoreet rhoncus malesuada, metus ligula sodales erat, in fringilla mauris arcu in nisl. Nulla mi orci, tincidunt id leo non, ultricies sagittis est."
              :nodes [2 5 9]}

           2 {:name "Aspects"
              :nodes [3 4]}

           3 {:name "Light fingered sea elf incendiary enthusiast"
              :label "High Concept"}

           4 {:name "Can't resist shiny things"
              :label "Trouble"}

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

           }})
