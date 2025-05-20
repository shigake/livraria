# 📖 Livraria API

API REST em Java com Spring Boot para gestão de livros, integração com fontes externas (Google Books, Open Library) e aplicação de padrões SOLID, Design Patterns e arquitetura limpa.

---

## ⚡ Tecnologias

* Java 17
* Spring Boot 3.x
* Spring Data JPA
* MySQL
* Swagger/OpenAPI
* Lombok

---

## ⚙️ Como executar a aplicação

### 1. Clone o repositório:

```bash
git clone https://github.com/shigake/livraria.git
cd livraria
```

### 2. Configure o banco MySQL

Crie um banco de dados chamado `livraria` e ajuste o `application.yml` com seu `username` e `password`.

### 3. Rode a aplicação

```bash
./mvnw spring-boot:run
```

---

## 📃 Documentação da API

Disponível em:

```
http://localhost:8080/swagger-ui.html
```

---

## ✅ Testes (em implementação)

Os testes serão adicionados na próxima etapa usando JUnit 5 e Mockito.

---

## 🔧 Exemplos práticos de SOLID

### S — Single Responsibility Principle

**`GoogleBookAdapter`** tem apenas a responsabilidade de converter um `GoogleBooksResponse.Item` em `Book`. Ele não realiza chamadas HTTP nem acessa o banco de dados. Isso facilita testes e manutenção.

### O — Open/Closed Principle

**`BookSeedServiceImpl`** pode ser extendido para novos provedores de livros (ex: Gutendex) sem alterar as classes existentes, apenas adicionando novas implementações do `BookProviderFacade`.

### L — Liskov Substitution Principle

Qualquer classe que implemente `BookProviderFacade` (como `GoogleBooksFacade`, `OpenLibraryFacade`) pode ser usada sem quebrar o contrato esperado pelo serviço. Ou seja, `BookSeedServiceImpl` não precisa saber qual implementação está usando.

### I — Interface Segregation Principle

A interface `BookSeedService` separa métodos de seeding de fontes distintas (`seedBooksFromGoogle`, `seedBooksFromOpenLibrary`) permitindo implementações específicas sem forçar métodos desnecessários.

### D — Dependency Inversion Principle

`BookSeedServiceImpl` depende de `BookProviderFacade`, uma abstração de provedor de livros, em vez de depender diretamente de `GoogleBooksClient` ou `RestTemplate`. Isso facilita a inversão de dependência, testes e extensibilidade.

---

## 🧰 Design Patterns aplicados

* **Facade**: `GoogleBooksFacade` e `OpenLibraryFacade` encapsulam chamadas HTTP, parsing e conversão de dados em uma interface simples `fetchBooks()`.
* **Adapter**: `GoogleBookAdapter` e `OpenLibraryAdapter` transformam as estruturas externas (DTOs) em entidades `Book` do domínio.
* **Factory**: `BookProviderFactory` escolhe dinamicamente qual implementação de `BookProviderFacade` usar com base em um enum `BookProvider`.
* **DTO**: `GoogleBooksResponse`, `OpenLibraryResponse` modelam as respostas das APIs externas sem acoplar a estrutura ao domínio.
* **Builder**: uso de `Book.builder()` para criação fluente e imutável dos objetos de domínio.

---

## 🏫 Pilares da Orientação a Objetos

### ✍ Encapsulamento

As entidades de domínio (ex: `Book`) mantém seus atributos privados com acesso controlado por meio de getters/setters, usando `@Getter` e `@Setter` do Lombok.

### 🔗 Herança

Embora o projeto não utilize herança clássica, reuso de estrutura acontece via records aninhados e abstrações como `BookProviderFacade`, que são substituíveis.

### ✨ Polimorfismo

`BookProviderFacade` permite que várias classes (Google, OpenLibrary) implementem o mesmo contrato. O código do serviço permanece o mesmo, não importando qual implementação está por trás.

### ⚖️ Abstração

Interfaces como `BookSeedService` e `BookProviderFacade` escondem os detalhes das implementações concretas e permitem a evolução do sistema sem impacto direto.

---

Para dúvidas ou sugestões, abra uma issue ou envie um PR ✨
