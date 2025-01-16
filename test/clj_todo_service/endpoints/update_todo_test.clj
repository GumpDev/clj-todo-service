(ns clj-todo-service.endpoints.update-todo-test
  (:require [clojure.test :refer :all]
            [clj-todo-service.operations.upsert-todo :refer [upsert-todo!]]
            [clj-todo-service.endpoints.update-todo :refer [update-todo]]
            [clj-todo-service.test_utils :refer [mock-request]]))


(def mock-ToDo {:id "test-id"
                :title "test-name"
                :description "test-description"
                :status 0})

(defn mock-upsert-todo [n body]
  (cond
    (= n "test-id") (merge mock-ToDo body)
    :else (throw (Exception. "ToDo Not Found"))))


(deftest get-todos-test
  (testing "get-todos"
    (with-redefs [upsert-todo! mock-upsert-todo]
      (testing "with valid body and id"
        (is (=
             {:body   {:description "test-desc"
                       :id          "test-id"
                       :status      0
                       :title       "test-name 2"}
              :status 202}
             (update-todo (mock-request
                           {:path-params {:id "test-id"}
                            :body {:title "test-name 2"
                                   :description "test-desc"}})))))
      (testing "with invalid body and id"
        (is (=
             {:body   {:error "ToDo Not Found"}
              :status 404}
             (update-todo (mock-request
                           {:path-params {:id "test-id2"}
                            :body {:title "test-name 2"
                                   :description "test-desc"}}))))))))

(run-tests)