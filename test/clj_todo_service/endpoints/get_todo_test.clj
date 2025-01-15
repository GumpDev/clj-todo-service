(ns clj-todo-service.endpoints.get-todo-test
  (:require [clojure.test :refer :all]
            [clj-todo-service.test_utils :refer [mock-request]]
            [clj-todo-service.endpoints.get-todo :refer [get-todo]]
            [clj-todo-service.operations.find-todo :refer [find-todo]]))

(def mock-ToDo {:id "test-id"
                :title "test-name"
                :description "test-description"
                :status 0})
(def mock-ToDo2 {:id "test-id2"
                 :title "test-name"
                 :description "test-description"
                 :status 1})

(defn mock-find-todo [n]
  (cond
    (= n "test-id") mock-ToDo
    (= n "test-id2") mock-ToDo2
    :else nil))

(deftest get-todo-test
  (testing "get-todo"
    (with-redefs [find-todo mock-find-todo]
      (testing "with valid id"
        (are [x y] (= x y)
                   {:body   mock-ToDo
                    :status 200}
                   (get-todo (mock-request {:path-params {:id "test-id"}}))
                   {:body   mock-ToDo2
                    :status 200}
                   (get-todo (mock-request {:path-params {:id "test-id2"}}))))
      (testing "with invalid id"
        (are [x y] (= x y)
                   {:body   {:error "ToDo not Found"}
                    :status 404}
                   (get-todo (mock-request {:path-params {:id "invalid-id"}})))))))
