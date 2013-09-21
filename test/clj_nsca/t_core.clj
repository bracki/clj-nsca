(ns clj-nsca.t-core
  (:use midje.sweet)
  (:use [clj-nsca.core])
  (:import [com.googlecode.jsendnsca NagiosSettings]
           [com.googlecode.jsendnsca.encryption Encryption]))

(facts "about `nagios-settings`"
  (fact "it accepts host and port"
    (nagios-settings "host" 12345) => (doto (NagiosSettings.) 
                                        (.setNagiosHost "host") 
                                        (.setPort 12345)))

  (fact "it sets an TRIPLE_DES encryption method when password is given"
    (nagios-settings "host" 12345 "top-secret") => (doto (NagiosSettings.) 
                                                     (.setNagiosHost "host") 
                                                     (.setPort 12345) 
                                                     (.setPassword "top-secret")
                                                     (.setEncryption Encryption/TRIPLE_DES)))
  (fact "it accepts host, port and password"
    (nagios-settings "host" 12345 "top-secret") => (doto (NagiosSettings.) 
                                                     (.setNagiosHost "host") 
                                                     (.setPort 12345) 
                                                     (.setPassword "top-secret"))))
