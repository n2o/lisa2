(ns lisa.utils
  (:require [com.fulcrologic.guardrails.core :refer [=> >defn ?]]
            [goog.string :refer [format]]
            [lisa.config :as config]
            [oops.core :refer [oset!]]))

(>defn set-website-title!
  "Set a document's website title."
  [title]
  [(? string?) :ret any?]
  (when title
    (let [new-title (format "%s – %s" title config/application-name)]
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
