(ns horus.core-spec
  (:require [speclj.core :refer :all]
            [horus.calls]
            [horus.core :refer [app-routes]])
  (:use ring.mock.request))

(describe "horus.core"
  (with app (app-routes { :sms identity }))
  (describe "GET /"
    (it "shows a welcome message"
      (let [response (@app (request :get "/"))
            {status :status body :body} response]
        (should= 200 status)
        (should-contain "Horus" body))))

  (describe "POST /calls"
    (it "responds with xml"
      (let [response (@app (request :post "/calls"))
        { status :status headers :headers body :body } response]
        (should= "text/xml; charset=utf-8" (get headers "Content-Type"))
        (should= body horus.calls/twiml)
        (should= 200 status))))

  (describe "POST /recordings"
    (it "responds with 201 created"
      (let [response (@app (request :post "/recordings"))
            { status :status} response]
        (should= 201 status)))))
