(defproject yieldbot/flambo "0.9.0-SNAPSHOT"
  :description "A Clojure DSL for Apache Spark"
  :url "https://github.com/yieldbot/flambo"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :mailing-list {:name "flambo user mailing list"
                 :archive "https://groups.google.com/d/forum/flambo-user"
                 :post "flambo-user@googlegroups.com"}
  :dependencies [[org.clojure/clojure "1.12.0"]
                 [org.clojure/tools.logging "1.3.0"]
                 [com.google.guava/guava "33.4.6-jre"]
                 [yieldbot/serializable-fn "0.1.3"
                  :exclusions [com.twitter/chill-java]]]
  :profiles {:dev
             {:dependencies [[midje "1.10.10"]
                             [criterium "0.4.3"]]
              :plugins [[lein-midje "3.2.1"]
                        [michaelblume/lein-marginalia "0.9.0"]
                        ;; [codox "0.8.9"]
                        [funcool/codeina "0.3.0"
                         :exclusions [org.clojure/clojure]]]
              ;; so gen-class stuff works in the repl
              :aot [flambo.function
                    flambo.example.tfidf]}
             :provided
             {:dependencies
              [[com.fasterxml.jackson.core/jackson-core "2.15.2"]
               [org.slf4j/slf4j-api "2.0.7"]
               [org.apache.spark/spark-core_2.12 "3.5.1"
                :exclusions [[com.google.code.findbugs/jsr305]
                             [com.google.code.gson/gson]
                             [com.google.protobuf/protobuf-java]
                             [com.fasterxml.jackson.core/jackson-core]
                             [org.slf4j/slf4j-api]
                             [commons-logging]]]
               [org.apache.spark/spark-streaming_2.12 "3.5.1"
                :exclusions [[com.google.code.findbugs/jsr305]
                             [com.google.code.gson/gson]
                             [com.google.protobuf/protobuf-java]
                             [org.slf4j/slf4j-api]]]
               [org.apache.spark/spark-streaming-kafka-0-10_2.12 "3.5.1"]
               [org.apache.spark/spark-sql_2.12 "3.5.1"
                :exclusions [[com.google.code.findbugs/jsr305]
                             [com.google.code.gson/gson]
                             [com.google.protobuf/protobuf-java]
                             [org.apache.yetus/audience-annotations]
                             [org.slf4j/slf4j-api]]]
               [org.apache.spark/spark-hive_2.12 "3.5.1"
                :exclusions [[com.google.code.findbugs/jsr305]
                             [com.google.code.gson/gson]
                             [com.google.protobuf/protobuf-java]
                             [org.apache.yetus/audience-annotations]
                             [org.slf4j/slf4j-api]
                             [commons-logging]]]
               [org.apache.spark/spark-catalyst_2.12 "3.5.1"
                :exclusions [[com.google.code.findbugs/jsr305]
                             [com.google.code.gson/gson]
                             [com.google.protobuf/protobuf-java]
                             [org.slf4j/slf4j-api]]]]}
             :clojure-1.6
             {:dependencies [[org.clojure/clojure "1.6.0"]]}
             :clojure-1.7
             {:dependencies [[org.clojure/clojure "1.7.0"]]}
             :uberjar
             {:aot :all}
             :example
             {:main flambo.example.tfidf
              :source-paths ["test/flambo/example"]
              :aot [flambo.example.tfidf]}}
  :checksum :warn ;; https://issues.apache.org/jira/browse/SPARK-5308
  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"]
  :codeina {:reader :clojure
            :src ["src/clj"]
            :target "doc/codeina"
            :src-uri "https://github.com/yieldbot/flambo/blob/develop/"
            :src-uri-prefix "#L"
            }
  :codox {:defaults {:doc/format :markdown}
          :include [flambo.api flambo.conf flambo.kryo flambo.sql]
          :output-dir "doc/codox"
          :src-dir-uri "http://github.com/yieldbot/flambo/blob/develop/"
          :src-linenum-anchor-prefix "L"}
  :jvm-opts ["-server" "-Xmx2g"
             "-Duser.language=en"
             "--add-opens=java.base/java.io=ALL-UNNAMED"
             "--add-opens=java.base/java.nio=ALL-UNNAMED"
             "--add-opens=java.base/java.lang.invoke=ALL-UNNAMED"
             "--add-opens=java.base/java.util=ALL-UNNAMED"
             "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED"
             "--add-opens=java.base/sun.util.calendar=ALL-UNNAMED"]
  :global-vars {*warn-on-reflection* false}
  :min-lein-version "2.5.0")
