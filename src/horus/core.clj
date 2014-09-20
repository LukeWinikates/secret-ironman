(ns horus.core
  (:use compojure.core
        ring.middleware.session
        ring.middleware.anti-forgery)
  (:require [compojure.route :as route]
            [compojure.handler :refer [site]]
            [environ.core :refer [env]]
            [horus.calls :as calls]
            [horus.recordings :as recordings]
            [horus.landing-page :as landing-page]
            [horus.signup-page :as signup-page]
            [horus.sms-client :as sms]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.logger :as logger])
  (:gen-class))

(defn app-routes [deps]
  (let [{sms-client :sms} deps]
    (routes
      (GET "/" [] landing-page/resource)
      (GET "/signup" [] (signup-page/resource))
      (POST "/calls" [] calls/resource)
      (POST "/recordings" [RecordingUrl Caller] (recordings/resource RecordingUrl Caller sms-client)))))

(def app
  (-> (app-routes { :sms sms/send-message })
    (wrap-anti-forgery)
      (wrap-session)
    (logger/wrap-with-logger)))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))

(def ^:dynamic server nil)

(defn restart []
  (if server
    (do
      (.stop server)
      (.join server)))
  (require 'horus.core :reload-all)
  (def server (-main 5000)))
