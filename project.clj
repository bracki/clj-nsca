(defproject clj-nsca "0.0.2"
  :description "Clojure wrapper for jsendnsca - Send passive Nagios checks from Clojure."
  :url "http://github.com/bracki/clj-nsca"
  :main clj-nsca.core
  :repositories {"fusesource" "http://repo.fusesource.com/nexus/content/groups/public/"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.googlecode/jsendnsca-core "1.3.1"]]
  :license {:name "Eclipse Public License - v 1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo
            :comments "same as Clojure"}
  :profiles {:dev {:dependencies [[midje "1.5.0"]]}})
