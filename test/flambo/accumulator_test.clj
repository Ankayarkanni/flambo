(ns flambo.accumulator-test
  (:use midje.sweet)
  (:require [flambo.accumulator :as a]
            [flambo.conf :as conf]
            [flambo.api :as f]))

(facts
 "about accumulators"
  (let [conf (-> (conf/spark-conf)
                 (conf/master "local[*]")
                 (conf/app-name "accumulator-test"))]
    (f/with-context c conf
      (facts "about basic accumulation"
        (let [acc (a/long-accumulator c (long 0))
              named-acc (a/long-accumulator c (long 0) "my-acc")
              rdd (f/parallelize c (map long (range 21)))]
          (fact "acc should be 210"
            (f/foreach rdd (f/fn [x] (a/add acc (long x))))
            (a/value acc) => 210)
          (fact "named-acc should be 210 and have a name"
            (f/foreach rdd (f/fn [x] (a/add named-acc (long x))))
            (a/value named-acc) => 210
            (a/name named-acc) => "my-acc"))))))
