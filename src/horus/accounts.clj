(ns horus.accounts
  (:require [clojure.string :as str]
            [environ.core :refer [env]]
            [liberator.core :refer [defresource]]
            [korma.db :refer :all]
            [noir.util.crypt :as c]
            [noir.validation :as v]
            [korma.core :refer :all]))

(defdb db { :naming { :fields #(str/replace % "-" "_") }
             :connection-uri (env :database-url)})

(defentity accounts
  (database db))

(defn valid? [ctx]
  (let [params (get-in ctx [:request :params])]
    (and
      (v/min-length? (:password params) 4)
      (v/matches-regex? (:phone params) #"^\d{11}$")
      (v/is-email? (:email params)))))

(defresource resource
 :allowed-methods [:post]
  :available-media-types ["text/plain" "text/html"]
  :handle-exception (fn [ctx] (.printStackTrace (:exception ctx)))
  :processable? valid?
  :post! (fn [ctx]
           (let [params (get-in ctx [:request :params])
                 sql-params (assoc (select-keys params [:email :phone])
                                   :password-digest (c/encrypt (:password params)))]
             (insert accounts
                     (values sql-params))
             ctx))
  :handle-created "Welcome!")

(defn all []
  (select accounts))
