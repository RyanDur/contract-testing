#!/usr/bin/env bash

set -e -x

export TERM=${TERM:-dumb}

apt-get update

apt-get install openssh-server

ufw allow 22

pushd my-product
  ./gradlew clean assemble
popd
