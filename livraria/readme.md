# 📖 Livraria API

API REST em Java com Spring Boot para gestão de livros, integração com fontes externas (Google Books, Open Library) e aplicação de padrões SOLID e boas práticas de arquitetura.

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

**`GoogleBookAdapter`**: converte apenas DTO em entidade `Book`. Não faz chamadas nem salva nada.

### O — Open/Closed Principle

**`BookSeedServiceImpl`**: permite adicionar novas fontes de livros sem alterar as existentes.

### L — Liskov Substitution Principle

**`BookProviderFacade`**: permite substituir `GoogleBooksFacade` por `OpenLibraryFacade` sem quebrar o código.

### I — Interface Segregation Principle

**`BookSeedService`**: define métodos específicos e pequenos (Google, OpenLibrary), podendo ser implementados separadamente.

### D — Dependency Inversion Principle

**`BookSeedServiceImpl`** depende de `facade` (abstração de integração), não do `RestTemplate` ou JSON.

---

## 🧰 Design Patterns aplicados

* **Facade**: `GoogleBooksFacade` e `OpenLibraryFacade` escondem a complexidade da integração externa
* **Adapter**: `GoogleBookAdapter` e `OpenLibraryAdapter` convertem DTO externo para modelo de domínio
* **DTO**: `GoogleBooksResponse`, `OpenLibraryResponse` encapsulam os dados de entrada externa
* **Builder**: utilizado em `Book.builder()` para construção imutável de objetos

---

## 🏫 Pilares da Orientação a Objetos

### ✍ Encapsulamento

Entidades como `Book` encapsulam seus dados e usam `@Getter/@Setter` via Lombok.

### 🔗 Herança

Aplicado indiretamente com record types reutilizando estruturas (ex: `VolumeInfo`, `Doc`).

### ✨ Polimorfismo

`BookProviderFacade` permite múltiplas implementações (Google, Open) com o mesmo contrato.

### ⚖️ Abstração

Interfaces como `BookSeedService` e `BookProviderFacade` expõem comportamentos sem expor implementações.

---

Para dúvidas ou sugestões, abra uma issue ou envie um PR ✨
