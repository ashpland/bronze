(ns bronze.db)

(def blank-db
  {:root-pane #uuid "5b8937e5-a765-4729-8c18-e99fb9b6b805", :panes {#uuid "5b8937e5-a765-4729-8c18-e99fb9b6b805" {:id #uuid "5b8937e5-a765-4729-8c18-e99fb9b6b805", :node-id #uuid "60cc09e1-4777-4120-a1aa-5f6e5cf81420", :next-pane nil}}, :root-node #uuid "60cc09e1-4777-4120-a1aa-5f6e5cf81420", :nodes {#uuid "60cc09e1-4777-4120-a1aa-5f6e5cf81420" {:name "Blank Card", :parent nil}}})

(def sample-db
 {:root-pane 0, :panes {0 {:id 0, :node-id 0, :next-pane #uuid "3d6bf222-9a39-471c-aa9b-96eeda7c2955"}, #uuid "3d6bf222-9a39-471c-aa9b-96eeda7c2955" {:id #uuid "3d6bf222-9a39-471c-aa9b-96eeda7c2955", :node-id 2, :next-pane nil}}, :root-node 0, :nodes {0 {:name "The Boy Who Fell Out Of The Sky", :nodes [0.1]}, 0.1 {:name "Characters", :nodes [1]}, 1 {:name "Tu-Kirno", :desc "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut vel congue lacus. Maecenas vitae sem non purus ullamcorper scelerisque nec et justo. Mauris convallis, nunc laoreet rhoncus malesuada, metus ligula sodales erat, in fringilla mauris arcu in nisl. Nulla mi orci, tincidunt id leo non, ultricies sagittis est.", :parent nil, :nodes [2 5 9 10.5]}, 2 {:name "Aspects", :parent 1, :nodes [3 4 4.1 4.2 4.3]}, 3 {:name "Light fingered sea elf incendiary enthusiast", :label "High Concept", :parent 2}, 4.3 {:name "Informal economy", :parent 2, :nodes [4.31 4.32 4.33]}, 4.1 {:name "Gonna do a crime", :parent 2}, 4.2 {:name "Varis is my BFF", :parent 2}, 4 {:name "Can't resist shiny things", :label "Trouble", :parent 2}, 4.32 {:checked "false", :parent 4.3}, 4.31 {:checked "false", :parent 4.3}, 4.33 {:checked "false", :parent 4.3}, 5 {:name "Skills", :parent 1, :nodes [6 7 8]}, 6 {:name "Shoot", :value "+4", :parent 5}, 7 {:name "Athletics", :value "+3", :parent 5}, 8 {:name "Craft", :value "+2", :parent 5}, 9 {:name "Stunts", :parent 1, :nodes [10]}, 10.5 {:name "Extras", :nodes [11]}, 10 {:name "Got a light?", :desc "+2 to Craft when making explosives.", :parent 9}, 11 {:name "Lil' Yachty", :desc "A nice description", :value 2, :checked "false", :label "High Concept", :link "https://fate-srd.com", :parent nil}}}
  )

(def old-default-db
  {:root-pane 0
   :panes {0 {:id 0
              :node-id 0
              :next-pane nil}}

   :root-node 0
   :nodes {0 {:name "The Boy Who Fell Out Of The Sky"
              :nodes [0.1]}

           0.1 {:name "Characters"
                :nodes [1]}

           1 {:name "Tu-Kirno"
              :desc "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut vel congue lacus. Maecenas vitae sem non purus ullamcorper scelerisque nec et justo. Mauris convallis, nunc laoreet rhoncus malesuada, metus ligula sodales erat, in fringilla mauris arcu in nisl. Nulla mi orci, tincidunt id leo non, ultricies sagittis est."
              :parent nil
              :nodes [2 5 9 10.5]}

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

           10.5 {:name "Extras"
                 :nodes [11]}

           11 {:name    "Lil' Yachty"
               :desc    "A nice description"
               :value   +2
               :checked "false"
               :label   "High Concept"
               :link    "https://fate-srd.com"
               :parent  nil}

           }})
