(ns horus.core-spec
  (:require [speclj.core :refer :all]
            [horus.calls])
  (:use horus.core
        ring.mock.request))

(describe "the root url"
  (it "shows a welcome message"
    (let [response (app (request :get "/"))
          {status :status body :body} response]
      (should= 200 status)
      (should-contain "Horus" body))))

(describe "receiving a call"
  (it "responds with xml"
    (let [response (app (request :post "/calls"))
      { status :status headers :headers body :body } response]
      (should= "text/xml; charset=utf-8" (get headers "Content-Type"))
      (should= body (horus.calls/twiml))
      (should= 200 status))))
