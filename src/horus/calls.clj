(ns horus.calls
  (:import (com.twilio.sdk.verbs Say TwiMLResponse)))

(defn twiml []
  (let [response (TwiMLResponse.)]
    (.append response (Say. "Hello"))
    (.toXML response)))
