(ns horus.core-spec
  (:require [speclj.core :refer :all]))

(defn hello-world []
  "hello world")

(describe "the world"
  (it "gets a greeting"
    (should (hello-world))))

