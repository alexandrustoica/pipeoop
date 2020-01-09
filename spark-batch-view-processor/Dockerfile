FROM jupyter/pyspark-notebook

USER root

ADD ./requirements.txt ./requirements.txt
RUN pip install -r ./requirements.txt

dockerADD ./process.py ./process.py

CMD ["python", "./process.py" ]