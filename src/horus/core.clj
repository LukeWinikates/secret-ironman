(ns horus.core
  (:use compojure.core)
  (:require [compojure.route :as route]
            [compojure.handler :refer [site]]
            [environ.core :refer [env]]
            [ring.adapter.jetty :as jetty])
  (:gen-class))

(defroutes app
  (GET "/" [] "<h1>Horus</h1>"))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))
