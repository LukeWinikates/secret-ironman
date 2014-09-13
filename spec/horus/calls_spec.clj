(ns horus.calls-spec
  (:require [speclj.core :refer :all])
  (:use [horus.calls]))

(describe "horus.calls"
  (describe "twiml"
    (it "tells twilio to say something"
      (should-contain "<Say>" twiml))
    (it "tells twilio to record"
      (should-contain "<Record" twiml))
    (it "includes the ?xml declaration"
      (should-contain "<?xml" twiml))))
