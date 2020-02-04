(ns bronze.subs
  (:require
   [re-frame.core :as re-frame]))

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
 ::head
 (fn [db]
   (:head db)))
