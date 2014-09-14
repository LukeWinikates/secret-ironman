(ns horus.sms-client
  (:require [environ.core :refer [env]])
  (:import (com.twilio.sdk TwilioRestClient)
           (org.apache.http.message BasicNameValuePair)))

(def twilio-account
  (env :twilio-account))

(def twilio-auth-token
  (env :twilio-auth-token))

(def twilio-number
  (env :twilio-number))

(defn ^:private pair [k v]
  (BasicNameValuePair. k v))

(defn ^:private to-twilio-client-params [params]
  [(pair "To" (get params "To"))
   (pair "Body" (get params "Body"))
   (pair "From" twilio-number)])

(defn send-message [params]
  (let [message-params (to-twilio-client-params params)]
    (->
      (TwilioRestClient. twilio-account twilio-auth-token)
      (.getAccount)
      (.getMessageFactory)
      (.create message-params))))

