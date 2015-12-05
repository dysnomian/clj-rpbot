(ns clj-rpbot.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

(s/defschema Message {:message String})

(def alice {:name "Alice"
            :user "l"
            :description "is a cute redhead"})
(def andrea {:name "Andrea"
            :user "a"
            :description "is a cute pixie"})
(def kale {:name "Kale"
            :user "t"
            :description "is a cute cyborg boy"})
(def kylie {:name "Kylie"
            :user "t"
            :description "is a cute cyborg girl"})

(def characters {"Alice" alice
                 "Andrea" andrea
                 "Kale" kale
                 "Kylie" kylie})

(defn present-description [character]
  (let [name (get character :name)
        description (get character :description)]
    (str name " " description)))

(defn show-character [name]
  (let [character (get characters name)]
    (present-description character)))

(defapi app
  {:formats [:json-kw]}
  (swagger-ui)
  (swagger-docs
    {:info {:title "RP Bot API"
            :description "Slackbot API for RP commands"}
     :tags [{:name "ping",
             :description "returns PONG"}
            {:name "examine",
             :description "returns a character's description"}]})

  (context* "/ping" []
    (GET* "/" []
      (ok {:ping "pong"})))

  (context* "/character" []
    :tags ["character"]
    (GET* "/show" []
      :return Message
      :query-params [name :- String]
      :summary "return the character description"
      (ok {:message (show-character name)})))

  (context* "/user" []
    :tags ["user"]
    (GET* "/" []
      :return Message
      :query-params [name :- String]
      :summary "say hello"
      (ok {:message (str "Terve, " name)}))))
