#!/usr/bin/env bash

DIR="$( cd "$( dirname "$0" )" && pwd )"

cd $DIR

source forjep/bin/activate

python "$1"
