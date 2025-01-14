(ns clj-todo-service.endpoints.done-todo
  (:require [clj-todo-service.operations.insert-todo :as operation]
            [clj-todo-service.operations.find-todo :as find-todo]
            [clj-todo-service.utils :as utils]
            [clojure.data.json :as json]
            [schema.core :as s]))

(s/defschema UpdateStatusTodoResponse
  {:status {:schema s/Int :require true}
   :body {:schema s/Str :require true}})

(s/defn done-todo :- UpdateStatusTodoResponse
  "Done ToDo status endpoint"
  [request]
  (let [id     (get-in request [:path-params :id])
        status 1
        todo   (find-todo/find-todo id)]
    (cond
      (nil? todo) (utils/error 404 "ToDo Not Found!")
      (not (= (:status todo) 0)) (utils/error 400 "ToDo can't be done!")

      :else
      (do
        (operation/insert-todo!
         {  :id id
          :title (:title todo)
          :description (:description todo)
          :status status })
        { :status 202
          :body (json/write-str todo)})
    )
  ))
