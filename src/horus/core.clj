(ns horus.core
  (:use compojure.core
        ring.util.response)
  (:require [compojure.route :as route]
            [horus.calls]
            [compojure.handler :refer [site]]
            [environ.core :refer [env]]
            [ring.adapter.jetty :as jetty])
  (:gen-class))

(defroutes app
  (GET "/" [] "<h1>Horus</h1>")
  (POST "/calls" []
       (-> {:status 200, :body (horus.calls/twiml)} (content-type "text/xml; charset=utf-8"))))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))
