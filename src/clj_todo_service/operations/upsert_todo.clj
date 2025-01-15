(ns clj-todo-service.operations.upsert-todo
  (:require [clj-todo-service.operations.find-todo :refer [find-todo]]
            [clj-todo-service.services.database :refer [transact]]
            [schema.core :as s]))

(s/defn upsert-todo!
  "Update ToDo to the database"
  [id changes]
  (let [todo (find-todo id)]
    (if (= todo nil)
      (throw (Exception. "ToDo Not Found"))
      (let [data (merge todo changes)]
        (do
          (transact [{  :todo/id id
                        :todo/title (:title data)
                        :todo/description (:description data)
                        :todo/status (:status data)}])
          data)))))

