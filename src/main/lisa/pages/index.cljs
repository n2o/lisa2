(ns lisa.pages.index
  (:require [lisa.pages.base :refer [base]]))

(defn- index-page []
  [base {:page/title "Start"
         :page/description "TODO"}
   [:<>
    [:h1 "Systemische Beratung Freund"]]])

(defn index []
  [index-page])
