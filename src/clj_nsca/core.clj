(ns clj-nsca.core
  (:import [com.googlecode.jsendnsca.builders NagiosSettingsBuilder MessagePayloadBuilder]
           [com.googlecode.jsendnsca Level NagiosPassiveCheckSender]
           [com.googlecode.jsendnsca.encryption Encryption]))

(defn nagios-settings
  "Create Nagios settings."
  [& {:keys [host port password encryption]
      :or {host "localhost"
           port 5667
           password ""
           encryption Encryption/NONE}}]
  (-> (NagiosSettingsBuilder.)
      (.withNagiosHost host)
      (.withPort port)
      (.withPassword password)
      (.withEncryption encryption)
      (.create)))

(defn nagios-message
  "Create a Nagios message."
  [& {:keys [host level service description]}]
  (-> (MessagePayloadBuilder.)
      (.withHostname host)
      ;; There's a typo in the java lib.
      ;; That's why the method is called
      ;; `tolevel` instead of `toLevel`.
      (.withLevel (Level/tolevel level))
      (.withServiceName service)
      (.withMessage description)
      (.create)))

(defn send-message
  "Send a message."
  [settings message]
  (-> (NagiosPassiveCheckSender. settings)
      (.send message)))

(defn -main
  [& args]
  (send-message
    (nagios-settings :host "localhost"
                     :port 5667
                     :password "top-secret"
                     :encryption Encryption/TRIPLE_DES)
    (nagios-message :host "horst"
                    :level "warning"
                    :service "API"
                    :description "Oh noes!!!")))
