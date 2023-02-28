FROM openjdk:11

RUN apt-get update && apt-get install nano

ENV TZ=Asia/Bangkok
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ADD target/*.jar /usr/src/app/entrypoint.jar

WORKDIR /usr/src/app/

ENV SECRET_DB_IP=172.17.0.1
ENV SECRET_DB_PORT=3306
ENV SECRET_DB_USERNAME=thitiwas
ENV SECRET_DB_PASSWORD=P@ssw0rd
ENTRYPOINT ["java","-jar", "/usr/src/app/entrypoint.jar"]
