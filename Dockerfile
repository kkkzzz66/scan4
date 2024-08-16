FROM openjdk:21-bullseye
ENV TZ Asia/Shanghai
WORKDIR /data
COPY target/ROOT.jar .
CMD ["java","-jar","-Djava.security.egd=file:/dev/./urandom","ROOT.jar"]