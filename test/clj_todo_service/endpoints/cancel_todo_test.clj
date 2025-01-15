(ns clj-todo-service.endpoints.cancel-todo-test
  (:require [clojure.test :refer :all]
            [clj-todo-service.test_utils :refer [mock-request]]
            [clj-todo-service.endpoints.cancel-todo :refer [cancel-todo]]
            [clj-todo-service.operations.find-todo :refer [find-todo]]
            [clj-todo-service.operations.insert-todo :refer [insert-todo!]]))

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
    (with-redefs [find-todo mock-find-todo
                  insert-todo! mock-insert-todo]
      (testing "with valid todo and status"
        (is
          (=
            (cancel-todo (mock-request {:path-params {:id "test-id"}}))
            {:body   {:id "test-id"
                      :title "test-name"
                      :description "test-description"
                      :status 2}
             :status 202}))
        )
      (testing "with valid todo and invalid status"
        (is
         (=
          (cancel-todo (mock-request {:path-params {:id "test-id2"}}))
          {:body   {:error "ToDo can't be cancelled because is already cancelled!"}
           :status 400}))
        )
      (testing "with invalid todo and status"
        (is
         (=
          (cancel-todo (mock-request {:path-params {:id "test-i"}}))
          {:body {:error "ToDo Not Found!"}
           :status 404}))
        ))))

(run-tests)