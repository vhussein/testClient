# testClient

### Prerequisite:
1. You would need to have docker install on your machine https://docs.docker.com/get-started/

2. Once installed, run the docker-compose.yml in src/main/resources/db/docker-compose.yml. This will create a mysql image in your local docker.

3. After the mysql container is created & started, you can run the src/main/resources/db/db-init/init.sql

4. Subsequently you can run another script to create the table src/main/resources/db/init.sql

5. Before you can deploy the image, do a _mvn clean install_

6. Once the artefact (*.jar) is created, you can run the following command:

    > docker build -t images/testclient .
    && docker run
    -p 8080:8080
    --env MYSQL_HOST=host.docker.internal
    --env MYSQL_USERNAME=pcdbuser
    --env MYSQL_PWD=pcdbuser
    --name testclient
    -m 4g
    --cpus 1
    images/testclient 

    > **Note**: host.docker.internal is a workaround for mac, for windows, you may want to check your docker config
