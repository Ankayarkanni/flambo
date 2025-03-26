(ns flambo.accumulator
  (:import [org.apache.spark.util AccumulatorV2]
           [org.apache.spark.api.java JavaSparkContext]
           [scala Option])
  (:refer-clojure :exclude [name]))

(defn long-accumulator
  "Creates longAccumulator and adds the value"
  ([sc value]
   (as-> (.longAccumulator (JavaSparkContext/toSparkContext sc)) $
     (do (.add $ value) $)))
  ([sc value name]
   (as-> (.longAccumulator (JavaSparkContext/toSparkContext sc) name) $
     (do (.add $ value) $))))

(defn double-accumulator
  "Creates doubleAccumulator and adds the value"
  ([sc value]
   (as-> (.doubleAccumulator (JavaSparkContext/toSparkContext sc)) $
     (do (.add $ value) $)))
  ([sc value name]
   (as-> (.doubleAccumulator (JavaSparkContext/toSparkContext sc) name) $
     (do (.add $ value) $))))

(defn coll-accumulator
  "Creates collectionAccumulator and adds the value"
  ([sc value]
   (as-> (.collectionAccumulator (JavaSparkContext/toSparkContext sc)) $
     (do (.add $ value) $)))
  ([sc value name]
   (as-> (.collectionAccumulator (JavaSparkContext/toSparkContext sc) name) $
     (do (.add $ value) $))))

(defn value
  "Returns the value of Accumulator"
  [^AccumulatorV2 accumulator-var]
  (.value accumulator-var))

(defn name
  "Returns the name of Accumulator"
  [^AccumulatorV2 accumulator-var]
  (let [name-var (.name accumulator-var)]
    (if (= scala.Some (class name-var))
      (.get name-var))))

(defn add
  "Add the value to Accumulator"
  [^AccumulatorV2 accumulator-var value]
  (.add accumulator-var value))
