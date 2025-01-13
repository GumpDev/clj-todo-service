(ns clj-todo-service.services.http_server
  (:require [io.pedestal.http.route :as route]
            [io.pedestal.http :as http]
            [io.pedestal.test :as test]
            [clj-todo-service.endpoints.get-todos :as get-todos]
            [clj-todo-service.endpoints.create-todo :as create-todo]))

(def routes (route/expand-routes
              #{["/todo" :post create-todo/create-todo :route-name :create-todo]
                ["/todo" :get get-todos/get-todos :route-name :get-todos]}))

(def service-map {::http/routes routes
                  ::http/port 9999
                  ::http/type :jetty
                  ::http/json-body true})

(def server (atom nil))

(defn start-server! []
  (reset! server (http/start (http/create-server service-map)))
  (println "Started HTTP server on port 9999"))

(defn stop-server! []
  (http/stop @server)
  (println "Stopped HTTP server"))

(defn test-endpoint [verb url]
  (test/response-for (::http/service-fn @server) verb url))

(start-server!)
