(ns clj-rpbot.character)

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

(defn show [name]
  (let [character (get characters name)]
    (present-description character)))
