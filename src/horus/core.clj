(ns horus.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [environ.core :refer [env]]
            [horus.accounts :as accounts]
            [horus.calls :as calls]
            [horus.recordings :as recordings]
            [horus.landing-page :as landing-page]
            [horus.signup-page :as signup-page]
            [horus.sms-client :as sms]
            [liberator.dev]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.logger :as logger]
            [ring.middleware.resource :refer [wrap-resource]])
  (:gen-class))

(defn app-routes [deps]
  (let [{sms-client :sms} deps]
    (routes
      (GET "/" [] landing-page/resource)
      (GET "/signup" [] (signup-page/resource))
      (POST "/calls" [] calls/resource)
      (POST "/accounts" [] accounts/resource)
      (POST "/recordings" [RecordingUrl Caller] (recordings/resource RecordingUrl Caller sms-client)))))


(def app
  (-> (app-routes { :sms sms/send-message })
      (wrap-defaults site-defaults)
      (liberator.dev/wrap-trace :ui)
      (logger/wrap-with-logger)))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty app {:port port :join? false})))

(def ^:dynamic server nil)

(defn restart []
  (if server
    (do
      (.stop server)
      (.join server)))
  (require 'horus.core :reload-all)
  (def server (-main 5000)))
