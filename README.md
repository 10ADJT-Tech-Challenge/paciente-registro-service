# Serviço de Registro de Paciente (paciente-registro-service)

Este projeto é um microserviço Spring Boot para gerenciar o registro de pacientes.

## Tecnologias Utilizadas

* **Java 25**: Plataforma de desenvolvimento.
* **Spring Boot**: Framework para criação de aplicações Java.
* **Spring Data JPA**: Para persistência de dados.
* **Spring MVC**: Para a criação de endpoints REST.
* **PostgreSQL**: Banco de dados relacional.
* **RabbitMQ**: Sistema de mensageria para comunicação assíncrona.
* **Flyway**: Ferramenta para versionamento e migração de banco de dados.
* **Lombok**: Biblioteca para reduzir código boilerplate em Java.
* **Maven**: Ferramenta de automação de compilação.
* **Docker**: Para containerização da aplicação e seus serviços dependentes.
* **SpringDoc OpenAPI**: Para documentação da API (Swagger).

## Configuração

O serviço é configurado através de variáveis de ambiente. Você pode criar um arquivo `.env` na raiz do projeto, baseado no `.env-example`, para definir suas configurações locais.

As principais variáveis são:

* `DB_HOST`: Host do banco de dados PostgreSQL.
* `DB_PORT`: Porta do banco de dados PostgreSQL.
* `DB_NAME`: Nome do banco de dados.
* `DB_USER`: Usuário do banco de dados.
* `DB_PASSWORD`: Senha do banco de dados.
* `RABBITMQ_HOST`: Host do RabbitMQ.
* `RABBITMQ_PORT`: Porta do RabbitMQ.
* `RABBITMQ_USER`: Usuário do RabbitMQ.
* `RABBITMQ_PASSWORD`: Senha do RabbitMQ.

## Como Executar

### Usando Docker Compose

A maneira mais simples de executar o projeto e suas dependências (PostgreSQL e RabbitMQ) é utilizando Docker Compose.

1. Certifique-se de ter o Docker e o Docker Compose instalados.
2. Execute o comando na raiz do projeto:

   ```bash
   docker-compose up -d
   ```

### Executando Localmente

1. Certifique-se de ter instâncias do PostgreSQL e RabbitMQ em execução e acessíveis pela aplicação.
2. Configure as variáveis de ambiente (conforme a seção "Configuração").
3. Execute a aplicação usando sua IDE ou via linha de comando com o Maven:

   ```bash
   ./mvnw spring-boot:run
   ```

## Documentação da API

A documentação da API está disponível via Swagger UI. Após iniciar a aplicação, você pode acessá-la no seguinte endereço:

* **Swagger UI**: [http://localhost:8080/v1/swagger-ui.html](http://localhost:8080/v1/swagger-ui.html)
* **OpenAPI Spec (JSON)**: [http://localhost:8080/v1/api-docs](http://localhost:8080/v1/api-docs)
