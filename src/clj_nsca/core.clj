(ns clj-nsca.core
  (:import [com.googlecode.jsendnsca.builders NagiosSettingsBuilder]))

(defn nagios-settings
  "Create Nagios Settings"
  ([host port] (.create (doto (new NagiosSettingsBuilder) (.withNagiosHost host) (.withPort port))))
  ([host port password] (.create (doto (new NagiosSettingsBuilder) (.withNagiosHost host) (.withPort port) (.withPassword password)))))
