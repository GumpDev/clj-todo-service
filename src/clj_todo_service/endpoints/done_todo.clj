(ns clj-todo-service.endpoints.done-todo
  (:require [clj-todo-service.operations.upsert-todo :refer [upsert-todo!]]
            [clj-todo-service.operations.find-todo :refer [find-todo]]
            [clj-todo-service.utils :refer [response]]
            [schema.core :as s]))

(s/defn done-todo
  "Done ToDo status endpoint"
  [request]
  (let [id     (get-in request [:path-params :id])
        status 1
        todo   (find-todo id)]
    (cond
      (nil? todo) (response 404 {:error "ToDo Not Found!"})
      (not (= (:status todo) 0)) (response 400 {:error "ToDo can't be done!"})
      :else
      (do
        (upsert-todo! id {:status status})
        (response 202 (assoc todo :status 1))))))
