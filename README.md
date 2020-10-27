# proxy-service
A simple proxy-service for using together with 3scale

### Building 

The project is built with maven and consists of a spring-boot application with rest-endpoints.

### Running the example in OpenShift

The application is meant to be deployed on top of OpenShift with Source2Image.

To list all the running pods:

    oc get pods

Then find the name of the pod that runs this quickstart, and output the logs from the running pods with:

    oc logs <name of pod>

You can also use the OpenShift console to view status, running pods, and view logs and much more.  

**Readiness** and **LivenessProbe** polls are aimed/connected to the /actuator/health endpoints instead of /health .
Logs are read and the readiness will pollute the logs if aimed at /health.

## Open API specification (Swagger)
The application comes with a swagger-plugin and when run hosts a swagger-ui where you can expore and try the service out.  
The swagger-page can be found here: http://[host].[domain]:[port]/swagger-ui.html  
ex: http://localhost:8080/swagger-ui.html id run locally  
The API-specification can be used for configuring a 3scale-API towards it.

## Application metadata
The application also use Spring-boot actuator to expose more endpoints for metrics and status etc.
Listed below is the available endpoints.

**/actuator/health**

Shows application health information

**/actuator/info**

Displays arbitrary application info

**/actuator/loggers**

the logging level of the application

**/actuator/metrics**

Shows ‘metrics’ information

**/actuator/prometheus**

Exposes metrics in a format that can be scraped by a Prometheus server. 