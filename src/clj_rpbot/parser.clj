(ns clj-rpbot.parser
  (:require [clojure.data.json :as json]
            [clj-rpbot.character :as character]))

(def example-slack-request
  {:user_name "liss"
   :command "/ex"
   :text "Andrea"})

(def example-slack-response
  (json/write-str {:response_type "ephemeral"
                   :text "Andrea is a cute pink-haired pixie."}))

(def context character/characters)

(def examine-synonyms '("/ex" "/examine"))

(defn form-response [text]
  {:response_type "ephemeral"
   :text text})

(defn show [text-params]
    (if (some #{text-params} (keys context))
      (form-response (character/show text-params))
      (form-response "Huh? I don't know who you're trying to examine.")))
