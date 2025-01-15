(ns clj-todo-service.utils_test
  (:require [clojure.test :refer :all]
            [clj-todo-service.utils :refer :all]
            [clj-todo-service.test_utils :refer [mock-request]]))


(def invalid-test-request
  {:b "teste"})

(deftest get-body-test
  (testing "get-body"
    (testing "with valid body"
      (is (=
           (get-body (mock-request {:body {:name "Joao" :balance 100.5 :active true}}))
                     {:name "Joao"
                      :balance 100.5
                      :active true})))
    (testing "with invalid body"
      (is (=
           (get-body invalid-test-request)
           nil)))))

(deftest error-test
  (testing "response"
    (testing "with valid code and messages"
      (are [x y] (= x y)
        (response 404 {:error "Not Found"})
        {:status 404, :body {:error "Not Found"}})
        (response 400 {:error "Bad request"})
        {:status 400, :body {:error "Bad request"}}
        (response 200 {:success true})
        {:status 200, :body {:success true}})))

(run-tests)