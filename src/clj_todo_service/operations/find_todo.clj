(ns clj-todo-service.operations.find-todo
  (:require [clj-todo-service.services.database :as db]
            [clj-todo-service.schemas.todo-schema :as schema]
            [schema.core :as s]))

(s/defn find-todo :- schema/ToDo
  "Find one ToDo from the database"
  [id :- s/Str]
  (first (db/q
   '[:find ?id ?title ?description ?status
     :keys id title description status
     :in $ ?id
     :where
     [?e :todo/id ?id]
     [?e :todo/title ?title]
     [?e :todo/description ?description]
     [?e :todo/status ?status]]
   id)))
