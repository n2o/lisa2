(ns lisa.pages.base
  (:require ["@heroicons/react/solid" :refer [MoonIcon SunIcon]]
            [lisa.utils :as utils]
            [re-frame.core :as rf]
            [reitit.frontend.easy :as rfe]))

(defn- header
  "Define the academy header."
  []
  (let [dark-mode? @(rf/subscribe [:dark-mode?])
        query-parameters @(rf/subscribe [:navigation/query-parameters])]
    [:nav.flex.bg-blue.dark:bg-blue-dark.p-6.text-white
     [:div.flex.items-center.flex-no-shrink.mr-6.cursor-pointer
      {:on-click #(rf/dispatch [:navigation/navigate :routes/start])}
      [:img.h-8.pr-2 {:src "TODO"}]
      [:span.font-semibold.text-xl.tracking-tight
       "Systemische Beratung Freund"]]
     [:a.header-link {:href (rfe/href :routes/start {} query-parameters)} "Start"]
     [:div.ml-auto]
     [:> (if dark-mode? MoonIcon SunIcon)
      {:class "h-7 my-auto pr-3 cursor-pointer"
       :on-click #(rf/dispatch [:dark-mode/toggle])}]
     [:a.header-link-external
      {:href "https://systemische-beratung-freund.de"} "systemische-beratung-freund.de"]]))

(defn- footer
  "Define the academy footer."
  []
  [:footer
   [:nav.flex.bg-blue-dark.p-6.text-white.mt-5
    [:div.flex.items-center.flex-no-shrink.mr-6
     [:span.font-semibold.text-xl.tracking-tight "Systemische Beratung Freund"]]
    [:div.ml-auto]
    [:a.text-white.mr-3 {:target :_blank :href "#todo"} "Impressum"]
    [:a.text-white {:target :_blank :href "#todo"} "Datenschutz"]]])

;; -----------------------------------------------------------------------------

(defn root
  "Root view to initialize and render the application."
  []
  (if-let [current-view @(rf/subscribe [:navigation/current-view])]
    [current-view]
    [:div]))

(defn base [{:page/keys [title description]} body]
  (utils/set-website-title! title)
  (utils/set-website-description! description)
  [:main
   [:div.dark:bg-gray-700.dark:text-white.flex.flex-col.min-h-screen
    [header]
    [:div.container.mx-auto.px-3.pt-3.grow body]
    [footer]]])
