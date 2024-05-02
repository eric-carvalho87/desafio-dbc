# Sistema de Enquetes

Este é um sistema de enquetes simples desenvolvido em Java, utilizando o framework Spring Boot e banco de dados H2 para persistência.

## Funcionalidades

- Criar uma nova enquete com título
- Abrir uma sessão de votação
- Votar em uma enquete existente
- Listar todas as enquetes disponíveis
- Visualizar os resultados de uma enquete

## Pré-requisitos

- Java 11
- Maven
- Git
- IntelliJ IDEA (opcional, mas recomendado)

## Instalação e Execução

1. Clone o repositório:

```bash
git clone https://github.com/eric-carvalho87/desafio-dbc.git
```

2. Navegue até o diretório do projeto:

```bash
cd desafio-dbc
```

3. Execute o aplicativo Spring Boot:

```bash
mvn spring-boot:run
```

O aplicativo estará disponível em http://localhost:8080.  

## Exemplos de Uso

### Criar uma nova enquete

```bash
curl -X POST -H "Content-Type: application/json" -d '{"title":"Minha Enquete"}' http://localhost:8080/api/polls
```
### Votar em uma enquete

```bash
curl -X POST -H "Content-Type: application/json" -d '{"pollId":"<ID_DA_ENQUETE>","votingOption":<ID_DA_OPCAO_DE_VOTO>,"cpfNumber":"<ID_DO_USUARIO>"}' http://localhost:8080/api/vote
```

### Iniciar uma sessão

```bash
curl -X POST -H "Content-Type: application/json" -d '{"timeSession":<TEMPO_EM_SEGUNDOS>}' http://localhost:8080/api/polls/<ID_DA_ENQUETE>/start
```

### Listar todas as enquetes

```bash
curl http://localhost:8080/api/polls
```

### Visualizar os resultados de uma enquete

```bash
curl http://localhost:8080/api/polls/<ID_DA_ENQUETE>/result
```