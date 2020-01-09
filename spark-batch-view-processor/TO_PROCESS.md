```python
from pyspark import SparkContext, SparkConf

conf = SparkConf().setAppName("test").setMaster("local[*]")
sc = SparkContext(conf=conf)
```


```python
work_units = sc.textFile("hdfs://10.111.0.250/team_3/data/workunit") \
    .map(lambda x: x.split(",")) 

count_operator_type_a = lambda x, y: (x[0] + 1, x[1]) if y == 'TID_A' else (x[0], x[1] + 1)
add_operator = lambda x, y: (x[0] + y[0], x[1] + y[1])

bv_count_by_type = work_units \
    .map(lambda x: (int(x[1]), x[3])) \
    .aggregateByKey((0, 0), count_operator_type_a, add_operator) \
    .collect()

print(bv_count_by_type)
```

    [(3, (1014, 986)), (6, (263, 245)), (1, (498, 509)), (4, (1000, 1000)), (2, (1000, 1000)), (5, (973, 1027))]



```python
operator = lambda index: lambda acc, it: acc + float(it[index])
bv_avg_by_pressure = work_units \
    .map(lambda x: (x[1], x)) \
    .groupByKey(1) \
    .map(lambda batch: (batch[0], batch[1], len(batch[1]))) \
    .map(lambda x: 
        (x[0], {
            "avg_cup_pressure": reduce(operator(4), x[1], 0) / x[2],
            "avg_lower_cup_pressure": reduce(operator(5), x[1], 0) / x[2],
            "avg_upper_cup_pressure": reduce(operator(6), x[1], 0) / x[2],
            "avg_air_pressure": reduce(operator(7), x[1], 0) / x[2],
            "avg_lower_air_pressure": reduce(operator(8), x[1], 0) / x[2],
            "avg_upper_air_pressure": reduce(operator(9), x[1], 0) / x[2]
        }, x[2]))\
    .collect()

print(bv_avg_by_pressure)
```

    [('1', {'avg_cup_pressure': 0.4803363494421414, 'avg_lower_cup_pressure': 0.07718085434906724, 'avg_upper_cup_pressure': 0.8394153719880806, 'avg_air_pressure': 0.5109220372749211, 'avg_lower_air_pressure': 0.16186044905327474, 'avg_upper_air_pressure': 0.7475594165467463}, 1007), ('2', {'avg_cup_pressure': 0.5002304916664246, 'avg_lower_cup_pressure': 0.2114661526413086, 'avg_upper_cup_pressure': 0.6430089947916884, 'avg_air_pressure': 0.5127042076382469, 'avg_lower_air_pressure': 0.29857622579553755, 'avg_upper_air_pressure': 0.5111311284242889}, 2000), ('3', {'avg_cup_pressure': 0.507479774435546, 'avg_lower_cup_pressure': 0.2114661526413086, 'avg_upper_cup_pressure': 0.6430089947916884, 'avg_air_pressure': 0.5022401762359339, 'avg_lower_air_pressure': 0.29857622579553755, 'avg_upper_air_pressure': 0.5111311284242889}, 2000), ('4', {'avg_cup_pressure': 0.5089259519912503, 'avg_lower_cup_pressure': 0.2114661526413086, 'avg_upper_cup_pressure': 0.6430089947916884, 'avg_air_pressure': 0.5022648723330805, 'avg_lower_air_pressure': 0.29857622579553755, 'avg_upper_air_pressure': 0.5111311284242889}, 2000), ('5', {'avg_cup_pressure': 0.5152638640032037, 'avg_lower_cup_pressure': 0.2114661526413086, 'avg_upper_cup_pressure': 0.6430089947916884, 'avg_air_pressure': 0.48749723576659365, 'avg_lower_air_pressure': 0.29857622579553755, 'avg_upper_air_pressure': 0.5111311284242889}, 2000), ('6', {'avg_cup_pressure': 0.499414370333852, 'avg_lower_cup_pressure': 0.2588602184357661, 'avg_upper_cup_pressure': 0.8644600663695554, 'avg_air_pressure': 0.5109830054243508, 'avg_lower_air_pressure': 0.12784632036706156, 'avg_upper_air_pressure': 0.9318035816730827}, 508)]



```python
errors = sc.textFile("hdfs://10.111.0.250/team_3/data/pressure_error") \
    .map(lambda x: x.split(","))

work_units_with_errors = work_units.map(lambda x: (x[0], x)).join(errors.map(lambda x: (x[1], x)))

count_operator = lambda x, y: (x[0] + 1, x[1], x[2]) if y == '1' \
    else (x[0], x[1] + 1, x[2]) if y == '2' \
    else (x[0], x[1], x[2] + 1)    

comb_operator = lambda x, y: (x[0] + y[0], x[1] + y[1], x[2] + y[2])

bv_error_count = work_units_with_errors \
    .map(lambda x: (x[1][0][1], x[1][1][2])) \
    .aggregateByKey((0, 0, 0), count_operator, comb_operator) \
    .collect()

print(bv_error_count)
```

    [('1', (79, 134, 0)), ('2', (239, 900, 0)), ('6', (162, 40, 0)), ('4', (250, 851, 0)), ('5', (243, 927, 0)), ('3', (257, 919, 0))]



```python

```
