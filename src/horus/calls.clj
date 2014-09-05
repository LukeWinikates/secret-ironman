(ns horus.calls
  (:import (com.twilio.sdk.verbs Record Say TwiMLResponse)))

(defn twiml []
  (let [response (TwiMLResponse.)]
    (.append response (Say. "Hello, please begin your recording after the tone."))
    (.append response
      (doto (Record.)
        (.setAction "/recordings")))
    (str
      "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
      (.toXML response))))
