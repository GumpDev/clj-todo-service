(ns clj-todo-service.endpoints.get-todo
  (:require [clj-todo-service.operations.find-todo :refer [find-todo]]
            [schema.core :as s]
            [clj-todo-service.utils :refer [response]]))

(s/defn get-todo
  "Get one ToDo endpoint"
  [request]
  (let [id (get-in request [:path-params :id])
        todo (find-todo id)]
    (cond
      (= todo nil) (response 404 {:error "ToDo not Found"})
      :else
      (response 200 todo))))
