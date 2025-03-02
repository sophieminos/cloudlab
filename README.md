# cloudlab

## Set up
Démarrer minikube :
```
minikube start
```
Dépoiement :
```
kubectl apply -f deployment.yml  
```  
```
kubectl apply -f mysql-deployment.yml
```

Si besoin vérifier la base de données en accédant au pod mysql :
```
kubectl exec -it pod/mysql-[id_pod] -- mysql -u root -ppassword
```

Lancer le port-forward :
```
kubectl -n istio-system port-forward deployment/istio-ingressgateway 31380:8080
```
Ainsi on accède à notre app via l'url : [localhost:31380](localhost:31380)

- Le frontend est à [localhost:31380](localhost:31380) ;
- L'api de rentalservice est à [localhost:31380/rentalservice](localhost:31380/rentalservice) (la liste des voitures est à [localhost:31380/rentalservice/cars](localhost:31380/rentalservice/cars));
- La base de données utilisée par l'api est à [localhost:31380/mysql](localhost:31380/mysql) ;


## Infos utiles pour avoir ses propres images Docker
### API rentalservice :
Build app java :
```
cd rentalService &&
./gradlew clean build --refresh-dependencies
```
Générer l'image Docker :
```
docker build -t [docker_id]/rental-service:latest .
docker build -t sophieminos/rental-service:latest .
```
Pusher en DockerHub :
```
docker push [docker_id]/rental-service:latest
docker push sophieminos/rental-service:latest
```
Le script d'intialisation des tables de la base de donnée :
[rentalService/src/main/resources/data.sql](rentalService/src/main/resources/data.sql)

### Frontend :
Générer l'image Docker :
```
docker build -t [docker_id]/rental-frontend:latest .
docker build -t sophieminos/rental-frontend:latest .
```
Pusher en DockerHub :
```
docker push [docker_id]/rental-frontend:latest
docker push sophieminos/rental-frontend:latest
```


