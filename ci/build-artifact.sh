#!/usr/bin/env bash

set -e -x

export TERM=${TERM:-dumb}

apt-get update

apt-get -y install openssh-server

apt-get -y install ufw

ufw allow 22

pushd my-product
  ./gradlew clean assemble
popd
