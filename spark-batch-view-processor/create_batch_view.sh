#!/bin/bash

export PYSPARK_PYTHON=/usr/bin/python3.4
spark-submit pipeoop/spark-batch-view-processor/process.py
