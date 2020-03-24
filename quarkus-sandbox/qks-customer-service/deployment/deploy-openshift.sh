#!/usr/bin/env bash

readonly SERVICE_NAME='simple-service'

# Build native application
./mvnw package -Pnative -Dnative-image.docker-build=true

# Create a new Quarkus project
oc new-project $SERVICE_NAME

# Create a new Binary Build
oc new-build --binary --name=$SERVICE_NAME -l app=$SERVICE_NAME

# Set the dockerfilePath attribute into the Build Configuration
oc patch bc/$SERVICE_NAME -p '{"spec":{"strategy":{"dockerStrategy":{"dockerfilePath":"src/main/docker/Dockerfile.native"}}}}'

# Start the build, uploading content from the local folder
oc start-build $SERVICE_NAME --from-dir=. --follow

# Create a new Application, using as input the Binary Build Image Stream
oc new-app --image-stream=$SERVICE_NAME:latest

# Expose the Service through a Route
oc expose svc/$SERVICE_NAME
