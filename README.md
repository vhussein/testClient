# testClient

### Prerequisite:
1. You would need to have docker install on your machine https://docs.docker.com/get-started/

2. Once installed, run the docker-compose.yml in src/main/resources/db/docker-compose.yml. This will create a mysql image in your local docker.

3. After the mysql container is created & started, you can run the src/main/resources/db/db-init/init.sql

4. Subsequently you can run another script to create the table src/main/resources/db/init.sql

5. Before you can deploy the image, do a _mvn clean install_

6. Once the artefact (*.jar) is created, you can run the following command:

    > docker build -t vhussein/test-client .
      && docker run
      -p 8081:8080 -p 5000:5000 -p 5005:5005
      --env MYSQL_HOST=<HOST_IP>
      --env MYSQL_USERNAME=pcdbuser
      --env MYSQL_PWD=pcdbuser
      --name test-client
      -m 4g
      -e "JAVA_OPTS=-Xmx2g -Xms2g -XX:+UseConcMarkSweepGC -XX:NativeMemoryTracking=summary -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=5000 -Dcom.sun.management.jmxremote.rmi.port=5000 -Dcom.sun.management.jmxremote.local.only=false -Djava.rmi.server.hostname=<HOST_IP>" -e SPRING_PROFILE=-Dspring.profiles.active=dev
      vhussein/test-client  

    > **Note**: host.docker.internal is a workaround for mac, for windows, you may want to check your docker config. I'm using Docker Toolbox so my IP is 192.169.99.102
