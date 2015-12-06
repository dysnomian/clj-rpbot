(ns clj-rpbot.parser
  :require [clojure.data.json :as json])

(def example-slack-request
  (json/write-str {:a 1 :b 2}))

