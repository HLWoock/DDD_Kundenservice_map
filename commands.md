Docker
======
docker ps -a --format  "table {{.Names}}"
docker ps -a --filter name=^/postgres$

docker run -d --name activemq -p 61616:61616 -p 8161:8161 rmohr/activemq

docker images "hermannw/*"

docker build -t hermannw/kundenservice:0.1.0 .   //oder mit gradle
docker push hermannw/kundenservice:0.1.0  

docker exec -it activemq /bin/bash oder sh
docker logs avtivemq -f                          // ich kann Kommentare --- ins log schreiben

docker network ls


Docker-compose
==============
docker-compose up -d


Kubernetes
==========
kubectl create deployment [name]                            kubectl create deployment nginx-depl --image=nginx
kubectl edit deployment [name]
kubectl delete deployment [name]

kubectl get nodes | pod | services | replicaset | deployment | all -o wide -o yaml > [file name] --watch -n [ns name]
kubectl get pods -l environment=production,tier=frontend

kubectl logs [pod name]
kubectl exec -it [pod name] -- bin/bash
kubectl describe pod | service [pod name]

kubectl apply -f [file name] --namespace=[ns name]          config-file.yaml
kubectl delete -f [file name]                               config-file.yaml

kubectl create namespace [ns name]
kubectl api-resources --namespaced=true|false

DOS
===
netstat -ano | findstr LISTENING
tasklist | findstr [PID]
Get-NetTCPConnection -State Listen
Get-Process -Id (Get-NetTCPConnection -State Listen).OwningProcess


Git 
===
Projektverzeichnis in C:\Users\Administrator\git erstellen  
im Browser auf github einloggen  
Projekt anlegen  
mit Github Desktop File->Clone repository->url Projekt clonen  
Sourcecode in Verzeichnis kopieren   
mit Github Desktop committen und pushen   
in IDE importieren  


