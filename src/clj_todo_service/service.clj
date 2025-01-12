(ns clj-todo-service.service
  (:require [io.pedestal.http.route :as route]
            [io.pedestal.http :as http]
            [io.pedestal.test :as test])
  (:import (java.util UUID)))

(def store (atom {}))

(defn criar-tarefa-mapa [nome]
  {:nome nome :status 0})

(defn criar-tarefa [request]
  (let [uuid (UUID/randomUUID)
         nome (get-in request [:query-params :nome])
         tarefa (criar-tarefa-mapa nome)]
     (swap! store assoc uuid tarefa)
     {:status 200 :body "Success"}))

(defn funcao-hello [request]
  {:status 200 :body (str "hello world " (get-in request [:query-params :name]))})

(def routes (route/expand-routes
              #{["/hello" :get funcao-hello :route-name :hello-world]
                ["/tarefa" :post criar-tarefa :route-name :criar-tarefa]}))

(def service-map {::http/routes routes
                  ::http/port 9999
                  ::http/type :jetty
                  ::http/join? false})

(def server (atom nil))

(defn start-server! []
  (reset! server (http/start (http/create-server service-map)))
  (println "Started HTTP server on port 9999"))

(defn test-endpoint [verb url]
  (test/response-for (::http/service-fn @server) verb url))

(start-server!)
(println (test-endpoint :get "/hello"))
(println (test-endpoint :post "/criar-tarefa?nome=teste"))