steps using Dockerfile to create mysqldb container, employee-jpa image and employee-jpa-container
1. create a network named employee-mysql
       docker network create employee-mysql
2. create mysqldb container, check container logs, create a user in database:
       docker run --name mysqldb --network employee-mysql -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=testdb -d mysql:8
       docker container logs -f <container name>
       docker exec -it mysqldb bash
       mysql -uroot -p1234

3. build spring boot app image using Dockerfile
        docker build -t employee-jpa .
4. create employee-jpa-container using image employee-jpa
5. insert data using POST request using curl and check using GET
        curl --header "Content-Type: application/json"   --request POST   --data '{"empName":"Bill Black"}'   http://localhost:8080/saveEmp
        curl --header "Content-Type: application/json"  --request GET http://localhost:8080/

Steps to use docker-componse.yml
1. You have to have images created before you can use docker-compose.yml, the image for
mysql already exists in docker hub. You have to build your spring boot app image using
Dockerfile first.
       docker build -t employee-jpa .
       docker images
2. Because in docker-compose.yml file, when we start two services, mysql database is not
ready to connect, we have to add a command line utility to wait for database to be ready, then
start our spring boot app. So in Dockerfile, I have to add these two lines:
        ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.7.3/wait /wait
        RUN chmod +x /wait
also change this line:
from "CMD java -jar employee-jpa-0.0.1-SNAPSHOT.jar" to "CMD /wait && java -jar employee-jpa-0.0.1-SNAPSHOT.jar"
In docker-compose.yml, add two line in the employee-jpa service:
        environment:
              WAIT_HOSTS: mysqldb:3306

3. create and start both containers: mysqldb and employee-jpa-conatainer
        docker-compose up
3. use curl command to insert data and check data in table
        curl --header "Content-Type: application/json"   --request POST   --data '{"empName":"Bill Black"}'   http://localhost:8080/saveEmp
        curl --header "Content-Type: application/json"  --request GET http://localhost:8080


Steps to create new user in database. This step is not necessary since in the application.properties,
we use root as user. I just put here so that I don't forget the commands:
        create user 'tester'@'%' identified by '1234';      ----create a user with username and password
        grant all on *.* to 'tester'@'%';                   ----grant all privileges
        select user, host from mysql.user;                  ----check user


