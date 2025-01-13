(ns clj-todo-service.utils
  (:require [cheshire.core :as cheshire]))

(defn get-body [request]
  (let [body (get request :body)]
    (if body
      (cheshire/parse-string (slurp body) true)
      request)))
