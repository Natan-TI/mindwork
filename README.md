# 🧠 MindWork API -- Global Solution 2025/2

API em **Spring Boot 3** para monitorar o bem-estar emocional de
colaboradores, ajudando empresas a promover um ambiente de trabalho mais
saudável e alinhado ao tema **"O Futuro do Trabalho"**.

A solução permite que:

-   colaboradores registrem seu **estado emocional** ao longo do tempo;
-   métricas como **nível de estresse, horas de sono e tempo de tela**
    sejam monitoradas;
-   empresas analisem, de forma **agregada e anonimizada**, tendências
    de bem-estar, identificando riscos e oportunidades de intervenção.

------------------------------------------------------------------------

## 🚀 Tecnologias Utilizadas

-   **Java 21**
-   **Spring Boot 3.5.x**
    -   Spring Web
    -   Spring Data JPA
    -   Spring Security (JWT / Stateless)
    -   Validation
-   **PostgreSQL** (via Docker)
-   **Hibernate**
-   **Lombok**
-   **springdoc-openapi** (Swagger UI) -- Documentação da API
-   **Maven**

------------------------------------------------------------------------

## 🏗 Arquitetura e Domínio

Camadas principais:

-   `entities` -- Entidades de domínio (`User`, `Organization`,
    `MoodEntry`)
-   `enums` -- Tipos enumerados (`Role`, `MoodState`, `DataSourceType`)
-   `repositories` -- Interfaces JPA
-   `services` / `services.impl` -- Regras de negócio e casos de uso
-   `controllers` -- Endpoints REST
-   `security` -- Configuração de segurança, JWT, filtros
-   `exceptions` -- Exceções de domínio + tratamento global
-   `dto` -- DTOs de request/response
-   `config` -- Configurações adicionais (OpenAPI, Seed, etc.)

### Entidades principais

-   **Organization**
    -   `id`
    -   `name`
    -   `createdAt`
    -   relação com `User`
-   **User**
    -   `id`
    -   `name`
    -   `email`
    -   `password` (hash Bcrypt)
    -   `role` (`ADMIN` ou `EMPLOYEE`)
    -   `organization`
    -   `createdAt`
    -   `consentGivenAt`
    -   `pseudonymousKey` (chave pseudonimizada para proteção de dados)
-   **MoodEntry**
    -   `id`
    -   `user`
    -   `mood` (`MoodState`: CALM, STRESSED, ANXIOUS, etc.)
    -   `stressLevel`
    -   `sleepHours`
    -   `screenTimeMinutes`
    -   `notes`
    -   `source` (`DataSourceType`: MANUAL, WEARABLE, SYSTEM)
    -   `confidence`
    -   `createdAt`

------------------------------------------------------------------------

## 🔐 Segurança

A API implementa:

-   Autenticação baseada em **JWT (Bearer Token)**;
-   Política de sessão **STATELESS**;
-   Controle de acesso por **perfil (ROLE)**:
    -   `ADMIN`
    -   `EMPLOYEE`

### Perfis e permissões (resumo)

-   **ADMIN**
    -   Criar e gerenciar `Organization`
    -   Listar e gerenciar `User`
    -   Visualizar dados agregados de `MoodEntry` por organização
-   **EMPLOYEE**
    -   Registrar suas próprias entradas de humor (`MoodEntry`)
    -   Consultar histórico pessoal de humor

### Seed de usuário ADMIN

Na inicialização, a aplicação cria automaticamente:

-   Usuário **ADMIN**
-   Organização "MindWork Admin Org"

Credenciais padrão (podem ser alteradas em produção):

``` text
Email: admin@mindwork.com
Senha: admin123
```

------------------------------------------------------------------------

## 🗄️ Configuração do Banco (PostgreSQL + Docker)

Suba um container PostgreSQL:

``` bash
docker compose up -d db
```

Configure o `application.properties`:

``` properties
spring.datasource.url=jdbc:postgresql://localhost:5432/mindwork
spring.datasource.username=mindwork
spring.datasource.password=mindwork
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT
jwt.secret=MINHA_CHAVE_SUPER_SECRETA_MINDWORK_1234567890
jwt.expiration=86400000
```

------------------------------------------------------------------------

## ▶️ Como executar o projeto

``` bash
mvn clean package
mvn spring-boot:run
```

Aplicação sobe em:

``` text
http://localhost:8080
```

------------------------------------------------------------------------

## 📚 Swagger / OpenAPI

A documentação da API é gerada automaticamente com
**springdoc-openapi**.

-   Swagger UI:\
    👉 `http://localhost:8080/swagger-ui/index.html`

-   OpenAPI JSON:\
    👉 `http://localhost:8080/v3/api-docs`

------------------------------------------------------------------------

## 🔑 Fluxo de Autenticação

1.  **Login como ADMIN (seed)**\
    `POST /auth/login`

    ``` json
    {
      "email": "admin@mindwork.com",
      "password": "admin123"
    }
    ```

    Resposta:

    ``` json
    { "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." }
    ```

2.  **Criar uma Organization** (ADMIN)\
    `POST /organizations`\
    Header: `Authorization: Bearer <TOKEN_ADMIN>`

    ``` json
    {
      "name": "Empresa X Tecnologia"
    }
    ```

3.  **Registrar um Employee**\
    `POST /users/register`

    ``` json
    {
      "organizationId": "UUID_DA_ORGANIZATION",
      "name": "Fulano da Silva",
      "email": "fulano@empresa.com",
      "password": "123456"
    }
    ```

4.  **Login como Employee**

5.  **Enviar uma MoodEntry**

------------------------------------------------------------------------

## 🔗 Principais Endpoints

-   `POST /auth/login`
-   `POST /users/register`
-   `/organizations/**` -- ADMIN
-   `/users/**` -- ADMIN
-   `/mood-entries/**`

------------------------------------------------------------------------

## ⚖️ Privacidade e Ética

-   Dados pseudonimizados
-   Consentimento informado
-   Foco em saúde mental, não monitoramento invasivo

------------------------------------------------------------------------

## 👥 Equipe

<table>
  <tr>
    <td align="center">
        <sub>
          <b>João Pedro Marques Rodrigues - RM98307</b>
          <br>
        </sub>
        <sub>
          <b>Natan Eguchi dos Santos - RM98720</b>
          <br>
        </sub>
        <sub>
          <b>Kayky Paschoal Ribeiro - RM99929</b>
          <br>
        </sub>
    </td>
  </tr>
</table>
