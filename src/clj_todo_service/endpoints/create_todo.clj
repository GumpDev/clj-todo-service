(ns clj-todo-service.endpoints.create-todo
  (:require [clj-todo-service.utils :refer [get-body response]]
            [clj-todo-service.operations.insert-todo :refer [insert-todo!]]
            [schema.core :as s])
  (:import (java.util UUID)))

(s/defn create-todo
  "Create a new ToDo endpoint"
  [request]
  (let [body (get-body request)
        todo {  :id (.toString (UUID/randomUUID))
                :title (get body :title)
                :description (get body :description)
                :status 0}]
      (cond
        (= (:title body) nil) (response 400 {:error "Missing title"})
        :else
        (do
          (insert-todo! todo)
          (response 201 todo)))))
