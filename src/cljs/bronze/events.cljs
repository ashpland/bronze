(ns bronze.events
  (:require
   [re-frame.core :as re-frame]
   [bronze.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/blank-db))
