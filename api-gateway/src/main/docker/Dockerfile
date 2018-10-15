# see https://raw.githubusercontent.com/fabric8io-images/java/master/images/alpine/openjdk8/jre/Dockerfile
FROM ${from.image}

USER root

# Create a user and group used to launch processes
# The user ID 1000 is the default which is different from already existing 
# default users on alpine so there is a high chance that this ID will be equal 
# to the current user making it easier to use volumes (no permission issues)
RUN echo "appgroup:x:1000:" >> /etc/group \
 && echo "appuser:x:1000:0:App User:/opt/appuser:/sbin/nologin" >> /etc/passwd \
 && mkdir -p /opt/appuser \
 && chmod 755 /opt/appuser

USER appuser

# Set the working directory to appuser' user home directory
WORKDIR /opt/appusaer

USER root

RUN mkdir -p /deployments
RUN mkdir -p /deployments/sharedclasses

VOLUME /deployments/sharedclasses

# JAVA_APP_DIR is used by run-java.sh for finding the binaries
ENV JAVA_APP_DIR=/deployments \
    JAVA_MAJOR_VERSION=8

RUN echo "securerandom.source=file:/dev/urandom" >> /opt/java/openjdk/jre/lib/security/java.security

# Agent bond including Jolokia and jmx_exporter
ADD agent-bond/agent-bond-opts /opt/run-java-options
RUN chmod 755 /opt/run-java-options \
 && mkdir -p /opt/agent-bond \
 && curl http://central.maven.org/maven2/io/fabric8/agent-bond-agent/1.2.0/agent-bond-agent-1.2.0.jar \
          -o /opt/agent-bond/agent-bond.jar \
 && chmod 444 /opt/agent-bond/agent-bond.jar
 
ADD agent-bond/jmx_exporter_config.yml /opt/agent-bond/

# https://github.com/fabric8io/docker-maven-plugin/tree/master/samples/run-java
# Add run script as /deployments/run-java.sh and make it executable
ADD run-java.sh /deployments/
# ADD target/docker-extra/run-java/run-java.sh /deployments/
RUN chmod 755 /deployments/run-java.sh

COPY maven/ /deployments/

# Run under user "appuser" and prepare for be running
RUN chown -R appuser /deployments \
 && chmod -R 'g+rwX' /deployments \
 && chown -R appuser:root /deployments
 
EXPOSE 8080 8778 9779

USER appuser

ENV JAVA_OPTIONS="-Xshareclasses:cacheDir=/deployments/sharedclasses"

CMD [ "/deployments/run-java.sh" ]