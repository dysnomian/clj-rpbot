(ns clj-rpbot.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [clj-rpbot.parser :as parser]))

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
                   :summary "return the character description"
                   :query-params [text :- String]
                   (ok (parser/show text)))))
