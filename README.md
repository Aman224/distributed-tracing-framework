# Distributed Tracing Framework

## MSc Project COMP5200M

## Introduction

This framework performs distributed tracing using the service mesh in container orchestration systems. It uses the [Istio](https://istio.io/) service mesh to collect trace information which is then stored in a [MongoDB](https://www.mongodb.com/) database. This database is queried to generate the trace visualisation. It is developed as a [Spring Boot](https://spring.io/projects/spring-boot) backend application that can be deployed as a pod in [Kubernetes](https://kubernetes.io/). This deployment will configure the Istio proxies to send trace information to this application.

## Requirements

- Java 11+
- [Docker](https://www.docker.com/)
- Kubernetes
- Istio

## How to Build

This application uses Maven to build the jar file. To build the application follow the below steps
1. Navigate to the root directory of the zip file
2. Run the command

       ./mvnv clean package

   This will generate a .jar file inside the ./target folder called `tracing-demo-0.0.1-SNAPSHOT.jar`

## Building the docker image
1. Navigate to the root directory of the zip file
2. Run the command

       docker build -t tracing-framework:0.0.1 .

   This command will build a docker image and tag it as `tracing-framework:0.0.1`

3. If [Minikube](https://minikube.sigs.k8s.io/docs/start/) is used as the Kubernetes cluster the image need not be pushed into docker hub. It can be directly loaded into the Minikube cluster using the command

       minikube image load tracing-framework:0.0.1

   For other Kubernetes deployments, the image must be pushed to docker hub.

## Pushing docker image to docker hub
1. Login to docker credentials using the command

       docker login --username=<your-username> --email=<your-email>

2. List the images to find the image id. This command will list out all the images in the current environment.

       $ docker images

       REPOSITORY                    TAG             IMAGE ID       CREATED        SIZE
       tracing-framework             0.0.1           ebb8f5e9b686   4 hours ago    188MB

3. Tag the docker image using the command

       docker tag <image-id> <your-username>/tracing-framework:0.0.1

4. Push the image to docker hub

       docker push <your-username>/tracing-framework:0.0.1

## Deploying application in Kubernetes
1. Copy the contents from the folder deployment/kube to the environment with the Kubernetes cluster deployed. It may be a local Minikube cluster or a cloud environment.
2. Deploy the Mongodb database using the command

       kubectl apply -f deployment/kube/mongo-deployment.yaml

3. Deploy the tracing framework using the command

       kubectl apply -f deployment/kube/deployment.yaml

4. Wait for the pods to be ready

       $ kubectl get pods -n istio-system

       NAME                                   READY   STATUS             RESTARTS   AGE
       istio-egressgateway-545f48cf64-zb76s   1/1     Running            0          17m
       istio-ingressgateway-dc7bfc496-vfmwz   1/1     Running            0          17m
       istiod-647cff69d6-4rxs8                1/1     Running            0          18m
       tracing-db-5f667f9f56-xw2hb            1/1     Running            0          16m
       tracing-framework-5974bc6b5b-k2b8x     1/1     Running            0          16m       

   After a few minutes, the pods will be ready for the tracing application and the MongoDB database.

6. Access the application by port forwarding port 8080 to the tracing framework Kubernetes pod.

       kubectl port-forward <pod-name> 8080:8080 -n istio-system

   The application will be available with the URL HTTP://localhost:8080/trace/spans.

## Run Tests

The unit tests for the application can be run using

    ./mvnv test


## API Documentation

|   |	API Endpoint	                | Method |	Parameters                |	Description                                                          |
|---|-----------------------------------|--------|----------------------------|----------------------------------------------------------------------|
| 1 |	/api/v1/trace/spans             |	POST |	Body: Trace Span          |	Collects and Stores traces in API v1 format from Istio service mesh. |
| 2 |	/api/v2/trace/spans             |	POST |	Body: Trace Span          |	Collects and Stores traces in API v2 format from Istio service mesh. |
| 3 |	/trace/spans                    |	GET  |	None                      |	Retrieves all the spans from the database.                           |
| 4 |	/trace/{traceId}/spans          |	GET  |	PathParam: traceId        |	Retrieves all the spans from the database for a specified traceId.   |
| 5 |	/trace/{traceId}/visualise      |	GET  |	PathParam: traceId        |	Generates the trace visualisation for a given traceId.               |
| 6 |	/trace/{traceId}/spans/{spanId} |	GET  |	PathParam: traceId, spanId|	Returns the span with specified traceId and spanId.                  |


## Output

Upon running the application a visualisation will be generated on the API endpoint /trace/{traceId}/visualise based on the traceId specified.