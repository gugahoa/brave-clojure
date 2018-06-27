(ns do-things.core)

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  "Replace the beginning of :name part with right, if :name part starts with left"
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-"),
   :size (:size part)})

(defn symmetrize-body-parts
  "Take asymmetric body parts and symmetrize it"
  [asym-body-parts]
  (into asym-body-parts
    (set (map matching-part asym-body-parts))))

(defn hit
  "Randomly hit a body part"
  [asym-body-parts]
  (let [sym-body-part (symmetrize-body-parts asym-body-parts)
        acc-body-size (reduce + (map :size sym-body-part))
        target (rand acc-body-size)]
    (loop [[part & remaining] sym-body-part
           acc-target (:size part)]
      (if (> acc-target target)
        part
        (recur remaining (+ acc-target (:size (first remaining))))))))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

; Exercises:

; 2. Write a function that takes a number and adds 100 to it.
(defn add100
  [num]
  (+ num 100))

; 3. Write a function, dec-maker, that works exactly like the function inc-maker except with subtraction:
(defn dec-maker
  [n]
  #(- % n))

; 4. Write a function, mapset, that works like map except the return value is a set: 
(defn mapset
  [f collection]
  (set (map f collection)))
