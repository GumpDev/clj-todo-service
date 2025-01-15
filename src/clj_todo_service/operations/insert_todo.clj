(ns clj-todo-service.operations.insert-todo
  (:require [clj-todo-service.schemas.todo-schema :refer [ToDo]]
            [clj-todo-service.services.database :refer [transact]]
            [schema.core :as s]))

(s/defn insert-todo!
  "Insert a new ToDo to the database"
  [todo :- ToDo]
  (let [id (:id todo)
        title (:title todo)
        description (:description todo)
        status (:status todo)]
    (transact [{:todo/id id
                   :todo/title title
                   :todo/description description
                   :todo/status status}])))
