(ns clj-todo-service.endpoints.update-todo
  (:require [clj-todo-service.utils :as utils]
            [clj-todo-service.operations.insert-todo :as operation]
            [clj-todo-service.operations.find-todo :as find-todo]
            [clojure.data.json :as json]
            [schema.core :as s]))

(s/defschema UpdateTodoResponse
  {:status {:schema s/Int :require true}
   :body {:schema s/Str :require true}})

(s/defn update-todo :- UpdateTodoResponse
  "Update ToDo endpoint"
  [request]
  (let [body (utils/get-body request)
        id (get-in request [:path-params :id])
        title (:title body)
        description (:description body)
        todo (find-todo/find-todo id)]
    (do
      (operation/insert-todo!
       {  :id id
        :title title
        :description description
        :status (:status todo) })
      { :status 202
       :body (json/write-str todo)})))


