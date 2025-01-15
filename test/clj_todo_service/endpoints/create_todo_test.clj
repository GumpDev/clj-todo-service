(ns clj-todo-service.endpoints.create-todo-test
  (:require [clojure.test :refer :all]
            [clj-todo-service.test_utils :refer [mock-request]]
            [clj-todo-service.endpoints.create-todo :refer [create-todo]]
            [clj-todo-service.operations.insert-todo :refer [insert-todo!]]))

(def mock-body {:title "test-name"
                :description "test-description"})
(def mock-body2 {:title "test-name"})

(defn mock-insert-todo [x] nil)

(deftest create-todo-test
  (testing "create-todo"
    (with-redefs [insert-todo! mock-insert-todo]
      (testing "with valid body"
         (let [request (mock-request {:body mock-body})
               response (create-todo request)
               status (:status response)
               body (:body response)]
           (are [x y] (= x y)
                      201 status
                      "test-name" (:title body)
                      "test-description" (:description body)
                      0 (:status body))))
      (testing "with valid body and without description"
        (let [request (mock-request {:body mock-body2})
              response (create-todo request)
              status (:status response)
              body (:body response)]
          (are [x y] (= x y)
                     status 201
                     "test-name" (:title body)
                     nil (:description body)
                     0 (:status body))))
      (testing "with invalid body"
        (let [request (mock-request {:body {}})
              response (create-todo request)
              status (:status response)
              body (:body response)]
          (are [x y] (= x y)
                     400 status
                     "Missing title" (:error body)))))))

(run-tests)