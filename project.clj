(defproject metosin/compojure-api "2.0.0-alpha18"
  :description "Compojure Api"
  :url "https://github.com/metosin/compojure-api"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo
            :comments "same as Clojure"}
  :dependencies [[potemkin "0.4.4"]
                 [prismatic/schema "1.1.7"]
                 [prismatic/plumbing "0.5.5"]
                 [frankiesardo/linked "1.2.9"]
                 [metosin/muuntaja "0.5.0"]
                 [ring/ring-core "1.6.3" :exclusions [clj-time commons-codec]]
                 [compojure "1.6.0"]
                 [metosin/ring-http-response "0.9.0"]
                 [metosin/ring-swagger "0.25.0"]
                 [metosin/ring-swagger-ui "2.2.10"]]
  :profiles {:uberjar {:aot :all
                       :ring {:handler examples.thingie/app}
                       :source-paths ["examples/thingie/src"]
                       :dependencies [[org.clojure/clojure "1.9.0"]
                                      [http-kit "2.2.0"]
                                      [reloaded.repl "0.2.4"]
                                      [com.stuartsierra/component "0.3.2"]]}
             :dev {:plugins [[lein-clojars "0.9.1"]
                             [lein-midje "3.2.1"]
                             [lein-ring "0.12.3"]
                             [funcool/codeina "0.5.0"]]
                   :dependencies [[org.clojure/clojure "1.9.0"]
                                  [metosin/spec-tools "0.5.1" :exclusions [org.clojure/spec.alpha]]
                                  [org.clojure/core.async "0.4.474"]
                                  [javax.servlet/javax.servlet-api "4.0.0"]
                                  [peridot "0.5.0" :exclusions [clj-time commons-codec]]
                                  [midje "1.9.1" :exclusions [com.rpl/specter
                                                              commons-codec
                                                              clj-time]]
                                  [com.rpl/specter "1.1.0"]
                                  [com.stuartsierra/component "0.3.2"]
                                  [metosin/jsonista "0.1.1"]
                                  [reloaded.repl "0.2.4"]
                                  [org.immutant/immutant "2.1.9" :exclusions [org.slf4j/slf4j-api]]
                                  [http-kit "2.2.0"]
                                  [criterium "0.4.4"]]
                   :test-paths ["test19"]
                   :ring {:handler examples.thingie/app
                          :reload-paths ["src" "examples/thingie/src"]}
                   :source-paths ["examples/thingie/src" "examples/thingie/dev-src"]
                   :main examples.server}
             :dev18 {:plugins [[lein-midje "3.2.1"]]
                     :dependencies [[org.clojure/clojure "1.8.0"]
                                    [clojure-future-spec "1.9.0-alpha16"]
                                    [metosin/spec-tools "0.5.1" :exclusions [org.clojure/spec.alpha]]
                                    [org.clojure/core.async "0.4.474"]
                                    [peridot "0.5.0" :exclusions [clj-time commons-codec]]
                                    [metosin/jsonista "0.1.1"]
                                    [javax.servlet/javax.servlet-api "4.0.0"]
                                    [midje "1.9.1" :exclusions [com.rpl/specter
                                                                commons-codec
                                                                clj-time]]
                                    [com.rpl/specter "1.1.0"]
                                    [com.stuartsierra/component "0.3.2"]
                                    [criterium "0.4.4"]]}
             :perf {:jvm-opts ^:replace ["-server"
                                         "-Xmx4096m"
                                         "-Dclojure.compiler.direct-linking=true"]}
             :logging {:dependencies [[org.clojure/tools.logging "0.4.0"]
                                      [org.slf4j/jcl-over-slf4j "1.7.25"]
                                      [org.slf4j/jul-to-slf4j "1.7.25"]
                                      [org.slf4j/log4j-over-slf4j "1.7.25"]
                                      [ch.qos.logback/logback-classic "1.2.3" :exclusions [org.slf4j/slf4j-api]]]}
             :async {:jvm-opts ["-Dcompojure-api.test.async=true"]
                     :dependencies [[manifold "0.1.6" :exclusions [org.clojure/tools.logging]]]}}
  :eastwood {:namespaces [:source-paths]
             :add-linters [:unused-namespaces]}
  :codeina {:sources ["src"]
            :target "gh-pages/doc"
            :src-uri "http://github.com/metosin/compojure-api/blob/master/"
            :src-uri-prefix "#L"}
  :deploy-repositories [["releases" :clojars]]
  :aliases {"all" ["with-profile" "dev:dev18:dev,async"]
            "start-thingie" ["run"]
            "aot-uberjar" ["with-profile" "uberjar" "do" "clean," "ring" "uberjar"]
            "test-ancient" ["midje"]
            "perf" ["with-profile" "default,dev,perf"]
            "deploy!" ^{:doc "Recompile sources, then deploy if tests succeed."}
                      ["do" ["clean"] ["midje"] ["deploy" "clojars"]]})
