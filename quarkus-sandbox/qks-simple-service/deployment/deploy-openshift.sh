#!/usr/bin/env bash

# Build native application
./mvnw buildNative

# Create a new Quarkus project
oc new-project quarkus-sandbox

# Create a new Binary Build named "quarkus-sandbox"
oc new-build --binary --name=quarkus-sandbox -l app=quarkus-sandbox

# Set the dockerfilePath attribute into the Build Configuration
oc patch bc/quarkus-sandbox -p '{"spec":{"strategy":{"dockerStrategy":{"dockerfilePath":"src/main/docker/Dockerfile.native"}}}}'

# Start the build, uploading content from the local folder
oc start-build quarkus-sandbox --from-dir=. --follow

# Create a new Application, using as Input the "quarkus-sandbox" Image Stream
oc new-app --image-stream=quarkus-sandbox:latest

# Expose the Service through a Route
oc expose svc/quarkus-sandbox
