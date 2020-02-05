(ns bronze.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::panes
 (fn [db]
   (:panes db)))

(re-frame/reg-event-db
 ::new-pane
 (fn [db [_ current-pane-id node-id]]
   (let [next-id (get-in db [:panes current-pane-id :next-pane])
         new-id (random-uuid)
         new-pane {:id new-id
                   :node-id node-id
                   :next-pane next-id}]
     (-> db
         (assoc-in [:panes new-id] new-pane)
         (assoc-in [:panes current-pane-id :next-pane] new-id)))))

(re-frame/reg-sub
 ::nodes
 (fn [db]
   (:nodes db)))

(re-frame/reg-sub
 ::node
 (fn [_ _]
   (re-frame/subscribe [::nodes]))
 (fn [nodes [_ node-id]]
   (get nodes node-id)))

(re-frame/reg-sub
 ::node-key
 (fn [[_ node-id _field-key] _]
   (re-frame/subscribe [::node node-id]))
 (fn [node [_ _node-id field-key]]
   (get node field-key)))

(re-frame/reg-event-db
 ::update-node-key
 (fn [db [_ id field-key new-value]]
   (assoc-in db [:nodes id field-key] new-value)))

(defn get-children
  [nodes id]
  (let [child-ids (get-in nodes [id :nodes])]
    (when (not-empty child-ids)
      (concat child-ids (->> child-ids
                             (map #(get-children nodes %))
                             flatten
                             (remove nil?))))))

(re-frame/reg-event-db
 ::remove-node
 (fn [db [_ node-id]]
   (let [node (get-in db [:nodes node-id])
         parent-id (:parent node)
         child-ids (set (get-children (:nodes db) node-id))]
     (-> db
         (update-in [:nodes parent-id :nodes] #(remove #{node-id} %))
         (update :nodes (fn [nodes]
                          (reduce (fn [nodes id] (dissoc nodes id))
                                  nodes
                                  (conj child-ids node-id))))))))

(re-frame/reg-event-db
 ::add-node
 (fn [db [_ parent-id]]
   (let [node-id (random-uuid)]
     (-> db
         (update-in [:nodes parent-id :nodes] #(if %
                                                 (conj % node-id)
                                                 [node-id]))
         (update :nodes #(assoc % node-id {:name "New item"
                                           :parent parent-id}))))))
