(ns horus.calls
  (:use ring.util.response)
  (:import (com.twilio.sdk.verbs Record Say TwiMLResponse)))

(def twiml
  (let [response (TwiMLResponse.)]
    (.append response (Say. "Hello, please begin your recording after the tone."))
    (.append response
      (doto (Record.)
        (.setAction "/recordings")))
    (str
      "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
      (.toXML response))))

(def resource
  (-> {:status 200, :body twiml} (content-type "text/xml; charset=utf-8")))
