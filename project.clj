(defproject clj-rpbot "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [clj-time "0.9.0"] ; required due to bug in lein-ring
                 [org.clojure/data.json "0.2.6"]
                 [metosin/compojure-api "0.22.0"]
                 [com.apa512/rethinkdb "0.11.0"]
                 [lein-dotenv "RELEASE"]]
  :min-lein-version "2.0.0"
  ;; :jvm-opts     ^:replace ["-Xss512k" "-Xms80m" "-Xmx100m"
  ;;                          "-XX:MaxMetaspaceSize=100m"
  ;;                          "-XX:MaxDirectMemorySize=40m"]
  :ring {:handler clj-rpbot.handler/app}
  :uberjar-name "server.jar"
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]]
                   :plugins [[lein-ring "0.9.6"]]}})
