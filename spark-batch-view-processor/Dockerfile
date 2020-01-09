FROM jupyter/pyspark-notebook

USER root

ADD ./process.py ./process.py
ADD ./requirements.txt ./requirements.txt
RUN pip install -r ./requirements.txt

CMD ["python", "./process.py" ]