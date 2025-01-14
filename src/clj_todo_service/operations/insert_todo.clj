(ns clj-todo-service.operations.insert-todo
  (:require [clj-todo-service.schemas.todo-schema :as schema]
            [clj-todo-service.services.database :as db]
            [schema.core :as s]))

(s/defn insert-todo!
  "Insert a new ToDo to the database"
  [todo :- schema/ToDo]
  (let [id (:id todo)
        title (:title todo)
        description (:description todo)
        status (:status todo)]
    (db/transact [{:todo/id id
                   :todo/title title
                   :todo/description description
                   :todo/status status}])))
