(ns clj-todo-service.entities.todo-entity)

(def todo-entity [{:db/ident :todo/id
                   :db/valueType :db.type/string
                   :db/cardinality :db.cardinality/one
                   :db/unique :db.unique/identity
                   :db/doc "The title of the todo"}

                  {:db/ident :todo/title
                   :db/valueType :db.type/string
                   :db/cardinality :db.cardinality/one
                   :db/doc "The title of the todo"}

                  {:db/ident :todo/description
                   :db/valueType :db.type/string
                   :db/cardinality :db.cardinality/one
                   :db/doc "The description of the todo"}

                  {:db/ident :todo/status
                   :db/valueType :db.type/long
                   :db/cardinality :db.cardinality/one
                   :db/doc "The status of the todo"}])
