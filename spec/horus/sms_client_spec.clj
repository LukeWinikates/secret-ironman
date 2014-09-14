(ns horus.sms-client-spec
  (:import (com.twilio.sdk TwilioRestClient))
  (:require [speclj.core :refer :all]
            [environ.core :refer [env]])
  (:use [horus.sms-client]))

(describe "horus.sms-client"
  (describe "send-message"
    (it "sends a message using the twilio API"
      (let [to-number (env :test-number)
            body "let's send a message with the test credentials"
            params { "To" to-number
                     "Body" body }
            message (send-message  params)]
        (should= "queued" (.getStatus message))
        (should= to-number (.getTo message))
        (should= body (.getBody message))
        (should-be-nil (.getErrorCode message))))))
