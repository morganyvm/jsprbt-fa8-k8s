# jsprbt-fa8-k8s
PoC de Java 8+ Spring Boot com Kubernetes 


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
git clone https://github.com/morganyvm/jsprbt-fa8-k8s.git
```

### Dev Environment
* Maven; *
(versão mínima 3.5.3)

* [Fabric8 (plugin maven para build e deployment no kubernetes);](http://spring.fabric8.io "fabric8")  


## Java
Necessário para o ambiente de desenvolvimento (`build`, testes e empacotamento) e para a execução das aplicações spring boot, executando dentro dos `containers` Docker.
Para os `containers` Docker a implementação selecionada é o OpenJDK. E a versão mínima da JVM é a versão 8 (OpenJDK 8 update 131), pois, somente à partir dessa versão do OpenJDK a JVM passa a entender os limites de memória e cpu dos `containers` Docker.


## Helm
[Helm](https://www.helm.sh helm) é uma ferramenta para gerenciar charts Kubernetes. Charts são pacotes 
de recursos Kubernetes pré-configurados.

### Instalacao
* https://docs.helm.sh/using_helm/#installing-helm

---
## Postgresql
Instalação no kubernetes 

```bash
$ helm install --name todo-db-service \
  --set postgresUser=todo-user,postgresPassword=${random},postgresDatabase=todo-db \
    stable/postgresql
```

A execução do comando acima vai imprimir algo similar ao resultado abaixo: 

```bash
NAME:   todo-db-service
LAST DEPLOYED: Sun Jul 29 20:07:49 2018
NAMESPACE: default
STATUS: DEPLOYED

RESOURCES:
==> v1/ConfigMap
NAME                        DATA  AGE
todo-db-service-postgresql  0     0s

==> v1/PersistentVolumeClaim
NAME                        STATUS   VOLUME    CAPACITY  ACCESS MODES  STORAGECLASS  AGE
todo-db-service-postgresql  Pending  hostpath  0s

==> v1/Service
NAME                        TYPE       CLUSTER-IP     EXTERNAL-IP  PORT(S)   AGE
todo-db-service-postgresql  ClusterIP  10.106.203.50  <none>       5432/TCP  0s

==> v1beta1/Deployment
NAME                        DESIRED  CURRENT  UP-TO-DATE  AVAILABLE  AGE
todo-db-service-postgresql  1        1        1           0          0s

==> v1/Pod(related)
NAME                                         READY  STATUS   RESTARTS  AGE
todo-db-service-postgresql-65d88cb9c8-p4g8z  0/1    Pending  0         0s

==> v1/Secret
NAME                        TYPE    DATA  AGE
todo-db-service-postgresql  Opaque  1     0s


NOTES:
PostgreSQL can be accessed via port 5432 on the following DNS name from within your cluster:
todo-db-service-postgresql.default.svc.cluster.local
To get your user password run:

    PGPASSWORD=$(kubectl get secret --namespace default todo-db-service-postgresql -o jsonpath="{.data.postgres-password}" | base64 --decode; echo)

To connect to your database run the following command (using the env variable from above):

   kubectl run --namespace default todo-db-service-postgresql-client --restart=Never --rm --tty -i --image postgres \
   --env "PGPASSWORD=$PGPASSWORD" \
   --command -- psql -U todo-user \
   -h todo-db-service-postgresql todo-db



To connect to your database directly from outside the K8s cluster:
     PGHOST=127.0.0.1
     PGPORT=5432

     # Execute the following commands to route the connection:
     export POD_NAME=$(kubectl get pods --namespace default -l "app=postgresql,release=todo-db-service" -o jsonpath="{.items[0].metadata.name}")
     kubectl port-forward --namespace default $POD_NAME 5432:5432

```


### fabric8 maven plugin

Para o build e deployment dos serviços (spa-ui, api-gateway, auth-server e todo-service) será utilizado o plugin fabric8.

O plugin fabric8 será responsável por empacotar as aplicações no `container` Docker e montar os graphs [Helm](https://www.helm.sh helm) para `deployment` no Kubernetes.

Para implantar (`deployment`) os graphs helm no Kubernetes basta executar o comando abaixo:  
  

```bash
mvn clean install fabric8:deploy
```

