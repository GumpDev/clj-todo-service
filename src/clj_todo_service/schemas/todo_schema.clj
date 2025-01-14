(ns clj-todo-service.schemas.todo-schema
  (:require [schema.core :as s]))

(def status-types #{:TODO
                    :DONE
                    :CANCELLED})
(s/defschema TodoStatus (apply s/enum status-types))

(s/defschema ToDo {:id { :schema s/Uuid :require true }
                   :title { :schema s/Str :require true }
                   :description { :schema s/Str }
                   :status { :schema TodoStatus :require true}})