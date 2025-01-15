(ns clj-todo-service.utils
  (:require [clojure.edn :as edn]))

(defn get-body [request]
  (let [body (:body request)]
    (if body
      (edn/read-string (slurp body))
      nil)))

(defn response [code body]
  { :status code
    :body body})