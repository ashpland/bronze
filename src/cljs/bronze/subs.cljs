(ns bronze.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::head
 (fn [db]
   (:head db)))

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

(re-frame/reg-event-db
 ::remove-node
 (fn [db [_ id]]
   (let [parent-id (get-in db [:nodes id :parent])]
     (-> db
         (update-in [:nodes parent-id :nodes] #(remove #{id} %))
         (update :nodes #(dissoc % id))))))
