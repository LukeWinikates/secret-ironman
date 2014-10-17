(ns horus.heroku-util-spec
  (:require [speclj.core :refer :all]
            [horus.heroku-util :refer :all]))

(describe "horus.heroku-util"
  (describe "heroku-jdbc-uri"
    (it "turns heroku's database-url into a JDBC uri"
      (let [parsed (heroku-jdbc-uri "postgres://user:password@some-ec2-instance.compute-n.amazonaws.com:1111/dbname")]
        (should= "jdbc:postgresql://some-ec2-instance.compute-n.amazonaws.com:1111/dbname?user=user&password=password" parsed)))
    (it "is a no-op on uris that already have the jdbc: protocal"
      (let [parsed (heroku-jdbc-uri "jdbc:postgresql://localhost/horus-test")]
        (should= "jdbc:postgresql://localhost/horus-test" parsed)))))
