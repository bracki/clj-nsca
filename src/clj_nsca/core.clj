(ns clj-nsca.core
  (:import [com.googlecode.jsendnsca.builders NagiosSettingsBuilder MessagePayloadBuilder]
           [com.googlecode.jsendnsca Level NagiosPassiveCheckSender]
           [com.googlecode.jsendnsca.encryption Encryption]))

(defn nagios-settings
  "Create Nagios settings."
  [opts]
  (let [opts (merge {:host "localhost"
                     :port 5667
                     :password ""
                     :encryption :NONE} opts)]
  (-> (NagiosSettingsBuilder.)
      (.withNagiosHost (:host opts))
      (.withPort (:port opts))
      (.withPassword (:password opts))
      (.withEncryption (Encryption/valueOf (name (:encryption opts))))
      (.create))))

(defn nagios-message
  "Create a Nagios message."
  [host level service message]
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
  (let [sender (nagios-sender (nagios-settings {:host "localhost"
                                                :port 5667
                                                :password "top-secret"
                                                :encryption :TRIPLE_DES}))]
    (send-message sender (nagios-message "horst" "warning" "API" "Oh noes!!!"))))
