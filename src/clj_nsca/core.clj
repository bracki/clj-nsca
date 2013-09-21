(ns clj-nsca.core
  (:import [com.googlecode.jsendnsca.builders NagiosSettingsBuilder]))

(defn nagios-settings
  "Create Nagios Settings"
  ([host port] (-> (NagiosSettingsBuilder.)
                            (.withNagiosHost host)
                            (.withPort port)
                            (.create)))

  ([host port password] (-> (NagiosSettingsBuilder.)
                            (.withNagiosHost host)
                            (.withPort port)
                            (.withPassword password)
                            (.create))))
