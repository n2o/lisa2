(ns lisa.routes
  (:require [lisa.pages.index :refer [index]]
            [re-frame.core :as rf]))

(def routes
  ["" {:controllers [{:start #(rf/dispatch [:init])}]}
   ["/" {:controllers [{:start (fn [{:keys [query]}]
                                 (rf/dispatch [:navigation/navigate :routes/start {} query]))}]
         :name :routes/start
         :views index}]])
