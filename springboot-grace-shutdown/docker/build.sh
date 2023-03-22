#! /bin/sh
cp -rf ../target/springboot-grace-shutdown-0.0.1.jar ./
docker build --platform=linux/amd64 -t hcapp/grace-down-demo:shell-java ./