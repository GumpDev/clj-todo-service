(ns clj-todo-service.endpoints.cancel-todo
  (:require [clj-todo-service.operations.insert-todo :as operation]
            [clj-todo-service.operations.find-todo :as find-todo]
            [clj-todo-service.utils :as utils]
            [clojure.data.json :as json]
            [schema.core :as s]))

(s/defschema UpdateStatusTodoResponse
  {:status {:schema s/Int :require true}
   :body {:schema s/Str :require true}})

(s/defn cancel-todo :- UpdateStatusTodoResponse
  "Cancel ToDo status endpoint"
  [request]
  (let [id     (get-in request [:path-params :id])
        status 2
        todo   (find-todo/find-todo id)]
    (cond
      (nil? todo) (utils/error 404 "ToDo Not Found!")
      (= (:status todo) 2) (utils/error 400 "ToDo can't be cancelled because is already cancelled!")

      :else
      (do
        (operation/insert-todo!
         {:id          id
          :title       (:title todo)
          :description (:description todo)
          :status      status})
        {:status 202
         :body   (json/write-str (assoc todo :status status))}))))