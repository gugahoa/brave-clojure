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
  (map #(merge-with (fn [a, b] (b a)) % conversions) csv-map))

(defn mapify
  "Returns a seq of maps using vamp-keys"
  [rows]
  (map-convert (map (partial zipmap vamp-keys) rows)))
