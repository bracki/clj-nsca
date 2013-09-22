(ns clj-nsca.t-core
  (:use midje.sweet)
  (:use [clj-nsca.core])
  (:import [com.googlecode.jsendnsca NagiosSettings MessagePayload]
           [com.googlecode.jsendnsca.encryption Encryption]))

(facts "about `nagios-settings`"
  (fact "it accepts host and port"
    (nagios-settings :host "host"
                     :port 12345) =>
        (doto (NagiosSettings.)
          (.setNagiosHost "host")
          (.setPort 12345)
          (.setPassword "")
          (.setEncryption Encryption/NONE)))

  (fact "it accepts host, port, password and encryption algorithm"
    (nagios-settings :host "host"
                     :port 12345
                     :password "top-secret"
                     :encryption Encryption/TRIPLE_DES) =>
        (doto (NagiosSettings.)
          (.setNagiosHost "host")
          (.setPort 12345)
          (.setPassword "top-secret")
          (.setEncryption Encryption/TRIPLE_DES))))

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
