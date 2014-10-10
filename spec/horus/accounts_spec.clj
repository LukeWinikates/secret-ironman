(ns horus.accounts-spec
  (:require [speclj.core :refer :all]
            [noir.util.crypt :as c]
            [horus.accounts :as accounts]
            [ring.mock.request :refer :all]))

(describe "horus.accounts"
  (describe "valid?"
    (it "requires a valid email"
        (let [email "las"
              phone "15553331234"
              password "onabike"
              account-params {:email email :phone phone :password password}]
         (let [response (accounts/resource (assoc (request :post "/accounts") :params account-params))
               {status :status body :body} response]
           (should= 422 status))))

    (it "requires a valid phone number"
        (let [email "irisandspike@coolwedding.com"
              phone "15535"
              password "onabike"
              account-params {:email email :phone phone :password password}]
         (let [response (accounts/resource (assoc (request :post "/accounts") :params account-params))
               {status :status body :body} response]
           (should= 422 status))))

    (it "requires a password"
        (let [email "irisandspike@coolwedding.com"
              phone "15553331234"
              password ""
              account-params {:email email :phone phone :password password}]
         (let [response (accounts/resource (assoc (request :post "/accounts") :params account-params))
               {status :status body :body} response]
           (should= 422 status)))))

  (describe "creating an account"
    (it "adds an account to the database"
        (let [email "irisandspike@coolwedding.com"
              phone "15553331234"
              password "onabike"
              account-params {:email email :phone phone :password password}]
          (accounts/resource (assoc (request :post "/accounts") :params account-params))
          (let [created (last (accounts/all))]
            (should= email (:email created))
            (should= phone (:phone created))
            (should (c/compare password (:password_digest created))))))))
