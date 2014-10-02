(ns horus.accounts-spec
  (:require [speclj.core :refer :all]
            [horus.accounts :as accounts])
  (:use horus.recordings
        ring.mock.request))

(describe "horus.accounts"
  (describe "creating an account"
    (it "adds an account to the database"
        (let [email "irisandspike@coolwedding.com"
              phone "15555551235"
              account-params {:email email :phone phone}]
          (accounts/resource (assoc (request :post "/accounts") :params account-params))
          (let [created (last (accounts/all))]
            (should= email (:email created))
            (should= phone (:phone created)))))))
