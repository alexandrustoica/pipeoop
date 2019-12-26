# pipeoop

`pipeoop` is a real-time and batch data processing pipeline for an industrial spray painting gun machine. [Design Specs - WIP](https://github.com/alexandrustoica/pipeoop/wiki)

## How to contribute

To run our project, you'll need [Docker](https://docs.docker.com/install/) and [Docker Compose](https://docs.docker.com/compose/install/). 

Fork repository, make changes, send us a pull request. We will review your changes and apply them to the master branch shortly, provided they don't violate our quality standards. To avoid frustration, before sending us your pull request, please run all services provided in `docker-compose.yml` file, and make sure everything runs according to our design specs:

```
$ docker-compose up
```

> Note: Each directory contains data configurations or programs used in our pipeline, if you need to create a new service, please attach all the required files in its directory.

## Authors

* **Alexandru Stoica** - *Initial work* - [Master](https://github.com/alexandrustoica/pipeoop/new/master)
