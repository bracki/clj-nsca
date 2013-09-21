(ns clj-nsca.t-core
  (:use midje.sweet)
  (:use [clj-nsca.core])
  (:import [com.googlecode.jsendnsca NagiosSettings]))

(facts "about `nagios-settings`"
  (fact "it accepts host and port"
    (nagios-settings "host" 12345) => (doto (NagiosSettings.) (.setNagiosHost "host")  (.setPort 12345))))
