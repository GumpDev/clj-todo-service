(ns clj-todo-service.services.database
  (:require [datomic.client.api :as d]
            [clj-todo-service.schemas.todo-schema :as ts]))

(def cfg (d/client {:server-type :datomic-local
                    :storage-dir "/Users/gustavo.paes/Documents/Datomic"
                    :system "dev"}))

(d/create-database cfg {:db-name "todo"})

(def conn (d/connect cfg {:db-name "todo"}))

(def db (d/db conn))

(d/transact conn {:tx-data ts/todo-schema })

