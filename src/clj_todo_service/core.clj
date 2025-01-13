(ns clj-todo-service.core
  (:require [datomic.client.api :as d]))

(def client (d/client {:server-type :datomic-local
                       :storage-dir :mem
                       :system "dev"}))

(d/create-database client {:db-name "teste"})
(def conn (d/connect client {:db-name "teste"}))


(println "Started App")