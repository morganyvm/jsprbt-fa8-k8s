# jsprbt-fa8-k8s
PoC de Java 8+ Spring Boot com Kubernetes 


![deployment strategy](Slide1.png)

---
# Pré-Requisitos

### Frontend:
* AngularJS;
* HTML 5;

### Backend:
* Java (versão mínima 8.);
* Springboot; *
* Springdata; *
* SpringREST; *
* SpringBatch;
* SpringSecurity;

### Banco de Dados:
* SQL, Oracle ou PostgreSQL *

### Repositório de controle de versão:
* Git *

Instale o git de acordo com o sistema operacional utilizado.

Clone este repositório:

```bash
$ git clone https://github.com/morganyvm/jsprbt-fa8-k8s.git
```

### Dev Environment
* Maven; *
(versão mínima 3.5.3)

* [Fabric8 (plugin maven para build e deployment no kubernetes);](http://spring.fabric8.io "fabric8")  

* Docker Edge com Kubernetes habilitado

## Java
Necessário para o ambiente de desenvolvimento (`build`, testes e empacotamento) e para a execução das aplicações spring boot, executando dentro dos `containers` Docker.
Para os `containers` Docker a implementação selecionada é o OpenJDK. E a versão mínima da JVM é a versão 8 (OpenJDK 8 update 131), pois, somente à partir dessa versão do OpenJDK a JVM passa a entender os limites de memória e cpu dos `containers` Docker.

## Helm
[Helm](https://www.helm.sh "helm") é uma ferramenta para gerenciar charts Kubernetes. [Charts](https://github.com/helm/charts "charts") são pacotes de recursos Kubernetes pré-configurados.

```bash
$ helm init 
```


### Instalacao
* https://docs.helm.sh/using_helm/#installing-helm


### Istio 1.0.0
* Instalação veja [Download and prepare for the installation](https://istio.io/docs/setup/kubernetes/download-release/#download-and-prepare-for-the-installation "Istio download-and-prepare-for-the-installation")


### [Instalação Istio](https://istio.io/docs/setup/kubernetes/helm-install/ "Istio + helm")


```bash
curl -L https://git.io/getLatestIstio | sh -
ISTIO_VERSION=$(curl -L -s https://api.github.com/repos/istio/istio/releases/latest | \
                  grep tag_name | sed "s/ *\"tag_name\": *\"\\(.*\\)\",*/\\1/")
cd istio-$ISTIO_VERSION
```


```bash
$ kubectl apply -f install/kubernetes/helm/istio/templates/crds.yaml
```

```
customresourcedefinition.apiextensions.k8s.io "virtualservices.networking.istio.io" created
customresourcedefinition.apiextensions.k8s.io "destinationrules.networking.istio.io" created
customresourcedefinition.apiextensions.k8s.io "serviceentries.networking.istio.io" created
customresourcedefinition.apiextensions.k8s.io "gateways.networking.istio.io" created
customresourcedefinition.apiextensions.k8s.io "envoyfilters.networking.istio.io" created
customresourcedefinition.apiextensions.k8s.io "policies.authentication.istio.io" created
customresourcedefinition.apiextensions.k8s.io "meshpolicies.authentication.istio.io" created
customresourcedefinition.apiextensions.k8s.io "httpapispecbindings.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "httpapispecs.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "quotaspecbindings.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "quotaspecs.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "rules.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "attributemanifests.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "bypasses.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "circonuses.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "deniers.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "fluentds.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "kubernetesenvs.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "listcheckers.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "memquotas.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "noops.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "opas.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "prometheuses.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "rbacs.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "redisquotas.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "servicecontrols.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "signalfxs.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "solarwindses.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "stackdrivers.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "statsds.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "stdios.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "apikeys.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "authorizations.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "checknothings.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "kuberneteses.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "listentries.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "logentries.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "edges.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "metrics.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "quotas.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "reportnothings.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "servicecontrolreports.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "tracespans.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "rbacconfigs.rbac.istio.io" created
customresourcedefinition.apiextensions.k8s.io "serviceroles.rbac.istio.io" created
customresourcedefinition.apiextensions.k8s.io "servicerolebindings.rbac.istio.io" created
customresourcedefinition.apiextensions.k8s.io "adapters.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "instances.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "templates.config.istio.io" created
customresourcedefinition.apiextensions.k8s.io "handlers.config.istio.io" created
```

```bash
$ kubectl apply -f install/kubernetes/helm/istio/charts/certmanager/templates/crds.yaml
```

```bash
customresourcedefinition.apiextensions.k8s.io/clusterissuers.certmanager.k8s.io created
customresourcedefinition.apiextensions.k8s.io/issuers.certmanager.k8s.io created
customresourcedefinition.apiextensions.k8s.io/certificates.certmanager.k8s.io created
```


```bash
$ kubectl apply -f install/kubernetes/helm/helm-service-account.yaml
```

```
serviceaccount "tiller" created
clusterrolebinding.rbac.authorization.k8s.io "tiller" created
```

```bash
$ helm init --service-account tiller
```

```
$HELM_HOME has been configured at /Users/morgany/.helm.

Tiller (the Helm server-side component) has been installed into your Kubernetes Cluster.

Please note: by default, Tiller is deployed with an insecure 'allow unauthenticated users' policy.
For more information on securing your installation see: https://docs.helm.sh/using_helm/#securing-your-helm-installation
Happy Helming!
```

```bash
$ helm install --name istio --namespace istio-system --set grafana.enabled=true --set servicegraph.enabled=true --set tracing.enabled=true  install/kubernetes/helm/istio
```

```
NAME:   istio
LAST DEPLOYED: Tue Aug 14 00:59:01 2018
NAMESPACE: istio-system
STATUS: DEPLOYED

RESOURCES:
==> v1/ConfigMap
NAME                             DATA  AGE
istio-galley-configuration       1     1m
istio-grafana-custom-resources   2     1m
istio-statsd-prom-bridge         1     1m
prometheus                       1     1m
istio-security-custom-resources  2     1m
istio                            1     1m
istio-sidecar-injector           1     1m

==> v1/ServiceAccount
NAME                                    SECRETS  AGE
istio-galley-service-account            1        1m
istio-ingressgateway-service-account    1        1m
istio-egressgateway-service-account     1        1m
istio-grafana-post-install-account      1        1m
istio-mixer-service-account             1        1m
istio-pilot-service-account             1        1m
prometheus                              1        1m
istio-citadel-service-account           1        1m
istio-sidecar-injector-service-account  1        1m

==> v1alpha2/kubernetesenv
NAME     AGE
handler  1m

==> v1alpha2/kubernetes
attributes  1m

==> v1alpha2/rule
stdio                   1m
tcpkubeattrgenrulerule  1m
kubeattrgenrulerule     1m
promtcp                 1m
stdiotcp                1m
promhttp                1m

==> v2beta1/HorizontalPodAutoscaler
NAME                  REFERENCE                        TARGETS        MINPODS  MAXPODS  REPLICAS  AGE
istio-egressgateway   Deployment/istio-egressgateway   <unknown>/60%  1        5        1         1m
istio-ingressgateway  Deployment/istio-ingressgateway  <unknown>/60%  1        5        1         1m
istio-telemetry       Deployment/istio-telemetry       <unknown>/80%  1        5        1         1m
istio-policy          Deployment/istio-policy          <unknown>/80%  1        5        1         1m
istio-pilot           Deployment/istio-pilot           <unknown>/55%  1        1        1         1m

==> v1alpha2/attributemanifest
NAME        AGE
istioproxy  1m
kubernetes  1m

==> v1alpha2/logentry
tcpaccesslog  1m
accesslog     1m

==> v1alpha2/metric
responsesize     1m
requestduration  1m
requestsize      1m
tcpbytesent      1m
requestcount     1m
tcpbytereceived  1m

==> v1beta1/ClusterRole
istio-galley-istio-system                1m
istio-ingressgateway-istio-system        1m
istio-egressgateway-istio-system         1m
istio-grafana-post-install-istio-system  1m
istio-mixer-istio-system                 1m
istio-pilot-istio-system                 1m
prometheus-istio-system                  1m
istio-citadel-istio-system               1m
istio-sidecar-injector-istio-system      1m

==> v1beta1/ClusterRoleBinding
NAME                                                    AGE
istio-galley-admin-role-binding-istio-system            1m
istio-egressgateway-istio-system                        1m
istio-ingressgateway-istio-system                       1m
istio-grafana-post-install-role-binding-istio-system    1m
istio-mixer-admin-role-binding-istio-system             1m
istio-pilot-istio-system                                1m
prometheus-istio-system                                 1m
istio-citadel-istio-system                              1m
istio-sidecar-injector-admin-role-binding-istio-system  1m

==> v1/Service
NAME                      TYPE          CLUSTER-IP      EXTERNAL-IP  PORT(S)                                                                                                    AGE
istio-galley              ClusterIP     10.105.121.157  <none>       443/TCP,9093/TCP                                                                                           1m
istio-egressgateway       ClusterIP     10.102.179.234  <none>       80/TCP,443/TCP                                                                                             1m
istio-ingressgateway      LoadBalancer  10.99.196.220   localhost    80:31380/TCP,443:31390/TCP,31400:31400/TCP,15011:31254/TCP,8060:30334/TCP,15030:31769/TCP,15031:32414/TCP  1m
grafana                   ClusterIP     10.101.149.133  <none>       3000/TCP                                                                                                   1m
istio-policy              ClusterIP     10.105.150.65   <none>       9091/TCP,15004/TCP,9093/TCP                                                                                1m
istio-telemetry           ClusterIP     10.99.86.38     <none>       9091/TCP,15004/TCP,9093/TCP,42422/TCP                                                                      1m
istio-statsd-prom-bridge  ClusterIP     10.101.81.80    <none>       9102/TCP,9125/UDP                                                                                          1m
istio-pilot               ClusterIP     10.105.130.198  <none>       15010/TCP,15011/TCP,8080/TCP,9093/TCP                                                                      1m
prometheus                ClusterIP     10.96.106.8     <none>       9090/TCP                                                                                                   1m
istio-citadel             ClusterIP     10.106.75.6     <none>       8060/TCP,9093/TCP                                                                                          1m
istio-sidecar-injector    ClusterIP     10.107.55.199   <none>       443/TCP                                                                                                    1m

==> v1beta1/Deployment
NAME                      DESIRED  CURRENT  UP-TO-DATE  AVAILABLE  AGE
istio-galley              1        1        1           0          1m
istio-egressgateway       1        1        1           1          1m
istio-ingressgateway      1        1        1           1          1m
grafana                   1        1        1           1          1m
istio-telemetry           1        1        1           0          1m
istio-policy              1        1        1           1          1m
istio-statsd-prom-bridge  1        1        1           0          1m
istio-pilot               1        1        1           0          1m
prometheus                1        1        1           0          1m
istio-citadel             1        1        1           0          1m
istio-sidecar-injector    1        1        1           0          1m

==> v1/Pod(related)
NAME                                       READY  STATUS             RESTARTS  AGE
istio-galley-699888c459-cs9kn              0/1    ContainerCreating  0         1m
istio-egressgateway-6cff45b4db-z6qx8       1/1    Running            0         1m
istio-ingressgateway-fc648887c-cw2qj       1/1    Running            0         1m
grafana-5fb774bcc9-x7g6x                   1/1    Running            0         1m
istio-telemetry-544b8d7dcf-px5w8           0/2    ContainerCreating  0         1m
istio-policy-75f75cc6fd-lb6fh              2/2    Running            0         1m
istio-statsd-prom-bridge-7f44bb5ddb-6nqmr  0/1    ContainerCreating  0         1m
istio-pilot-6cd95f9cc4-vslr7               0/2    ContainerCreating  0         1m
prometheus-84bd4b9796-72x76                0/1    ContainerCreating  0         1m
istio-citadel-5b956fdf54-7x4mx             0/1    ContainerCreating  0         1m
istio-sidecar-injector-6d59d46ff4-fz7q4    0/1    ContainerCreating  0         1m

==> v1alpha2/stdio
NAME     AGE
handler  1m

==> v1alpha3/DestinationRule
istio-telemetry  1m
istio-policy     1m

==> v1alpha3/Gateway
istio-autogenerated-k8s-ingress  1m

==> v1beta1/MutatingWebhookConfiguration
istio-sidecar-injector  1m

==> v1alpha2/prometheus
handler  1m
```


### Redis
Criação do Redis para rate-limit do API Gateway

```bash
$ helm install --name api-gateway-rate-limit-db \
  --set password=${random} \
    stable/redis
```


A execução do comando acima vai imprimir algo similar ao resultado abaixo:

```
NAME:   api-gateway-rate-limit-db
LAST DEPLOYED: Mon Jul 30 02:36:11 2018
NAMESPACE: default
STATUS: DEPLOYED

RESOURCES:
==> v1/Secret
NAME                             TYPE    DATA  AGE
api-gateway-rate-limit-db-redis  Opaque  1     0s

==> v1/Service
NAME                                    TYPE       CLUSTER-IP     EXTERNAL-IP  PORT(S)   AGE
api-gateway-rate-limit-db-redis-master  ClusterIP  10.111.9.103   <none>       6379/TCP  0s
api-gateway-rate-limit-db-redis-slave   ClusterIP  10.99.221.117  <none>       6379/TCP  0s

==> v1beta1/Deployment
NAME                                   DESIRED  CURRENT  UP-TO-DATE  AVAILABLE  AGE
api-gateway-rate-limit-db-redis-slave  1        1        1           0          0s

==> v1beta2/StatefulSet
NAME                                    DESIRED  CURRENT  AGE
api-gateway-rate-limit-db-redis-master  1        1        0s

==> v1/Pod(related)
NAME                                                    READY  STATUS             RESTARTS  AGE
api-gateway-rate-limit-db-redis-slave-85cc5674cc-59kbj  0/1    ContainerCreating  0         0s
api-gateway-rate-limit-db-redis-master-0                0/1    ContainerCreating  0         0s


NOTES:
** Please be patient while the chart is being deployed **
Redis can be accessed via port 6379 on the following DNS names from within your cluster:

api-gateway-rate-limit-db-redis-master.default.svc.cluster.local for read/write operations
api-gateway-rate-limit-db-redis-slave.default.svc.cluster.local for read-only operations


To get your password run:

    export REDIS_PASSWORD=$(kubectl get secret --namespace default api-gateway-rate-limit-db-redis -o jsonpath="{.data.redis-password}" | base64 --decode)

To connect to your Redis server:

1. Run a Redis pod that you can use as a client:

   kubectl run --namespace default api-gateway-rate-limit-db-redis-client --rm --tty -i \
    --env REDIS_PASSWORD=$REDIS_PASSWORD \
   --image docker.io/bitnami/redis:4.0.10-debian-9 -- bash

2. Connect using the Redis CLI:
   redis-cli -h api-gateway-rate-limit-db-redis-master -a $REDIS_PASSWORD
   redis-cli -h api-gateway-rate-limit-db-redis-slave -a $REDIS_PASSWORD

To connect to your database from outside the cluster execute the following commands:

    export POD_NAME=$(kubectl get pods --namespace default -l "app=redis" -o jsonpath="{.items[0].metadata.name}")
    kubectl port-forward --namespace default $POD_NAME 6379:6379
    redis-cli -h 127.0.0.1 -p 6379 -a $REDIS_PASSWORD

```

### Istio Automatic Sidecar Injection
* [Habilitação de automatic sidecar](https://istio.io/docs/setup/kubernetes/sidecar-injection/#automatic-sidecar-injection "Enabling Automatic Sidecar").

```bash
$ kubectl label namespace default istio-injection=enabled
```

### Postgresql
Criação do banco de dados postgresql para o todo-service

```bash
$ helm install --name todo-service-db \
  --set postgresUser=todo-user,postgresPassword=${random},postgresDatabase=todo-db \
    stable/postgresql
```

A execução do comando acima vai imprimir algo similar ao resultado abaixo: 

```
NAME:   todo-service-db
LAST DEPLOYED: Mon Jul 30 01:56:46 2018
NAMESPACE: default
STATUS: DEPLOYED

RESOURCES:
==> v1/Secret
NAME                        TYPE    DATA  AGE
todo-service-db-postgresql  Opaque  1     0s

==> v1/ConfigMap
NAME                        DATA  AGE
todo-service-db-postgresql  0     0s

==> v1/PersistentVolumeClaim
NAME                        STATUS   VOLUME    CAPACITY  ACCESS MODES  STORAGECLASS  AGE
todo-service-db-postgresql  Pending  hostpath  0s

==> v1/Service
NAME                        TYPE       CLUSTER-IP     EXTERNAL-IP  PORT(S)   AGE
todo-service-db-postgresql  ClusterIP  10.100.137.67  <none>       5432/TCP  0s

==> v1beta1/Deployment
NAME                        DESIRED  CURRENT  UP-TO-DATE  AVAILABLE  AGE
todo-service-db-postgresql  1        1        1           0          0s

==> v1/Pod(related)
NAME                                        READY  STATUS   RESTARTS  AGE
todo-service-db-postgresql-b4ccff6c4-pb8zw  0/1    Pending  0         0s


NOTES:
PostgreSQL can be accessed via port 5432 on the following DNS name from within your cluster:
todo-service-db-postgresql.default.svc.cluster.local
To get your user password run:

    PGPASSWORD=$(kubectl get secret --namespace default todo-service-db-postgresql -o jsonpath="{.data.postgres-password}" | base64 --decode; echo)

To connect to your database run the following command (using the env variable from above):

   kubectl run --namespace default todo-service-db-postgresql-client --restart=Never --rm --tty -i --image postgres \
   --env "PGPASSWORD=$PGPASSWORD" \
   --command -- psql -U todo-user \
   -h todo-service-db-postgresql todo-db



To connect to your database directly from outside the K8s cluster:
     PGHOST=127.0.0.1
     PGPORT=5432

     # Execute the following commands to route the connection:
     export POD_NAME=$(kubectl get pods --namespace default -l "app=postgresql,release=todo-service-db" -o jsonpath="{.items[0].metadata.name}")
     kubectl port-forward --namespace default $POD_NAME 5432:5432

```

### Ingress Controller

#### Istio Ingress
Vá para o diretório do api-gateway e criee o recurso do ingress.

```bash
$ cd api-gateway
$ kubectl apply -f src/main/etc/ingress.yml 
```

```bash
gateway.networking.istio.io/api-gateway created
virtualservice.networking.istio.io/httpbin created
```

---

## fabric8/fabric8-maven-plugin

Para o build e deployment dos serviços (spa-ui, api-gateway, auth-server e todo-service) será utilizado o plugin fabric8.

O plugin fabric8 será responsável por empacotar as aplicações no `container` Docker e montar os graphs helm para `deployment` no Kubernetes.

Para implantar (`deployment`) os graphs helm no Kubernetes basta executar o comando abaixo:  
  

```bash
$ mvn clean install fabric8:deploy
```

---

# Referencias


* [Docker Documentation](https://docs.docker.com "Docker Docs")


* [Helm](https://www.helm.sh "helm")


* [Helm PostgreSQL Chart](https://github.com/helm/charts/tree/master/stable/postgresql "PostgreSQL")


* [Helm Redis Chart](https://github.com/helm/charts/tree/master/stable/redis "Redis")


* [Helm Ingress Nginx](https://github.com/helm/charts/tree/master/stable/nginx-ingress "Ingress Nginx")


* [Nginx Ingress Controller](https://kubernetes.github.io/ingress-nginx/deploy "Nginx Ingress Controller")


* [K8s ConfigMap](https://kubernetes.io/docs/tasks/configure-pod-container/configure-pod-configmap#add-configmap-data-to-a-volume "ConfigMap") 


* [Fabric8 Maven Plugin](https://maven.fabric8.io "fabric8")


* [Fabric8 Spring Boot on Kubernetes](https://spring.fabric8.io "spring boot on k8s")


* [Istio](https://istio.io "Istio Service Mesh")

