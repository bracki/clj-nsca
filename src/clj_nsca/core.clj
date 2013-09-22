(ns clj-nsca.core
  (:import [com.googlecode.jsendnsca.builders NagiosSettingsBuilder MessagePayloadBuilder]
           [com.googlecode.jsendnsca Level NagiosPassiveCheckSender]))

(defn nagios-settings
  "Create Nagios settings."
  ([host port] (-> (NagiosSettingsBuilder.)
                            (.withNagiosHost host)
                            (.withPort port)
                            (.create)))

  ([host port password] (-> (NagiosSettingsBuilder.)
                            (.withNagiosHost host)
                            (.withPort port)
                            (.withPassword password)
                            (.create))))

(defn nagios-message
  "Create a Nagios message."
  [& {:keys [host level service description]}]
  (-> (MessagePayloadBuilder.)
      (.withHostname host)
      (.withLevel (Level/tolevel level))
      (.withServiceName service)
      (.withMessage description)
      (.create)))

(defn send-message
  "Send the message."
  [settings message]
  (-> (NagiosPassiveCheckSender. settings)
      (.send message)))

(defn -main
  [& args]
  (send-message
    (nagios-settings "localhost" 1234)
    (nagios-message :host "horst"
                    :level "ok"
                    :service "API"
                    :description "Alles geil")))
