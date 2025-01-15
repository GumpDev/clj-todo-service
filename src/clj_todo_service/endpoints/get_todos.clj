(ns clj-todo-service.endpoints.get-todos
  (:require [clj-todo-service.operations.find-todos :refer [find-todos]]
            [schema.core :as s]))

(s/defn get-todos
  "Get all ToDos endpoint"
  [request]
  (let [todos (find-todos)]
    { :status 200
      :body todos}))
