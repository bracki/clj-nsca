(ns clj-nsca.t-core
  (:use midje.sweet)
  (:use [clj-nsca.core])
  (:import [com.googlecode.jsendnsca.core NagiosSettings MessagePayload Encryption]))

(facts "about `nagios-settings`"
  (fact "it has defaults"
    (nagios-settings {}) =>
        (doto (NagiosSettings.)
          (.setNagiosHost "localhost")
          (.setPort 5667)
          (.setPassword "password")
          (.setEncryptionMethod NagiosSettings/NO_ENCRYPTION)))

  (fact "it accepts host, port, password and encryption algorithm"
    ;; bypass stupid equals method on NagiosSettings
    (let [opts {:host "host"
                :port 12345
                :password "top-secret"
                :encryption TRIPLE_DES_ENCRYPTION}
          settings (nagios-settings opts)]
          opts => {:host (.getNagiosHost settings)
                   :port (.getPort settings)
                   :password (.getPassword settings)
                   :encryption (.getEncryptionMethod settings)})))

(facts "about `nagios-message`"
  (fact "it has host, level, service, description"
     (let [msg (nagios-message "horst" "ok" "service" "description")]
       msg => (doto (MessagePayload.)
                (.setHostname "horst")
                (.setLevel "ok")
                (.setServiceName "service")
                (.setMessage "description")))))
