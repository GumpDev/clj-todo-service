(ns clj-todo-service.endpoints.cancel-todo
  (:require [clj-todo-service.operations.upsert-todo :refer [upsert-todo!]]
            [clj-todo-service.operations.find-todo :refer [find-todo]]
            [clj-todo-service.utils :refer [response]]
            [schema.core :as s]))

(s/defn cancel-todo
  "Cancel ToDo status endpoint"
  [request]
  (let [id     (get-in request [:path-params :id])
        status 2
        todo   (find-todo id)]
    (cond
      (nil? todo) (response 404 {:error "ToDo Not Found!"})
      (= (:status todo) 2) (response 400 {:error "ToDo can't be cancelled because is already cancelled!"})

      :else
      (do
        (upsert-todo! id {:status status})
        (response 202 (assoc todo :status status))))))