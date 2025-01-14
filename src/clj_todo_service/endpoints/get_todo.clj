(ns clj-todo-service.endpoints.get-todo
  (:require [clj-todo-service.operations.find-todo :as find-todo]
            [schema.core :as s]
            [cheshire.core :as cheshire]))

(s/defschema GetTodoResponse {:status {:schema s/Int :require true}
                              :body {:schema s/Str :require true}})

(s/defn get-todo :- GetTodoResponse
  "Get one ToDo endpoint"
  [request]
  (let [id (get-in request [:path-params :id])]
    { :status 200
      :body (cheshire/generate-string (find-todo/find-todo id))}))
