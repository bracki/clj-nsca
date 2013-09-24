(defproject clj-nsca "0.0.1-SNAPSHOT"
  :description "Clojure wrapper for jsendnsca - Send passive Nagios checks from Clojure."
  :url "http://github.com/bracki/clj-nsca"
  :repositories {"local" "file:mvn-repo"}
  :main clj-nsca.core
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.googlecode.jsendnsca/jsendnsca "2.1-SNAPSHOT"]]
  :profiles {:dev {:dependencies [[midje "1.5.0"]]}})
