#!/usr/bin/env bash

set -e -x

pushd my-product
  ./gradlew clean assemble
popd
