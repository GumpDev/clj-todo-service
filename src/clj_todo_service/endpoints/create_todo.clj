(ns clj-todo-service.endpoints.create-todo
  (:require [clj-todo-service.services.database :as db]
            [clj-todo-service.utils :as utils]
            [datomic.client.api :as d]
            [clojure.data.json :as json])
  (:import (java.util UUID)))

(defn insert-todo! [body]
  (let [id (get body :id)
        title (get body :title)
        description (get body :description)
        status (get body :status)]
    (d/transact db/conn
                {:tx-data [{:todo/id id
                            :todo/title title
                            :todo/description description
                            :todo/status status}]})))

(defn create-todo [request]
  (let [body (utils/get-body request)
        todo { :id (.toString (UUID/randomUUID))
          :title (get body :title)
          :description (get body :description)
          :status 0}]
    (do
      (insert-todo! todo)
      { :status 200
        :body (json/write-str todo)})))
