package flight_predictor

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.ml.feature.VectorAssembler
import PreProc._

object MyApp {
 def main(args : Array[String]): Unit = {
      val conf = new SparkConf().setAppName("My first Spark application")
      val sc = new SparkContext(conf)
      val spark = SparkSession.builder().appName("Spark SQL project").config("some option", "value").enableHiveSupport().getOrCreate()
      import spark.implicits._

      Logger.getLogger("org").setLevel(Level.WARN)
      
      val df = loadDf(spark)
      df.show(3)
 }

 /* Paste the following in spark-shell after doing :pa
   import org.apache.spark.SparkConf
   import org.apache.spark.SparkContext
   import org.apache.spark.SparkContext._
   import org.apache.log4j.{Level, Logger}
   import org.apache.spark.sql.SparkSession
   import org.apache.spark.sql.functions._

   val spark = SparkSession.builder().appName("Spark SQL project").config("some option", "value").enableHiveSupport().getOrCreate()
   import spark.implicits._
   
   val fromFile = spark.read.csv("src/main/resources/2008.csv")
   val rowToSkip = fromFile.first
   val headings = fromFile.take(1)(0).toSeq.map(_.toString)
   
   val dfWithCancelled = fromFile.filter(row => row != rowToSkip).toDF(headings:_*)
   val dfWithExc = dfWithCancelled.filter(dfWithCancelled("Cancelled") < 1)
                     
   val forbiddenColumns = List("ArrTime", "DepTime", "ActualElapsedTime", "AirTime", "TaxiIn", "Diverted", "CarrierDelay", "WeatherDelay", "NASDelay", "SecurityDelay", "LateAircraftDelay")
   val excludedColumns = List("Year", "DayOfMonth", "FlightNum", "TailNum", "DepDelay", "Cancelled", "CancellationCode")

   val df = dfWithExc.drop((forbiddenColumns ++ excludedColumns): _*)

 */
}
