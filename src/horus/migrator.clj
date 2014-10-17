(ns horus.migrator
  (:require [environ.core :refer [env]]
            [joplin.main :as joplin]
            [horus.heroku-util :as heroku]))

(defn databases []
  { :sql-prod {:type :jdbc :url (heroku/heroku-jdbc-uri (env :database-url))}
   :sql-dev {:type :jdbc :url "jdbc:postgresql://localhost/horus-dev" }
   :sql-test {:type :jdbc :url "jdbc:postgresql://localhost/horus-test" }})

(def environments
  {:dev [{:db :sql-dev :migrator :sql-mig}]
   :test [{:db :sql-test :migrator :sql-mig}]
   :production [{:db :sql-prod :migrator :sql-mig}] })

(def migrators
  { :sql-mig "migrations" })

(def seeds {})

(defn -main [command & args]
  (apply joplin/-main
    "-r" "joplin.jdbc.database"
    "-e" environments
    "-d" (databases)
    "-m" migrators
    "-s" seeds
    ;"migrate" "test")
    command args)
  (println "yeah!"))
