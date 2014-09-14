(defproject horus "0.1.0-SNAPSHOT"
  :min-lein-version "2.0.0"
  :description "A personal dictation assistant that sends transcripts to your inbox"
  :url "http://example.com/FIXME"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring "1.3.1"]
                 [compojure "1.1.8"]
                 [com.twilio.sdk/twilio-java-sdk "3.4.5"]
                 [org.apache.httpcomponents/httpcore "4.1.2"]
                 [ring.middleware.logger "0.5.0"]
                 [environ "1.0.0"]]
  :plugins [[lein-environ "1.0.0"]
            [speclj "2.9.0"]]
  :test-paths ["spec"]
  :main horus.core
  :target-path "target/%s"
  :aliases { "spec" ["with-profile" "test" "spec"] }
  :profiles {:uberjar {:aot :all}
             :dev-deps { :dependencies [[speclj "2.9.0"]
                                        [ring-mock "0.1.5"]]}
             :dev [:dev-deps :dev-env]
             :test [:dev-deps :test-env] })
