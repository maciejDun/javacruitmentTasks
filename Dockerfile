FROM openjdk:17-ea-10-jdk-alpine3.13
ADD javacruitment-application/build/libs/javacruitment-application.jar .
EXPOSE  8000
CMD java -jar javacruitment-application.jar