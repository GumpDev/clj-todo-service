(ns clj-todo-service.services.database
  (:require [datomic.client.api :as d]
            [clj-todo-service.entities.todo-entity :as todo-entity]))

(def cfg (d/client {:server-type :datomic-local
                    :system "dev"}))

(d/create-database cfg {:db-name "todo"})

(def conn (d/connect cfg {:db-name "todo"}))

(d/transact conn {:tx-data todo-entity/todo-entity })

(defn transact [query]
  (d/transact conn
               {:tx-data query}))

(defn q [query x] (d/q query (d/db conn) x))