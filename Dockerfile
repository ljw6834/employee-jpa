FROM openjdk:8
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.7.3/wait /wait
RUN chmod +x /wait
COPY ./target/employee-jpa-0.0.1-SNAPSHOT.jar employee-jpa-0.0.1-SNAPSHOT.jar
CMD /wait && java -jar employee-jpa-0.0.1-SNAPSHOT.jar
