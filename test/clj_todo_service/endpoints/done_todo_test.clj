(ns clj-todo-service.endpoints.done-todo-test
  (:require [clojure.test :refer :all]
            [clj-todo-service.test_utils :as utils]
            [clj-todo-service.endpoints.done-todo :as done-todo]))

(def mock-ToDo {:id "test-id"
                :title "test-name"
                :description "test-description"
                :status 0})
(def mock-ToDo2 {:id "test-id2"
                 :title "test-name"
                 :description "test-description"
                 :status 1})
(def mock-ToDo3 {:id "test-id3"
                 :title "test-name"
                 :description "test-description"
                 :status 2})

(defn mock-find-todo [n]
  (cond
    (= n "test-id") mock-ToDo
    (= n "test-id2") mock-ToDo2
    (= n "test-id3") mock-ToDo3
    :else nil))

(defn mock-insert-todo [x] nil)

(deftest done-todo-test
  (testing "done-todo"
    (with-redefs [clj-todo-service.operations.find-todo/find-todo mock-find-todo
                  clj-todo-service.operations.insert-todo/insert-todo! mock-insert-todo]
      (testing "with valid todo and status"
        (is
         (=
          (done-todo/done-todo (utils/mock-request {:path-params {:id "test-id"}}))
          {:body   "{\"id\":\"test-id\",\"title\":\"test-name\",\"description\":\"test-description\",\"status\":1}"
           :status 202}))
        )
      (testing "with valid todo and done status"
        (is
         (=
          (done-todo/done-todo (utils/mock-request {:path-params {:id "test-id2"}}))
          {:body   "{\"msg\":\"ToDo can't be done!\"}"
           :status 400}))
        )
      (testing "with invalid todo"
        (is
         (=
          (done-todo/done-todo (utils/mock-request {:path-params {:id "test-i"}}))
          {:body   "{\"msg\":\"ToDo Not Found!\"}"
           :status 404}))
        )
      (testing "with cancelled status"
        (is
         (=
          (done-todo/done-todo (utils/mock-request {:path-params {:id "test-id3"}}))
          {:body   "{\"msg\":\"ToDo can't be done!\"}"
           :status 400}))
        ))))

(run-tests)

