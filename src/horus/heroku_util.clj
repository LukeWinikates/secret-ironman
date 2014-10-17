(ns horus.heroku-util
  (:require [clojure.string :as string]))

(defn heroku-jdbc-uri [heroku-database-uri]
  (if (.startsWith heroku-database-uri "postgres://")
    (let [[_ user password host port db] (string/split heroku-database-uri #"://|:|@|/")]
      (str "jdbc:postgresql://" host ":" port "/" db "?user=" user "&password=" password))
    heroku-database-uri))
