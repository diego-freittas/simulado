# Usa a imagem base do OpenJDK 17
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR para dentro do container
# Substitua 'app.jar' pelo nome do seu arquivo, se for diferente
COPY target/simulado-0.0.1-SNAPSHOT.jar /app/app.jar

# Define as variáveis de ambiente para a conexão com o PostgreSQL
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres/simulado
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres11235813
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Expõe a porta que sua aplicação utiliza (por exemplo, 8080)
EXPOSE 8080

# Define o comando de inicialização
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
