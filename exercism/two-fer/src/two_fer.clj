(ns two-fer)

(defn two-fer
  ([] (str "One for you, one for me."))
  ([someone] (format "One for %s, one for me." someone))
)
