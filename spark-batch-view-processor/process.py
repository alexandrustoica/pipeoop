from pyspark import SparkContext, SparkConf

conf = SparkConf().setAppName("test").setMaster("local")
sc = SparkContext(conf=conf)
print(sc.textFile("/team_3/data/workunit").count())
