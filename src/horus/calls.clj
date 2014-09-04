(ns horus.calls
  (:import (com.twilio.sdk.verbs Say TwiMLResponse)))

(defn twiml []
  (let [response (TwiMLResponse.)]
    (.append response (Say. "Hello"))
    (str
      "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
      (.toXML response))))
