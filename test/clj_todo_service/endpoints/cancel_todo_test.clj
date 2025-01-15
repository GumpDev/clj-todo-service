(ns clj-todo-service.endpoints.cancel-todo-test
  (:require [clojure.test :refer :all]
            [clj-todo-service.test_utils :as utils]
            [clj-todo-service.endpoints.cancel-todo :as cancel-todo]))

(def mock-ToDo {:id "test-id"
                :title "test-name"
                :description "test-description"
                :status 0})
(def mock-ToDo2 {:id "test-id2"
                :title "test-name"
                :description "test-description"
                :status 2})

(defn mock-find-todo [n]
  (cond
    (= n "test-id") mock-ToDo
    (= n "test-id2") mock-ToDo2
    :else nil))

(defn mock-insert-todo [x] nil)

(deftest cancel-todo-test
  (testing "cancel-todo"
    (with-redefs [clj-todo-service.operations.find-todo/find-todo mock-find-todo
                  clj-todo-service.operations.insert-todo/insert-todo! mock-insert-todo]
      (testing "with valid todo and status"
        (is
          (=
            (cancel-todo/cancel-todo (utils/mock-request {:path-params {:id "test-id"}}))
            {:body   "{\"id\":\"test-id\",\"title\":\"test-name\",\"description\":\"test-description\",\"status\":2}"
             :status 202}))
        )
      (testing "with valid todo and invalid status"
        (is
         (=
          (cancel-todo/cancel-todo (utils/mock-request {:path-params {:id "test-id2"}}))
          {:body   "{\"msg\":\"ToDo can't be cancelled because is already cancelled!\"}"
           :status 400}))
        )
      (testing "with invalid todo and status"
        (is
         (=
          (cancel-todo/cancel-todo (utils/mock-request {:path-params {:id "test-i"}}))
          {:body   "{\"msg\":\"ToDo Not Found!\"}"
           :status 404}))
        ))))

(run-tests)