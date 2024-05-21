Hazelcast in docker:  
` docker run -it --rm -e HZ_CLUSTERNAME=test-cluster -p 5701:5701 hazelcast/hazelcast:5.4.0`

CURL commands:
```shell
curl -X GET http://localhost:8080/api/persons
curl -X GET http://localhost:8080/api/persons/{id}
curl -X PUT -H "Content-Type: application/json" -d '{"name": "John", "surname": "Doe"}' http://localhost:8080/api/persons/{id}
```
