(defproject horus "0.1.0-SNAPSHOT"
  :min-lein-version "2.0.0"
  :description "A personal dictation assistant that sends transcripts to your inbox"
  :url "http://example.com/FIXME"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring "1.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [commons-codec/commons-codec "1.9"]
                 [compojure "1.1.8"]
                 [com.twilio.sdk/twilio-java-sdk "3.4.5"]
                 [joplin.core "0.1.12"]
                 [joplin.jdbc "0.1.12"]
                 [liberator "0.12.1"]
                 [lib-noir "0.9.0"]
                 [korma "0.4.0"]
                 [org.apache.httpcomponents/httpcore "4.1.2"]
                 [org.clojure/java.jdbc "0.3.5"]
                 [postgresql/postgresql "9.1-901-1.jdbc4"]
                 [ring/ring-anti-forgery "1.0.0"]
                 [ring.middleware.logger "0.5.0"]
                 [environ "1.0.0"]]
  :bower-dependencies [bootswatch "3.2.0"]
  :plugins [[joplin.lein "0.1.12"]
            [lein-bower "0.5.1"]
            [lein-environ "1.0.0"]
            [speclj "2.9.0"]]
  :test-paths ["spec"]
  :main horus.core
  :target-path "target/%s"
  :joplin {
           :migrators { :sql-mig "migrations" }
           :databases { :sql-dev { :type :jdbc :url  "jdbc:postgresql://localhost/horus-dev" }}
           :environments {:dev [{:db :sql-dev :migrator :sql-mig}] }}
  ;:ragtime { :database "jdbc:postgresql://localhost/horus-dev" :migrations ragtime.sql.files/migrations}
  :aliases { "spec" ["with-profile" "test" "spec" "-f" "d"] }
  :profiles {:uberjar {:aot :all}
             :test-env {}
             :dev-env {}
             :dev-deps { :dependencies [[speclj "2.9.0"]
                                        [ring-mock "0.1.5"]]}
             :dev [:dev-deps :dev-env ]
             :test [:dev-deps :test-env] })
