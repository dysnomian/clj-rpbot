(defproject clj-rpbot "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clj-time "0.9.0"] ; required due to bug in lein-ring
                 [metosin/compojure-api "0.22.0"]
                 [org.clojure/data.json "0.2.6"]]
  :ring {:handler clj-rpbot.handler/app}
  :uberjar-name "server.jar"
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]]
                   :plugins [[lein-ring "0.9.6"]]}})
