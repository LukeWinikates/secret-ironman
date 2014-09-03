(ns horus.core-spec
  (:require [speclj.core :refer :all])
  (:use horus.core
        ring.mock.request))

(describe "the root url"
  (it "shows a welcome message"
    (let [response (app (request :get "/"))
          {status :status body :body} response]
      (should= 200 status)
      (should-contain "Horus" body))))

