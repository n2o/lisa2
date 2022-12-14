(ns lisa.pages.base
  (:require ["@heroicons/react/24/outline" :refer [Bars3Icon XMarkIcon]]
            [lisa.utils :as utils]
            [re-frame.core :as rf]
            [reagent.core :as r]))

(def ^:private menu
  [{:label "Start"
    :href "#"}
   {:label "Leistungen"
    :href "#"}
   {:label "Profil"
    :href "#"}
   {:label "Arbeiten mit Lasse"
    :href "#"}])

(defn- menu-links [props]
  [utils/basic-move-in
   :left
   {:type :spring
    :bounce 0.1
    :duration 0.5}
   [:section
    (for [{:keys [label href]} menu]
      [:a (merge {:href href
                  :key (str "menu-item-" label)}
                 props)
       label])]])

(defn- nav []
  (let [open? (r/atom false)]
    (fn []
      [:nav
       [:div.mx-auto.max-w-7xl.px-2.sm:px-6.lg:px-8
        [:div.relative.flex.h-16.items-center.justify-between
         [:div.absolute.inset-y-0.left-0.flex.items-center.sm:hidden
          [:button.inline-flex.items-center.justify-center.rounded-md.p-2.hover:bg-gray-100.hover:text-white
           {:aria-expanded "false",
            :aria-controls "mobile-menu",
            :type :button
            :on-click #(swap! open? not)}
           [:span.sr-only "Open main menu"]
           [:> (if @open? XMarkIcon Bars3Icon) {:className "text-gray-900 h-7"}]]]
         [:div.flex.flex-1.items-center.justify-center.sm:items-stretch.sm:justify-start
          [:div.flex.flex-shrink-0.items-center
           [:img.block.h-8.w-auto.lg:hidden
            {:alt "Your Company",
             :src
             "https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=500"}]
           [:img.hidden.h-8.w-auto.lg:block
            {:alt "Your Company",
             :src
             "https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=500"}]]
          [:div.hidden.sm:ml-6.sm:block
           [:div.flex.space-x-4.mt-1
            [menu-links {:class "text-gray-600 hover:bg-gray-100 px-3 py-2 rounded-md text-sm font-medium"}]]]]]]
       [:div#mobile-menu.sm:hidden
        (when @open?
          [:div.space-y-1.px-2.pt-2.pb-3
           [menu-links {:class "text-gray-600 hover:bg-gray-100 block px-3 py-2 rounded-md text-base font-medium"}]])]])))

(defn- header
  "Define the academy header."
  []
  [nav])

(defn- footer
  "Define the academy footer."
  []
  [:footer.text-gray-700.text-sm.pt-5
   [:hr.mx-5]
   [:nav.flex.p-6
    [:div.flex.items-center.flex-no-shrink.mr-6
     [:span.tracking-tight "Systemische Beratung Freund"]]
    [:div.ml-auto]
    [:a.mr-3 {:target :_blank :href "#todo"} "Impressum"]
    [:a {:target :_blank :href "#todo"} "Datenschutz"]]])

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
   [:div.flex.flex-col.min-h-screen
    [header]
    [:div.grow body]
    [footer]]])
