#!/bin/bash

sqoop job -exec import_workunit_job
sqoop job -exec import_pressure_error_job
