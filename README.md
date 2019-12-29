# pipeoop

`pipeoop` is a real-time and batch data processing pipeline for an industrial spray painting gun machine. [Design Specs - WIP](https://github.com/alexandrustoica/pipeoop/wiki)

## How to run

1. Build our services.
```
$ docker-compose build
```

2. Run our services (you can run in in detached mode with `-d`).
```
$ docker-compose up
```
Read the documentation [here](https://docs.docker.com/compose/reference/up/).
> Note: It might take a while to set up our `HDFS` service and requires at least 8gb RAM.

3. Optional: You can connect to our `HDFS` service by finding `cloudera` container's id:
```
docker exec -it $(docker container ls | grep cloudera | cut -d " " -f1) bash
```
Read the documentation [here](https://docs.docker.com/engine/reference/commandline/exec/).

4. Optional: You can go to [http://localhost:8888/](http://localhost:8888/) to access the `HDFS` server with Hue.
```
username: cloudera 
password: cloudera
```

5.1. To stop our Docker containers:
```
$ docker-compose down
```

5.2 To stop our Docker containers and delete their data (databases, cache, etc):
```
$ docker-compose down -v --rmi
```
Read the documentation [here](https://docs.docker.com/compose/reference/down/).


## How to contribute

To run our project, you'll need [Docker](https://docs.docker.com/install/) and [Docker Compose](https://docs.docker.com/compose/install/). 

Fork repository, make changes, send us a pull request. We will review your changes and apply them to the master branch shortly, provided they don't violate our quality standards. To avoid frustration, before sending us your pull request, please run all services provided in `docker-compose.yml` file, and make sure everything runs according to our design specs:

```
$ docker-compose up
```

> Note: Each directory contains data configurations or programs used in our pipeline, if you need to create a new service, please attach all the required files in its directory.

## Authors

* **Alexandru Stoica** - *Initial work* - [Master](https://github.com/alexandrustoica/pipeoop/new/master)
