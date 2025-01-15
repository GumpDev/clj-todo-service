(ns clj-todo-service.operations.find-todos
  (:require [clj-todo-service.services.database :refer [q]]
            [clj-todo-service.schemas.todo-schema :as schema]
            [schema.core :as s]))

(s/defn find-todos :- [schema/ToDo]
  "Find all ToDos from the database"
  []
  (q
   '[:find ?id ?title ?description ?status
     :keys id title description status
     :where
     [?e :todo/id ?id]
     [?e :todo/title ?title]
     [?e :todo/description ?description]
     [?e :todo/status ?status]
     (not [?e :todo/status 2])] []))
