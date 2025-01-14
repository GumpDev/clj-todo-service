(ns clj-todo-service.endpoints.create-todo
  (:require [clj-todo-service.utils :as utils]
            [clj-todo-service.operations.insert-todo :as operation]
            [clojure.data.json :as json]
            [schema.core :as s])
  (:import (java.util UUID)))

(s/defschema CreateTodoRequest
  {:body s/Any})
(s/defschema CreateTodoResponse
  {:status {:schema s/Int :require true}
   :body {:schema s/Str :require true}})

(s/defn create-todo :- CreateTodoResponse
  "Create a new ToDo endpoint"
  [request :- CreateTodoRequest]
  (let [body (utils/get-body request)
        todo { :id (.toString (UUID/randomUUID))
          :title (get body :title)
          :description (get body :description)
          :status 0}]
    (do
      (operation/insert-todo! todo)
      { :status 201
        :body (json/write-str todo)})))
