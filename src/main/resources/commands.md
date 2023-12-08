docker ps -a --format  "table {{.Names}}"
docker ps -a --filter name=^/postgres$

docker images "hermannw/*"

docker build -t hermannw/kundenservice:0.1.0 .   //oder mit gradle
docker push hermannw/kundenservice:0.1.0  
docker-compose up -d

docker exec -it activemq /bin/bash