docker ps -a --format  "table {{.Names}}"
docker ps -a --filter name=^/postgres$

docker run -d --name activemq -p 61616:61616 -p 8161:8161 rmohr/activemq

docker images "hermannw/*"

docker build -t hermannw/kundenservice:0.1.0 .   //oder mit gradle
docker push hermannw/kundenservice:0.1.0  
docker-compose up -d

docker exec -it activemq /bin/bash oder sh
docker logs avtivemq -f                          // ich kann Kommentare --- ins log schreiben

docker network ls