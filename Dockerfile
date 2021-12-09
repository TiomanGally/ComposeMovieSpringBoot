FROM adoptopenjdk/openjdk11:slim
RUN mkdir /app
COPY /target/*.jar /app/app.jar
CMD ["java","-jar","/app/app.jar"]
