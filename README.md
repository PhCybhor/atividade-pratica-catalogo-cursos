# Catálogo de Cursos

Atividade prática — Programação com Java e Spring Boot (Aulas 00 a 14).

Aplicação Spring Boot independente que modela a relação **1:N** entre Instrutor e Curso, com PostgreSQL, Liquibase, API REST e testes.

## Componentes do grupo

- Pedro Henrique Quarentani
- Dan Souza Silva
- Pedro Henrique Rodrigues da Silva

## Repositório

Projeto Maven (`br.edu:catalogocursos`), Java 21, Spring Boot 4.

## Como executar

1. Subir PostgreSQL e criar o banco `catalogocursos_dev`
2. Copiar `.env.example` para `.env` e ajustar as credenciais
3. Rodar com o perfil `dev`:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

## Testes

```bash
./mvnw clean test
```
