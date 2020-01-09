FROM python:3
ADD ./run.py ./run.py
ADD ./requirements.txt ./requirements.txt
RUN pip install -r ./requirements.txt
CMD [ "python", "./run.py" ]