(ns clj-nsca.core
  (:import [com.googlecode.jsendnsca.core.builders NagiosSettingsBuilder MessagePayloadBuilder]
           [com.googlecode.jsendnsca.core.utils LevelUtils]
           [com.googlecode.jsendnsca.core NagiosPassiveCheckSender NagiosSettings Encryption]))

(def NO_ENCRYPTION NagiosSettings/NO_ENCRYPTION)
(def XOR_ENCRYPTION NagiosSettings/XOR_ENCRYPTION)
(def TRIPLE_DES_ENCRYPTION NagiosSettings/TRIPLE_DES_ENCRYPTION)

(defn nagios-settings
  "Create Nagios settings."
  [opts]
  (let [opts (merge {:host "localhost"
                     :port 5667
                     :password "password"
                     :encryption NO_ENCRYPTION} opts)]
  (-> (NagiosSettingsBuilder.)
      (.withNagiosHost (:host opts))
      (.withPort (:port opts))
      (.withPassword (:password opts))
      (.withEncryption (:encryption opts))
      (.create))))

(defn nagios-message
  "Create a Nagios message."
  [host level service message]
  (-> (MessagePayloadBuilder.)
      (.withHostname host)
      (.withLevel (LevelUtils/getLevel (name level)))
      (.withServiceName service)
      (.withMessage message)
      (.create)))

(defn nagios-sender
  "Create a Nagios sender."
  [settings]
  (new NagiosPassiveCheckSender settings))

(defn send-message
  "Send a message."
  [sender message]
  (.send sender message))

(defn -main
  [& args]
  (let [sender (nagios-sender (nagios-settings {:host "localhost"
                                                :port 5667
                                                :password "top-secret"
                                                :encryption TRIPLE_DES_ENCRYPTION}))]
    (send-message sender (nagios-message "horst" "warning" "API" "Oh noes!!!"))))
