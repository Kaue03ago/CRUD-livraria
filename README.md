
# CRUD-livraria

Está é uma implementação de um CRUD de uma livraria, onde possui resquest como POST, GET, DELETE E PUT. Nele existem algumas funcionalidades básicas, porém fundamentais 

# Preparação Ambiente:
- JDK 11 ou seperior
- Gradle
- Postman ou similar 
- H2
- IDE (IntelliJ, STS, Eclipse, etc)

## Instalação e Execução 

#### 1- Clone o repositório 

```http
 1. https://github.com/Kaue03ago/CRUD-livraria.git
```
#### 2- Navegue até o diretório do projeto:

```http
 cd CRUD-livraria
```

#### 3- Em sua IDE ou terminal, execute o seguinte comando para construir o projeto:
```http
./gradlew build 
```
#### 4- Por final execute pela sua IDE ou a seguinte linha de comando:
```http
./gradlew bootRun
```

#### 6- Abra o Postman para testar as funcionalidades 

# Endpoints da API

| Parâmetro                 | Descrição                                    |
| :----------               | :----------                                  |
| POST /inserir          |`` Adiciona novos livros                    ``    |
| GET /listarLivrosTitulo           | ``Retorna todos os titulos dos livros disponiveis        ``          |
| GET /listarTodosLivros      | ``Retorna todos os livros e suas informações       ``                 |
| PUT /products/{id}      | ` Atualiza um produto existente pelo ID.`    |
| DELETE /products/{id}   | ``Deleta um livro existente pelo ID.       ``|
| DELETE /products        | ``Deleta todos os livros``                   |



#   Exemplo de requisições

##### Utilizando o Postman.

| Função                    |  Comando                           | 
| :----------               | :----------                        | 
| POST                    | ``http://http://localhost:8080/livraria/inserir``  |
| GET                     | ``http://http://localhost:8080/livraria/listarLivrosTitulo``  |
| GET                     | ``http://http://localhost:8080/livraria/listarTodosLivros   ``| 
GET                     | ``http://localhost:8080/livraria/listarLivro/{id}``                |
| PUT           | ``http://localhost:8080/livraria/editar/{id}              ``         |
| DELETE           | ``http://localhost:8080/livraria/deletarTodos``                        |
| DELETE           | ``http://localhost:8080/livraria/deletar/{id}``                        |