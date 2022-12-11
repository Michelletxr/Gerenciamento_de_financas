FROM sapmachine:latest
COPY target/financas_api-0.0.1-SNAPSHOT.jar financas_api-1.0.0.jar
ENTRYPOINT ["java","-jar","/financas_api-1.0.0.jar"]
#expondo a porta que a imagem deve rodar
EXPOSE 8080