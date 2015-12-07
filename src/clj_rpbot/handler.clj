(ns clj-rpbot.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [clj-rpbot.parser :as parser]))

(s/defschema Message {:message String})

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
    (POST* "/show" []
      :return Message
      :body-params [text :- String]
      :summary "return the character description"
      (ok {:message (parser/show text)})))

  (context* "/user" []
    :tags ["user"]
    (GET* "/" []
      :return Message
      :query-params [name :- String]
      :summary "say hello"
      (ok {:message (str "Terve, " name)}))))
