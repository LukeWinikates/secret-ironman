(ns horus.landing-page
  (:use hiccup.core
        hiccup.page))

(def resource
  (html5 [:head
          [:link { :rel "stylesheet" :href "//bootswatch.com/sandstone/bootstrap.css" }]
          [:title "Horus | Voice Recordings Sent to your Inbox"]]
         [:body
          [:div.container
          [:div.jumbotron
           [:h1 "Horus"]
           [:p "A personal dictation assistant that sends voice recordings to your inbox"]]]]))
