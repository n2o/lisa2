(ns lisa.utils
  (:require ["framer-motion" :refer [motion]] [clojure.string :as str]
            [com.fulcrologic.guardrails.core :refer [=> >defn ?]]
            [goog.string :refer [format]]
            [oops.core :refer [oset!]]))

;; -----------------------------------------------------------------------------
;; Motion

(defn basic-move-in
  "A basic move-in animation. Pass any transition you like."
  [from-direction transition component]
  (let [direction (case from-direction
                    :top {:y "-200%"}
                    :bottom {:y "200%"}
                    :left {:x "-200%"}
                    {:x "200%"})]
    [:> (.-div motion)
     {:initial direction
      :animate {:x 0 :y 0}
      :exit direction
      :transition transition}
     component]))

;; -----------------------------------------------------------------------------

(defn lorem [n]
  (let [lorem-ipsum (str/split "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet,"
                               #" ")]
    (str/join " " (take n lorem-ipsum))))

(>defn set-website-title!
  "Set a document's website title."
  [title]
  [(? string?) :ret any?]
  (when title
    (let [new-title (format "%s – %s" title "Systemische Beratung Freund")]
      (oset! js/document [:title] new-title))))

(>defn set-website-description!
  "Set a document's website meta-description."
  [description]
  [(? string?) :ret any?]
  (when description
    (when-let [selector (.querySelector js/document "meta[name='description']")]
      (.setAttribute selector "content" description))
    (when-let [og-selector (.querySelector js/document "meta[name='og:description']")]
      (.setAttribute og-selector "content" description))))
