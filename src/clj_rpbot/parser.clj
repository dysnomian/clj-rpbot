(ns clj-rpbot.parser
  (:require [clj-rpbot.character :as character]))

(def context character/characters)

(defn form-response [text]
  {:response_type "ephemeral"
   :text text})

(defn show [text-params]
  (if (some #{text-params} (keys context))
    (form-response (character/show text-params))
    (form-response "Huh? I don't know who you're trying to examine.")))
