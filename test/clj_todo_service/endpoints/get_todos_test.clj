(ns clj-todo-service.endpoints.get-todos-test
  (:require [clojure.test :refer :all]
            [clj-todo-service.endpoints.get-todos :refer [get-todos]]
            [clj-todo-service.operations.find-todos :refer [find-todos]]))

(def mock-ToDos [{:id "test-id"
                  :title "test-name"
                  :description "test-description"
                  :status 0}
                 {:id "test-id2"
                  :title "test-name"
                  :description "test-description"
                  :status 1}])

(defn mock-find-todos [] mock-ToDos)

(deftest get-todos-test
  (testing "get-todos"
    (with-redefs [find-todos mock-find-todos]
      (is (=
           {:status 200
            :body mock-ToDos}
           (get-todos {}))))))

(run-tests)