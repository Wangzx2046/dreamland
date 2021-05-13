FROM openjdk:8-jdk-alpine
ENV TZ=Asia/Shanghai
ENV LANG C.UTF-8
ADD ./dreamland-api/target/devilMayCry.jar devilMayCry.jar
ENTRYPOINT ["java","-Dfile.encoding=UTF-8","-Dsun.jnu.encoding=UTF-8","-Dspring.profiles.active=prod", "-jar", "-Dserver.port=8088", "devilMayCry.jar"]
EXPOSE 8088