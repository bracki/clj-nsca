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
  [& {:keys [host level service message]}]
  (-> (MessagePayloadBuilder.)
      (.withHostname host)
      ;; There's a typo in the java lib.
      ;; That's why the method is called
      ;; `tolevel` instead of `toLevel`.
      (.withLevel (Level/tolevel level))
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
  (let [sender (nagios-sender (nagios-settings :host "localhost"
                                               :port 5667
                                               :password "top-secret"
                                               :encryption Encryption/TRIPLE_DES))]
    (send-message sender (nagios-message :host "horst"
                                         :level "warning"
                                         :service "API"
                                         :message "Oh noes!!!"))))
