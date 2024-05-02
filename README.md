COMPILE PROJECT  
> mvn install

START APP  
> java -jar -Dspring.profiles.active=default ./target/desafio-1.0-SNAPSHOT.jar

CREATE POLL  
```
curl --location 'http://localhost:8080/api/polls' \
--header 'Content-Type: application/json' \
--data '{
"title": "Teste app 3"
}'
```

START SESSION
```
curl --location 'http://localhost:8080/api/polls/5a878b5d-6e02-4d47-b805-4f5c350f3b9d/start' \
--header 'Content-Type: application/json' \
--data '{
"timeSession": 120
}'
```

VOTE
```
curl --location 'http://localhost:8080/api/vote' \
--header 'Content-Type: application/json' \
--data '{
"pollId":"5a878b5d-6e02-4d47-b805-4f5c350f3b9d",
"cpfNumber":"35108385056",
"votingOption": 2
}'
```

RESULT
```
curl --location 'http://localhost:8080/api/polls/5a878b5d-6e02-4d47-b805-4f5c350f3b9d/result'
```