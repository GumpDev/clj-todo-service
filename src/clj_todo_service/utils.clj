(ns clj-todo-service.utils
  (:require [cheshire.core :as cheshire]
            [clojure.data.json :as json]))

(defn get-body [request]
  (let [body (get request :body)]
    (if body
      (cheshire/parse-string (slurp body) true)
      request)))

(defn error [code msg]
  { :status code
    :body (json/write-str {:msg msg})})