(ns clj-todo-service.endpoints.get-todos
  (:require [clj-todo-service.operations.find-todos :as find-todos]
            [schema.core :as s]
            [cheshire.core :as cheshire]))

(s/defschema GetTodoResponse {:status {:schema s/Int :require true}
                              :body {:schema s/Str :require true}})

(s/defn get-todos :- GetTodoResponse
  "Get all ToDos endpoint"
  [request]
  {:status 200
   :body (cheshire/generate-string (find-todos/find-todos))})
