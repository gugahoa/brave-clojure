(ns armstrong-numbers)

(defn exp [x n]
  (reduce * (repeat n x)))

(defn digits
  "Take a number and return a list with it's digits"
  [n]
  (loop [num n
         digits []]
    (if (= num 0)
      digits
      (recur (int (/ num 10)) (cons (mod num 10) digits)))))

(defn armstrong? [n] ;; <- arglist goes here
  ;; your code goes here
  (let [digits-list (digits n)
        digits-size (count digits-list)]
    (= n (reduce #(+ %1 (exp %2 digits-size)) 0 digits-list)))
  )
