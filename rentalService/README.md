# lsi2

### Coding rules

Exception HTTP Handler: https://github.com/charroux/st2scl/blob/main/rentalService/src/main/java/com/example/rentalService/web/CarNotFoundException.java

Logger: https://github.com/charroux/lsi1/blob/main/src/main/java/com/example/lsi1/web/RentalWebService.java

### Project appropiation

Change the code.

#### Build du code

Compile the Java project:
```
./gradlew build
```
Under Linux, or
```
.\gradlew build
```
Under Windows

Build the docker image:
```
docker build -t myservice .
```

Check the image:
```
docker images
```

Start the container:
```
docker run -p 4000:8080 -t myservice
```

8080 is the port of the web service, while 4000 is the port for accessing the container. Test the web service using a web browser: http://localhost:4000 It displays hello.

Ctrl-C to stop the Web Service.

Check the containerID:
```
docker ps
```

Stop the container:
```
docker stop containerID
```

#### Publish the image to the Docker Hub

Retreive the image ID:
```
docker images
```

Tag the docker image:
```
docker tag imageID yourDockerHubName/imageName:version
```

Example: `docker tag 1dsd512s0d myDockerID/myservice:1`

Login to docker hub:
```
docker login
```
or
```
docker login http://hub.docker.com
```
or
```
docker login -u username -p password
```

Push the image to the docker hub:
```
docker push sophieminos/rental-service:latest
```

### Launch a workflow when the code is updated

The script is there: https://github.com/charroux/lsi1/blob/main/.github/workflows/action.yml

Create a new branch:
```
git branch newcarservice
```
Move to the new branch:
```
git checkout newcarservice
```
Update the code and commit changes:
```
git commit -a -m "newcarservice"
```
Push the changes to GitHub:
```
git push -u origin newcarservice
```
Create a Pull request on GitHub and follow the workflow.

Merge the branch on Github is everything is OK.

Then pull the new main version:

```
git checkout main
```
```
git pull origin main
```

If necessary delete the branch:

```
git branch -D newcarservice
```
```
git push origin --delete newcarservice
```
### Kubernetes

Start Docker Desktop

Use Minikube or Kubernetes via docker

Minikube installation: https://minikube.sigs.k8s.io/docs/start/?arch=%2Fwindows%2Fx86-64%2Fstable%2F.exe+download

Start Kubernetes minikube:
```
minikube start --cpus=2 --memory=5000 --driver=docker
```

---

### Install Istio
https://istio.io/latest/docs/setup/getting-started/

Download Istio (take care at the version 1.17 here).

```
cd istio-1.17.0    
export PATH=$PWD/bin:$PATH    
istioctl install --set profile=demo -y
cd ..   
```
Enable auto-injection of the Istio side-cars when the pods are started:
```
kubectl label namespace default istio-injection=enabled
```
Install the Istio addons (Kiali, Prometheus, Jaeger, Grafana):
```
kubectl apply -f samples/addons
```
Enable auto-injection of the Istio side-cars when the pods are started:
```
kubectl label namespace default istio-injection=enabled
```
---
### Deploy via command
```
kubectl create deployment rentalservice --image=sophieminos/rental-service:latest
```
Change the image from the Docker Hub.

Add a service:
```
kubectl expose deployment rentalservice --type=LoadBalancer   ```
```
Get the URL of the service:
```
minikube service rentalService --url  
```
Test in a browser
```
http://127.0.0.1:51300/cars  
```
---

### Deploy via a yalm file

Launch the deployment:
```
kubectl apply -f deployment.yml  
```
Chek is the pods is running:
```
kubectl get pods
```
Get the URL of the service:
```
minikube service rentalService --url  
```
Test in a browser
```
http://127.0.0.1:51300/cars  
```
---
Configure Docker so that it uses the Kubernetes cluster:
```
minikube docker-env
eval $(minikube -p minikube docker-env)
eval $(minikube docker-env)  
```
---
### Kubernetes Gateway

Check the configuration at 54: https://github.com/charroux/lsi1/blob/main/deployment.yml

Check also where the Kubernetes service is registered in the gataway at line 72.

Then get the address of the gateway:
```
kubectl -n istio-system port-forward deployment/istio-ingressgateway 31380:8080  
```
and finally test in your browser:
http://localhost:31380/rentalservice/cars
---
### Monotoring (service mesh)
#### Display the Kiali dashboard
Kiali is a console for Istio service mesh.
```
kubectl -n istio-system port-forward deployment/kiali 20001:20001
```
Launch the console: http://localhost:20001/

Active again carservice:

http://localhost:31380/rentalservice/cars

Then inspect the cluster in Kiali.


#### Monitoring with Graphana
```
kubectl -n istio-system port-forward deployment/grafana 3000:3000
```
http://localhost:3000/

---
