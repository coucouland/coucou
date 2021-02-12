SHELL:=/bin/bash
REGISTRY?=micoronode
IMAGE_NAME=coucou
TAGS?=latest
BUILD_ARGS?=
JAVA_VERSION=11
AWS_DEFAULT_REGION?=ap-southeast-2

DIAGRAMS=docker run -v "${PWD}:/work" figurate/diagrams python

.PHONY: all clean build tag push run

all: build

clean:
	./gradlew clean && docker rmi $(REGISTRY)/$(IMAGE_NAME)

build:
	./gradlew build && \
		docker build -t $(REGISTRY)/$(IMAGE_NAME) ${BUILD_ARGS} --build-arg JAVA_VERSION=$(JAVA_VERSION) \
 		--build-arg HTTP_PROXY=${http_proxy} --network=host .

tag:
	echo $(TAGS) | tr "/," "-\n" | xargs -n1 -I % docker tag $(REGISTRY)/$(IMAGE_NAME) $(REGISTRY)/$(IMAGE_NAME):%

push:
	echo $(TAGS) | tr "/," "-\n" | xargs -n1 -I % docker push $(REGISTRY)/$(IMAGE_NAME):%

run:
	docker-compose up

diagram:
	$(DIAGRAMS) diagram.py
