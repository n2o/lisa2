(ns lisa.pages.index
  (:require ["@heroicons/react/24/outline" :refer [UserIcon UsersIcon UserGroupIcon FaceSmileIcon]]
            [lisa.pages.base :refer [base]]))

(defn- leistung [icon label]
  [:article.text-center
   [:> icon {:class "h-10 mx-auto"}]
   label])

(defn- index-page []
  [base {:page/title "Start"
         :page/description "TODO"}
   [:<>
    [:img {:src "/img/background.webp"}]
    [:div.container.px-5
     [:h1 "Systemische Beratung Freund"]
     [:div.grid.grid-cols-4.gap-4
      [leistung UserIcon "Einzelberatung"]
      [leistung UsersIcon "Paarberatung"]
      [leistung FaceSmileIcon "Familienberatung"]
      [leistung UserGroupIcon "Teamberatung"]]]]])

(defn index []
  [index-page])
