from pyspark import SparkContext, SparkConf

conf = SparkConf().setAppName("test").setMaster("local")
sc = SparkContext(conf=conf)
sc.textFile("/team_3/data/workunit").map(lambda x: x.split(",")).map(lambda x: x[0]).saveAsTextFile("/team_3/result")


