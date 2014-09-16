(ns horus.recordings-spec
  (:require [speclj.core :refer :all]
            [horus.recordings :as recordings])
  (:use horus.recordings
        ring.mock.request))

(describe "horus.recordings"
  (describe "saving a recording"
    (with-stubs)
    (it "sends an SMS message containing a link to the recording"
      (let [recording-url "http://example.com/audio.mp3"
            caller "+15550001122"
            send-message (stub :send-message)
            recordings-resource (recordings/resource recording-url caller send-message)
            response (recordings-resource (request :post recording-url caller))]
        (should-have-invoked
          :send-message
          {:with [{ "To" caller
                   "Body" (recording-message recording-url)}]})))))
