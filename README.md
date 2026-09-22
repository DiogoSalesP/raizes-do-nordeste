# Raízes do Nordeste

API REST desenvolvida em Java com Spring Boot para gerenciamento de uma rede de lanchonetes. O sistema contempla clientes, produtos, pedidos, pagamentos, estoque, unidades e funcionários, além de autenticação e um fluxo de pagamento simulado (mock).

---

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina antes de prosseguir:

* **Linguagem:** Java 21
* **Gerenciador de dependências:** Maven 3.8+
* **Banco de Dados:** PostgreSQL 15+
* **Outras ferramentas:** Git, Docker (opcional, para rodar o banco localmente)

---

## Clonando o projeto

Clone o repositório:

git clone https://github.com/DiogoSalesP/raizes-do-nordeste.git

Entre na pasta do projeto:

cd raizes-do-nordeste

## Configuração de Variáveis de Ambiente

O projeto utiliza um arquivo `.env` para gerenciar as credenciais do banco de dados e outras configurações sensíveis

1. Na raiz do projeto, localize o arquivo de exemplo chamado `.env.example`.
2. Crie uma cópia deste arquivo e renomeie para `.env`:
   ```bash
   cp .env.example .env
   ```
3. Abra o arquivo `.env` e preencha as variáveis com as credenciais do seu ambiente local:
   ```ini
    # Configurações do Banco de Dados (PostgreSQL)
    POSTGRES_DB=raizesdonordeste
    POSTGRES_USER=postgres
    POSTGRES_PASSWORD=postgres
    
    # Configurações do pgAdmin
    PGADMIN_DEFAULT_EMAIL=admin@admin.com
    PGADMIN_DEFAULT_PASSWORD=admin
   ```

---

## Instalação das dependências

O projeto utiliza Maven para gerenciamento das dependências.

Linux/macOS: ./mvnw clean install

Windows: mvnw.cmd clean install

Caso o Maven esteja instalado globalmente:

  mvn clean install

O Maven fará o download das dependências definidas no pom.xml.

---

## Banco de dados PostgreSQL

PostgreSQL com Docker

A maneira recomendada para executar o banco é utilizando Docker.

Suba o PostgreSQL:

docker compose up -d postgres

Verifique se o container está em execução:

docker compose ps

Para visualizar os logs:

docker compose logs -f postgres

O banco será criado automaticamente de acordo com as configurações do docker-compose.yml.

---

## ▶️ Como Iniciar a API

Para rodar a aplicação em ambiente de desenvolvimento local, utilize o plugin do Spring Boot:

```bash
mvn spring-boot:run
```

A API inicializará e estará disponível em: `http://localhost:8080` (ou na porta definida no seu `.env`).

---

## 📚 Documentação (Swagger/OpenAPI)

A documentação interativa dos endpoints da API foi gerada com a biblioteca Springdoc OpenAPI. Com a aplicação rodando, acesse os links abaixo no seu navegador:

* **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) *(ou `/swagger-ui/index.html` dependendo da configuração)*
* **JSON OpenAPI:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

Na interface, você encontrará todos os endpoints, esquemas de requisição/resposta, validações e poderá testar as rotas da API diretamente pelo navegador.

---
