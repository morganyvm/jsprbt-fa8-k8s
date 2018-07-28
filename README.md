# jsprbt-fa8-k8s
PoC de Java 8+ Spring Boot com Kubernetes 


---
# Pré-Requisitos

###Frontend:
* AngularJS;
* HTML 5;

###Backend:
* Java (versão mínima 8.);
* Springboot; *
* Springdata; *
* SpringREST; *
* SpringBatch;
* SpringSecurity;

###Banco de Dados:
. SQL, Oracle ou PostgreSQL *
###Repositório de controle de versão:
. Git *


###Dev Environment
* Maven (versão mínima 3.5.3); *
* [Fabric8 (plugin maven para build e deployment no kubernetes);](http://spring.fabric8.io "fabric8")  


##Java
Para a execução


##Helm
[Helm](https://www.helm.sh helm) é uma ferramenta para gerenciar charts Kubernetes. Charts são pacotes 
de recursos Kubernetes pré-configurados.

###Instalacao
* https://docs.helm.sh/using_helm/#installing-helm

---
##Postgresql
Instalação no kubernetes 

```bash
$ helm install --name todo-db \
  --set postgresUser=todo-user,postgresPassword=secretpassword,postgresDatabase=todo-database \
    stable/postgresql
```

A execução do comando acima vai imprimir algo similar ao resultado abaixo: 

```bash
NAME:   todo-db
LAST DEPLOYED: Thu Jul 26 23:08:52 2018
NAMESPACE: default
STATUS: DEPLOYED

RESOURCES:
==> v1/Pod(related)
NAME                                 READY  STATUS   RESTARTS  AGE
todo-db-postgresql-5dfc478b59-qwhlx  0/1    Pending  0         1s

==> v1/Secret
NAME                TYPE    DATA  AGE
todo-db-postgresql  Opaque  1     1s

==> v1/ConfigMap
NAME                DATA  AGE
todo-db-postgresql  0     1s

==> v1/PersistentVolumeClaim
NAME                STATUS   VOLUME    CAPACITY  ACCESS MODES  STORAGECLASS  AGE
todo-db-postgresql  Pending  hostpath  1s

==> v1/Service
NAME                TYPE       CLUSTER-IP     EXTERNAL-IP  PORT(S)   AGE
todo-db-postgresql  ClusterIP  10.108.214.99  <none>       5432/TCP  1s

==> v1beta1/Deployment
NAME                DESIRED  CURRENT  UP-TO-DATE  AVAILABLE  AGE
todo-db-postgresql  1        1        1           0          1s


NOTES:
PostgreSQL can be accessed via port 5432 on the following DNS name from within your cluster:
todo-db-postgresql.default.svc.cluster.local
To get your user password run:

    PGPASSWORD=$(kubectl get secret --namespace default todo-db-postgresql -o jsonpath="{.data.postgres-password}" | base64 --decode; echo)

To connect to your database run the following command (using the env variable from above):

   kubectl run --namespace default todo-db-postgresql-client --restart=Never --rm --tty -i --image postgres \
   --env "PGPASSWORD=$PGPASSWORD" \
   --command -- psql -U todo-user \
   -h todo-db-postgresql todo-database



To connect to your database directly from outside the K8s cluster:
     PGHOST=127.0.0.1
     PGPORT=5432

     # Execute the following commands to route the connection:
     export POD_NAME=$(kubectl get pods --namespace default -l "app=postgresql,release=todo-db" -o jsonpath="{.items[0].metadata.name}")
     kubectl port-forward --namespace default $POD_NAME 5432:5432

```


###fabric8 maven plugin

Para o build e deployment dos serviços (spa-ui, api-gateway, auth-server e todo-service) será utilizado o plugin fabric8. 

```bash
mvn clean install fabric8:deploy
```

