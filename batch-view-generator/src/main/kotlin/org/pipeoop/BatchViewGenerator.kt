package org.pipeoop

import org.apache.spark.sql.SparkSession

/**
 * @author Alexandru Stoica
 */

class BatchViewGenerator {
}

fun main(args: Array<String>) {
    val logFile = "YOUR_SPARK_HOME/README.md"
    val spark = SparkSession.builder().appName("Simple Application").orCreate
    val logData = spark.read().textFile(logFile).cache()
    logData.filter { s -> s.contains("a")}.count()
    println("Lines with a: $logData")
    spark.stop()
}