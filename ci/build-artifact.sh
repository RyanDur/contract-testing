#!/usr/bin/env bash

set -e -x

export TERM=${TERM:-dumb}

sudo apt-get update

sudo apt-get install openssh-server

sudo ufw allow 22

pushd my-product
  ./gradlew clean assemble
popd
