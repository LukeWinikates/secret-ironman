(ns horus.core
  (:use compojure.core)
  (:require [compojure.route :as route]
            [compojure.handler :refer [site]]
            [environ.core :refer [env]]
            [horus.calls]
            [ring.adapter.jetty :as jetty])
  (:gen-class))

(defroutes app
  (GET "/" [] "<h1>Horus</h1>")
  (POST "/calls" [] horus.calls/resource))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))
