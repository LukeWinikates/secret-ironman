(ns horus.calls-spec
  (:require [speclj.core :refer :all])
  (:use [horus.calls]))

(describe "(twiml)"
  (it "tells twilio to say something"
    (should-contain "<Say>" (twiml))))
