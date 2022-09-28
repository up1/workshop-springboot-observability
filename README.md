# Workshop Spring boot and observability
* Logging
* Tracing
* Metric

## Step to run 

### 1. Create JAR file in each services

Service A
```
$cd service_a
$mvnw clean package
```

Service B
```
$cd service_b
$mvnw clean package
```

### 2. Create all containers with docker compose
```
$docker compose build
$docker compose up -d
$docker compose ps

NAME                    COMMAND                  SERVICE             STATUS              PORTS
workshop-grafana-1      "/run.sh"                grafana             running             0.0.0.0:3000->3000/tcp
workshop-prometheus-1   "/bin/prometheus --c…"   prometheus          running             0.0.0.0:9090->9090/tcp
workshop-service-a-1    "java -jar ./app.jar"    service-a           running             0.0.0.0:8081->8080/tcp
workshop-service-b-1    "java -jar ./app.jar"    service-b           running             0.0.0.0:8082->8080/tcp
workshop-zipkin-1       "start-zipkin"           zipkin              running (healthy)   0.0.0.0:9411->9411/tc
```

See all logs in container
```
$docker compose logs --follow
```


### 3. Call service A -> service B
```
$curl http://localhost:8081/call

Hello from service B

```

### 4. Check observability

Metric with Prometheus and Grafana
* Check health check in each service 
  * [Service A](http://localhost:8081/actuator/health)
  * [Service B](http://localhost:8082/actuator/health)
* Check endpoint of prometheus in each service 
  * [Service A](http://localhost:8081/actuator/prometheus)
  * [Service B](http://localhost:8082/actuator/prometheus)
* Check target status in Prometheus server 
  * http://localhost:9090/targets
* Check dashboard in Grafana
  * http://localhost:3000

Distributed tracing with Zipkin
* http://localhost:9411/

Centralized log with ELK stack
* Query data from Elasticsearch
  * http://localhost:9200/logstash/_search
* Kibana
  * http://localhost:5601/
