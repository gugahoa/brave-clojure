(ns core-functions.core)

(def filename "suspects.csv")
(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "Convert CSV into rows of colums"
  [csv]
  (map #(clojure.string/split % #",") (clojure.string/split-lines csv)))

(defn map-convert
  "Use conversions map to apply conversion to any map"
  [m]
  (map #(merge-with (fn [a, b] (b a)) % conversions) m))

(defn mapify
  "Returns a seq of maps using vamp-keys"
  [rows]
  (map-convert (map (partial zipmap vamp-keys) rows)))

(defn glitter-filter
  "Filter the records, looking for records with a glitter-index higher than minimum-glitter"
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

; 1. Turn the result of your glitter filter into a list of nam
(defn glitter-filter-name
  "Return a sequence of the names returned by glitter-filter"
  [minimum-glitter records]
  (map :name (glitter-filter minimum-glitter records)))

; 2. Write a function, append, which will append a new suspect to your list of suspects.
(defn append
  "Append a new suspect to the records list"
  [new-suspect records]
  (cons new-suspect records))

; 3. Write a function, validate, which will check that :name and :glitter-index are present when you append. The validate function should accept two arguments: a map of keywords to validating functions, similar to conversions, and the record to be validated.
(defn validate
  "Check if record contains :name and :glitter-index, and apply validators to it"
  [record validators]
  (if (not (and (contains? record :glitter-index) (contains? record :name)))
    nil
    (merge-with (fn [a, b] (b a)) record validators)))

; 4. Write a function that will take your list of maps and convert it back to a CSV string. You’ll need to use the clojure.string/join function.
(defn map-to-csv
  "Convert records to csv"
  [records]
  (clojure.string/join "\n" (map (partial clojure.string/join ",") (map vals records))))
