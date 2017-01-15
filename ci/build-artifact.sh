#!/usr/bin/env bash

set -e -x

export TERM=${TERM:-dumb}

apt-get update

apt-get -y install sudo

useradd -m docker && echo "docker:docker" | chpasswd && adduser docker sudo

apt-get -y install openssh-server

apt-get -y install ufw

sudo ufw allow 22

pushd my-product
  ./gradlew clean assemble
popd
