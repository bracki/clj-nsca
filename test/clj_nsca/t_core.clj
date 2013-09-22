(ns clj-nsca.t-core
  (:use midje.sweet)
  (:use [clj-nsca.core])
  (:import [com.googlecode.jsendnsca NagiosSettings MessagePayload]
           [com.googlecode.jsendnsca.encryption Encryption]))

(facts "about `nagios-settings`"
  (fact "it accepts host and port"
    (nagios-settings "host" 12345) => (doto (NagiosSettings.) 
                                        (.setNagiosHost "host") 
                                        (.setPort 12345)))

  (fact "it accepts host, port and password"
    (nagios-settings "host" 12345 "top-secret") => (doto (NagiosSettings.)
                                                     (.setNagiosHost "host")
                                                     (.setPort 12345)
                                                     (.setPassword "top-secret"))))

(facts "about `nagios-message`"
  (fact "it has host, level, service, description"
     (let [msg (nagios-message :host "horst"
                               :level "ok"
                               :service "service"
                               :description "description")]
       msg => (doto (MessagePayload.)
                (.setHostname "horst")
                (.setLevel "ok")
                (.setServiceName "service")
                (.setMessage "description")))))
