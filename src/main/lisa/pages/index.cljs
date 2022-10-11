(ns lisa.pages.index
  (:require ["@heroicons/react/24/outline" :refer [FaceSmileIcon UserGroupIcon
                                                   UserIcon UsersIcon]]
            [lisa.pages.base :refer [base]]
            [lisa.utils :as utils]))

(defn- leistung [icon label]
  [:article.text-center
   [:> icon {:class "h-10 mx-auto"}]
   label])

(defn- profil []
  [:section {:class "w-3/4 mx-auto pt-10"}
   [:h2.py-5 "Kurzprofil"]
   [:div.flex.flex-row
    [:div.pr-3
     [:p [utils/lorem 42]]
     [:p [utils/lorem 23]]
     [:p [utils/lorem 32]]
     [:p "---"]
     [:p [utils/lorem 12]]]
    [:img {:class "w-96"
           :src "/img/womandog.webp"}]]])

(defn- leistungen []
  [:div.grid.grid-cols-4.gap-4.py-10
   [leistung UserIcon "Einzelberatung"]
   [leistung UsersIcon "Paarberatung"]
   [leistung FaceSmileIcon "Familienberatung"]
   [leistung UserGroupIcon "Teamberatung"]])

(defn- index-page []
  [base {:page/title "Start"
         :page/description "TODO"}
   [:<>
    [:img {:src "/img/sandbank.webp"}]
    [:div.container.mx-auto
     [leistungen]
     [profil]]]])

(defn index []
  [index-page])
