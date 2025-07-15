# Projeto: Servidor de recursos
Este projeto é responsável por cadastro de usuários e clientes (ClientId e ClientSecret) e trabalha junto com resource e authorizarion server.

* Funcionalidades
  * Cadastro de usuário:    
    Cadastro, consulta de lista de usuários, consulta simples, atualização e exclusão.
  * Cadastro de clientes (ClientId e ClientSecret):
    Cadastro, consulta de lista de clientes, consulta simples, atualização e exclusão.
  * Suporte a múltiplos grant types:    
    Authorization Code, Client Credentials, Refresh Token, e outros.

* Dependências:
  * [Authorization Server](https://github.com/alberes/register-manager-authorization-server)
  * [Resource Server](https://github.com/alberes/register-manager-resource-server)

### Tecnologias Utilizadas
* Linguagem:
    * Java
    * Frameworks:
        * [Spring Boot 3.5.0](https://start.spring.io/)
    * Dependencias:
        * [Spring Security](https://docs.spring.io/spring-security/reference/index.html)
        * [Spring Oauth2 Client](https://docs.spring.io/spring-security/reference/servlet/oauth2/client/index.html)
        * [Lombok](https://projectlombok.org/features/)
* JDK:
    * versão 17
* IDE:
    * [Intellij](https://www.jetbrains.com/idea/)
* Gerenciado de dependencias:
    * [Apache Maven 3.9.9](https://maven.apache.org/)
* Container:
    * [Docker](https://www.docker.com/)
    * [Docker Hub](https://hub.docker.com/)
* Ferramentas:
    * [Postman](https://www.postman.com/)
    * [Google Chrome Versão 136.0.7103.93 Versão oficial 64 bits](https://www.google.com/intl/pt-BR/chrome/)
    * [PGAdmin](https://www.pgadmin.org/)
    * [Beekeeper](https://www.beekeeperstudio.io/)
* Ferramentas auxiliares:
    * [Markdown](https://stackedit.io/app#)
    * [Criar diagramas](https://docs.github.com/pt/get-started/writing-on-github/working-with-advanced-formatting/creating-diagrams)
    * [JWT IO](https://jwt.io/)
    * [Criptografia online](https://bcrypt-generator.com/)
    * [Base 64](https://www.base64encode.org/)
    * [4DEV](https://www.4devs.com.br/)

### Como Executar
1. Clone o repositório: git clone [register-manager-frontend-client](https://github.com/alberes/register-manager-frontend-client)

2. Executar o projeto (ver dependências)
- Abrir o terminal no diretório raiz do projeto [SUB_DIRETORIOS]/register-manager-frontend-client e executar o comando abaixo para gerar o pacote.
```
mvn -DskipTests=true clean package
```
- No termial entrar no diretório [SUB_DIRETORIOS]/register-manager-frontend-client/target
```
java -jar register-manager-frontend-client-0.0.1-SNAPSHOT.jar
```

A aplicação subirá na porta 8080

### Testes
1. Sistema:
    Acessar a URL http://127.0.0.1:8080/home

1. Montando um ambiente Docker (alterar a variável de ambiente active para docker no arquivo application.yaml)
   <a id="criar-register-manager-frontend-client"></a>
- Criando uma imagem da aplicação
```
docker build --tag register-manager-frontend-client:1.0.0 .
```
- Criando uma rede para comunicação entre os containeres
```
docker network create register-manager-authorization-network
```
- Subindo um container Docker da aplicação register-manager-resource-server
```
docker run --name register-manager-frontend-client -p 8080:8080 --network register-manager-authorization-network -d register-manager-frontend-client:1.0.0
```
- ou use as veriáveis de ambiente
```
docker run --name register-manager-frontend-client -p 8080:8080 --network register-manager-authorization-network -e ADMIN_CLIENT_ID=admin-client-id -e ADMIN_CLIENT_SECRET=admin-client-secret -e AUTHORIZATION_CODE=authorization_code -e "SCOPE=openid, profile, email, address, phone" -e CLIENT_NAME=register-manager-frontend-client-oidc -e ISSUER_URI=http://localhost:9090 -e LOG_NAME=register-manager-frontend-client.log -e LOG_LEVEL=warn -e REGISTER_MANAGER_RESOURCE_URL=http://localhost:9080 -d register-manager-frontend-client:1.0.0
``` 
- Listando os containeres que estão no ar
```
docker ps
``` 
- Listando as imagens
```
docker images
```
- Parando o contaner
```
docker stop register-manager-resource-server
```
- Subindo um container já existente
```
docker start register-manager-resource-server
```
3. Demontando o ambiente (Pare os containeres da aplicação e banco de dados antes de executar os comandos abaixo)
    - Removendo a aplicação
```
docker rm register-manager-resource-server
```
- Removendo o banco de dados
```
docker rm postgresdb
```
- Removendo a rede
```
docker network rm register-manager-authorization-network
```
- Removendo a imagem da aplicação
```
docker rmi register-manager-resource-server:1.0.0
```
- Removendo a imagem do banco de dados
```
docker rmi postgres:16.3
```
### Docker Compose para gerenciar os containeres
- Com a imagem da aplicação [register-manager-resource-server](#criar-register-manager-resource-server) é só executar o comando abaixo:
```   
docker-compose up -d
```
- Parando a aplicação com docker compose
```
docker-compose down
```