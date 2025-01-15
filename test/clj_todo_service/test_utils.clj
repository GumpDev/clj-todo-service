(ns clj-todo-service.test_utils
  (:require [clojure.java.io :as io]
            [clojure.test :refer :all])
  (:import [java.io ByteArrayInputStream]))

(defn mock-request [request]
  (let [body (get request :body "")
        path-params (:path-params request)
        byte-array (.getBytes (str body))
        b-file (io/input-stream (ByteArrayInputStream. byte-array))]
    {:path-params path-params
     :body b-file}))