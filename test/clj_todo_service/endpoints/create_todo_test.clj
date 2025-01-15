(ns clj-todo-service.endpoints.create-todo-test
  (:require [clojure.test :refer :all]
            [clj-todo-service.test_utils :as utils]
            [clojure.data.json :as json]
            [cheshire.core :as cheshire]
            [clj-todo-service.endpoints.create-todo :as create-todo]))

(def mock-body (json/write-str {:title "test-name"
                :description "test-description"}))
(def mock-body2 (json/write-str {:title "test-name"}))

(defn mock-insert-todo [x] nil)

(deftest create-todo-test
  (testing "create-todo"
    (with-redefs [clj-todo-service.operations.insert-todo/insert-todo! mock-insert-todo]
      (testing "with valid body"
         (let [request (utils/mock-request {:body mock-body})
               response (create-todo/create-todo request)
               status (:status response)
               body (cheshire/parse-string (:body response) true)]
           (are [x y] (= x y)
                      status 201
                      (:title body) "test-name"
                      (:description body) "test-description"
                      (:status body) 0)))
      (testing "with valid body and without description"
        (let [request (utils/mock-request {:body mock-body2})
              response (create-todo/create-todo request)
              status (:status response)
              body (cheshire/parse-string (:body response) true)]
          (are [x y] (= x y)
                     status 201
                     (:title body) "test-name"
                     (:description body) nil
                     (:status body) 0)))
      (testing "with invalid body"
        (let [request (utils/mock-request {:body {}})
              response (create-todo/create-todo request)
              status (:status response)
              body (cheshire/parse-string (:body response) true)]
          (are [x y] (= x y)
                     status 400
                     (:msg body) "Missing title"))))))

(run-tests)