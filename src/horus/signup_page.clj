(ns horus.signup-page
  (:use horus.layout
        hiccup.page
        hiccup.form
        ring.util.anti-forgery))

(defn form-group [field content]
  [:div.form-group
   (label {:class "control-label col-md-2"} field field)
   [:div.col-md-4
    content]])


(defn resource []
  (layout [:h1 "Sign Up"]
          [:p "Create a new account"]
          (form-to {:class "form-horizontal"} [:post "accounts/create"]
                   (anti-forgery-field)
                   (form-group "email"
                    (text-field {:class "form-control"} "email"))
                   (form-group "phone"
                    (text-field {:class "form-control" :type "tel"} "phone"))
                   (form-group "password"
                    (password-field {:class "form-control"} "password"))
                   [:div.form-group
                    [:div.col-md-6
                     (submit-button {:class "btn btn-primary btn-large pull-right"} "Create Account")]])))
