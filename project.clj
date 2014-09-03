(defproject horus "0.1.0-SNAPSHOT"
  :description "A personal dictation assistant that sends transcripts to your inbox"
  :url "http://example.com/FIXME"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring "1.3.1"]
                 [compojure "1.1.8"]
                 [speclj "2.9.0"]
                 [ring-mock "0.1.5"]]
  :plugins [[speclj "2.9.0"]]
  :test-path "spec/"
  :main ^:skip-aot horus.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
