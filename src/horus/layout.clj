(ns horus.layout
  (:use hiccup.core
        hiccup.page)
  (:require [environ.core :refer [env]]))

(defn assets []
  (if (env :horus-use-local-assets)
    [:link { :rel "stylesheet" :href "vendor/bootswatch/readable/bootstrap.css" }]
    [:link { :rel "stylesheet" :href "//maxcdn.bootstrapcdn.com/bootswatch/3.2.0/readable/bootstrap.min.css" }]))

(defn layout [& content]
  (html5 [:head
          [:title "Horus | Voice Recordings Sent to your Inbox"]]
          (assets)
         [:body
          [:div.container content ]]))
