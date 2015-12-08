(ns clj-rpbot.character
  (:require [rethinkdb.query :as r]))

(with-open [conn (r/connect :host "127.0.0.1" :port 28015 :db "test")]
  (r/run (r/db-create "test") conn)

  (defn create-characters-table
    (-> (r/db "test")
        (r/table-create "characters")
        (r/run conn)))

  (-> (r/db "test")
      (r/table "characters")
      (r/index-create "user" (r/fn [row]
                               (r/get-field row :user)))
      (r/run conn))

  (-> (r/db "test")
      (r/table "characters")
      (r/insert [{:name "Alice"
                  :user "l"
                  :description "is a cute redhead"}
                 {:name "Andrea"
                  :user "a"
                  :description "is a cute pixie"}
                 {:name "Kale"
                  :user "t"
                  :description "is a cute cyborg boy"}
                 {:name "Kylie"
                  :user "t"
                  :description "is a cute cyborg girl"}])
      (r/run conn))

  (-> (r/db "test")
      (r/table "characters")
      (r/get-all ["t"] {:index "user"})
      (r/filter (r/fn [row]
                  (r/eq "Kylie" (r/get-field row "name"))))
      (r/run conn))
  ;;
  ;; (def alice {:name "Alice"
  ;;             :user "l"
  ;;             :description "is a cute redhead"})
  ;; (def andrea {:name "Andrea"
  ;;              :user "a"
  ;;              :description "is a cute pixie"})
  ;; (def kale {:name "Kale"
  ;;            :user "t"
  ;;            :description "is a cute cyborg boy"})
  ;; (def kylie {:name "Kylie"
  ;;             :user "t"
  ;;             :description "is a cute cyborg girl"})
  ;;
  ;; (def characters {"Alice" alice
  ;;                  "Andrea" andrea
  ;;                  "Kale" kale
  ;;                  "Kylie" kylie})
  ;;
  ;; (defn present-description [character]
  ;;   (let [name (get character :name)
  ;;         description (get character :description)]
  ;;     (str name " " description)))
  ;;
  ;; (defn show [name]
  ;;   (let [character (get characters name)]
  ;;     (present-description character)))
