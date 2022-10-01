(ns lisa.core
  (:require [goog.dom :as gdom]
            [goog.string.format] ;; required for goog.string. We need to require it once in our project.
            [lisa.navigation :as navigation]
            [lisa.pages.base :as base]
            [lisa.utils]
            [re-frame.core :as rf]
            [reagent.dom]))

(defn- render []
  (reagent.dom/render [base/root] (gdom/getElement "app")))

(defn init
  []
  (navigation/init-routes!)
  (render))

(defn- ^:dev/after-load clear-cache-and-render!
  "The `:dev/after-load` metadata causes this function to be called after
  shadow-cljs hot-reloads code. This function is called implicitly by its
  annotation."
  []
  (rf/clear-subscription-cache!)
  (navigation/init-routes!)
  (render))

(rf/reg-event-fx
 :init
 (fn [_ _]
   {:fx []}))
