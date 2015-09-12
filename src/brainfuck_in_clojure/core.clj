; Author: Arijit Dasgupta (@rivalslayer)
; Brainfuck in Clojure

(ns brainfuck-in-clojure.core
  (:gen-class))

;
; State structure
; {
; :code array or string symbols literals e.g. [\. \+]
; :head Number/Integer
; :ram array of Number/Integer
; :pointer Number/Integer
; }
;
; + increment the value at pointer
; - decrement the value at pointer
; > increate the pointer value
; < decrate the pointer value
; . out the current data
; , in the next line
; [ if value at pointer is zero go to the next ]
;
;


(def ram-size 30)

(defn split-tape
  [code]
  (into [] (seq code)))

(defn initiate-state
  [code]
  {
    :code (split-tape code)
    :head 0
    :ram (loop [array [] n 0]
      (if (= n ram-size) array (recur (conj array 0) (inc n))))
    :pointer 0
    })

(def io
  {
    :output (fn [x] (println x))
    :input (fn [] (read-string (read-line)))
    })

(defn add-one
  [state io]
  (let [current-pointer (:pointer state) current-ram (:ram state) current-value (get current-ram current-pointer)]
    (assoc state :ram (assoc current-ram current-pointer (inc current-value)))))

(defn subtract-one
  [state io]
  (let [current-pointer (:pointer state) current-ram (:ram state) current-value (get current-ram current-pointer)]
    (assoc state :ram (assoc current-ram current-pointer (dec current-value)))))

(defn increment-pointer
  [state io]
  (let [current-pointer (:pointer state)]
    (assoc state :pointer (inc current-pointer))))

(defn decrement-pointer
  [state io]
  (let [current-pointer (:pointer state)]
    (assoc state :pointer (dec current-pointer))))

(defn goto-square
  [state io]
  (let [code (:code state) current-value (get (:ram state) (:pointer state))]
    (if (<= current-value 0)
      (loop [head (:head state)]
        (if (or (= (get code head) \]) (= head (count code)))
          (assoc state :head head)
          (recur (inc head))))
      state)))

(defn goto-back-square
  [state io]
  (let [code (:code state)]
    (loop [head (:head state)]
      (if (= (get code head) \[)
        (assoc state :head head)
        (recur (dec head))))))

(defn output
  [state io]
  (let [current-value (get (:ram state) (:pointer state))]
    (do
      ((:output io) current-value)
      state)))

(defn input
  [state io]
  (let [current-pointer (:pointer state) current-ram (:ram state)]
    (assoc state :ram (assoc current-ram current-pointer ((:input io))))))

(defn print-state
  [state io]
  (do
    ((:output io) state)
    state))

(defn check-instruction
  [state]
  (get (:code state) (:head state)))

(def funk-map {
  \+ add-one
  \- subtract-one
  \> increment-pointer
  \< decrement-pointer
  \[ goto-square
  \] goto-back-square
  \. output
  \, input
  \M print-state})

(defn tape-machine
  [initial-state io]
  (loop [state initial-state]
    (if (>= (:head state) (count (:code state)))
      nil
      (let [funk (get funk-map (check-instruction state))
            new-state (funk state io)
            new-head (:head new-state)]
        (if (>= new-head (:head state))
          (recur (assoc new-state :head (inc new-head)))
          (recur new-state))))))

(defn start-brainfuck
  [code]
  (let [new-state (initiate-state code)]
    (tape-machine (initiate-state code) io)))

(defn -main
  "Start the brainfuck interpreter"
  []
  (do
    (println "Brainfuck Interpreter v0.1")
    (println "by Arijit Dasgupta (@rivalslayer)")
    (println "RAM size: ")
    (println ram-size)
    (println "RAM is [0 0 0 0 ...].")
    (println "Instruction pointer is 0.")
    (println "Current pointer to RAM is 0.")
    (println "Brainfuck: https://en.wikipedia.org/wiki/Brainfuck")
    (println "Debug syntax M: Prints out whole state of the machine.")
    (println "Operates only on integer numbers. For input, only accepts integers.")
    (println "Caught errors are uncaught Java/Clojure errors. ")
    (println "Please type out your brainfuck code [RETURN runs the code]")
    (start-brainfuck (read-line))
    (println "Good bye!")))
