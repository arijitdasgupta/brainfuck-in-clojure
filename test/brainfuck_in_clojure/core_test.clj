(ns brainfuck-in-clojure.core-test
  (:require [clojure.test :refer :all]
            [brainfuck-in-clojure.core :refer :all]))

(deftest split-tape-test
  (testing "Split a tape into arrays"
    (is (=
      (split-tape "-+>")
      [\- \+ \>]))))

(deftest initiate-state-test
  (testing "State initiation"
    (is (let [code "+++"] (=
      (:code (initiate-state code))
      (split-tape code))))
    (is (=
      (:pointer (initiate-state "+++"))
      0))
    (is (=
      (:ram (initiate-state "+++"))
      (loop [array [] n 0] (if (= n ram-size) array (recur (conj array 0) (inc n))))))
    (is (=
      (:head (initiate-state "+++"))
      0))))

(deftest add-one-test
  (testing "Adding one to the current pointer"
    (is
      (let [state {
        :ram [0 1 0]
        :pointer 1
        :head 0
        :code [\- \-]
      }] (=
        (:ram (add-one state io))
        [0 2 0])))))

(deftest subtract-one-test
  (testing "Adding one to the current pointer"
    (is
      (let [state {
        :ram [0 1 0]
        :pointer 1
        :head 0
        :code [\- \-]
      }] (=
        (:ram (subtract-one state io))
        [0 0 0])))))

(deftest increment-test
  (testing "Increment of pointer"
   (is
     (let [state {
       :pointer 0
       }]
       (=
         (:pointer (increment-pointer state io)
         1))))))

(deftest decrement-test
 (testing "Decrement of pointer"
  (is
    (let [state {
      :pointer 1
      }]
      (=
        (:pointer (decrement-pointer state io)
        0))))))

(deftest goto-square-test
  (testing "Goto closing bracket"
    (is
      (let [state {
        :pointer 0
        :head 0
        :code [\[ \- \- \]]
        :ram [0 0 0 0]
        }]
        (=
          (:head (goto-square state io))
          3)))
    (is
      (let [state {
        :pointer 0
        :head 0
        :code [\[ \- \- \]]
        :ram [1 0 0 0]
        }]
        (=
          (:head (goto-square state io))
          0)))))

(deftest goto-next-test
  (testing "Goto next statement"
    (is
      (let [state {
        :pointer 0
        :head 0
        :code [\[ \- \- \]]
        :ram [1 0 0 0]
        }]
        (=
          (:head (goto-next state io)
          0))))))

(deftest tape-machine-test
  (testing "Tape machine"
    (is
      (=
        (let [state {
          :pointer 0
          :head 0
          :code [\+ \M \+ \M \- \M \> \M]
          :ram [0 0 0 0 0]
          }]
          (:ram (tape-machine state io))
          [1 0 0 0 0])))))
