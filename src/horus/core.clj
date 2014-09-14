(ns horus.core
  (:use compojure.core)
  (:require [compojure.route :as route]
            [compojure.handler :refer [site]]
            [environ.core :refer [env]]
            [horus.calls :as calls]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.logger :as logger])
  (:gen-class))

(defroutes app-routes
  (GET "/" [] "<h1>Horus</h1>")
  (POST "/calls" [] calls/resource))

(def app
  (-> app-routes
    (logger/wrap-with-logger)))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))
