(ns horus.recordings
  (:use [liberator.core :only [defresource]])
  (:require [horus.sms-client :as sms]))

(defn recording-message [url]
  (str "Your recording is at " url "."))

(defresource resource [RecordingUrl Caller send-message]
  :allowed-methods [:post]
  :handle-created (fn [_] RecordingUrl)
  :handle-exception (fn [ctx] (println ctx))
  :post! (fn [ctx]
           (->
               { "To" Caller
                 "Body" (recording-message RecordingUrl)}
             (send-message)
             ctx)))
