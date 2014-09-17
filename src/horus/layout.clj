(ns horus.layout
  (:use hiccup.core
        hiccup.page))

(defn layout [content]
  (html5 [:head
          [:link { :rel "stylesheet" :href "//bootswatch.com/sandstone/bootstrap.css" }]
          [:title "Horus | Voice Recordings Sent to your Inbox"]]
         [:body
          [:div.container content ]]))
