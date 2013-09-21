(defproject clj-nsca "0.0.1-SNAPSHOT"
  :description "Clojure wrapper for jsendnsca - Send passive Nagios checks from Clojure."
  :repositories {"local" "file:mvn-repo"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.googlecode.jsendnsca/jsendnsca "2.1-SNAPSHOT"]]
  :profiles {:dev {:dependencies [[midje "1.5.0"]]}})
