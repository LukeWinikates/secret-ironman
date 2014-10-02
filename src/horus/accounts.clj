(ns horus.accounts
  (:require [liberator.core :refer [defresource]]
            [korma.db :refer :all]
            [korma.core :refer :all]))

(defdb db-dev (postgres {:db "horus-dev"}))

(defentity accounts
  (database db-dev))

(defresource resource
 :allowed-methods [:post]
  :available-media-types ["text/plain" "text/html"]
  :handle-exception (fn [ctx] (.printStackTrace (:exception ctx)))
  :post! (fn [ctx]
           (let [params (get-in ctx [:request :params])]
             (insert accounts
                         (values (select-keys params [:email :phone])))
             ctx))
  :handle-created "Welcome!")

(defn all []
  (select accounts))
