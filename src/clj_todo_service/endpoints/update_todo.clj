(ns clj-todo-service.endpoints.update-todo
  (:require [clj-todo-service.utils :refer [get-body response]]
            [clj-todo-service.operations.upsert-todo :refer [upsert-todo!]]
            [schema.core :as s]))

(s/defn update-todo
  "Update ToDo endpoint"
  [request]
  (let [body (get-body request)
        id (get-in request [:path-params :id])]
    (try
      (response 202 (upsert-todo! id body))
      (catch Exception e (response 404 {:error (.getMessage e)})))))

