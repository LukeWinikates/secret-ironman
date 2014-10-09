(ns horus.accounts-spec
  (:require [speclj.core :refer :all]
            [horus.accounts :as accounts])
  (:use horus.recordings
        ring.mock.request))

(describe "horus.accounts"
  (describe "valid?"
    (it "requires a valid email"
        (let [email "las"
              phone "15553331234"
              account-params {:email email :phone phone}]
         (let [response (accounts/resource (assoc (request :post "/accounts") :params account-params))
               {status :status body :body} response]
           (should= 422 status))))

    (it "requires a valid phone number"
        (let [email "irisandspike@coolwedding.com"
              phone "15535"
              account-params {:email email :phone phone}]
         (let [response (accounts/resource (assoc (request :post "/accounts") :params account-params))
               {status :status body :body} response]
           (should= 422 status)))))

  (describe "creating an account"
    (it "adds an account to the database"
        (let [email "irisandspike@coolwedding.com"
              phone "15553331234"
              account-params {:email email :phone phone}]
          (accounts/resource (assoc (request :post "/accounts") :params account-params))
          (let [created (last (accounts/all))]
            (should= email (:email created))
            (should= phone (:phone created)))))))
