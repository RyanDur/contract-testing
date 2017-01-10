#!/usr/bin/env bash

set -e -x

export TERM=${TERM:-dumb}

pushd my-product
  ./gradlew clean check --debug
popd