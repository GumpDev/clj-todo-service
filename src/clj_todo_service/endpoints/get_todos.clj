(ns clj-todo-service.endpoints.get-todos
  (:require [datomic.client.api :as d]
            [cheshire.core :as cheshire]
            [clj-todo-service.services.database :as db]))

(defn find-todos []
  (d/q
    '[:find ?id ?title ?description ?status
      :where
      [?e :todo/id ?id]
      [?e :todo/title ?title]
      [?e :todo/description ?description]
      [?e :todo/status ?status]]
    db/db))

(defn get-todos [request]
  {:status 200
   :body (cheshire/generate-string (list (find-todos)))})

(d/q
  '[:find ?id ?title ?description ?status
    :where
    [?e :todo/id ?id]
    [?e :todo/title ?title]
    [?e :todo/description ?description]
    [?e :todo/status ?status]]
  db/db)
