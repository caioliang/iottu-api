# 🏍️ Sistema de Gerenciamento de Motos em Pátio

Este projeto é uma API REST desenvolvida com **Spring Boot** para gerenciar o controle de **motos**, **tags RFID**, **antenas de rastreamento** e **pátios de armazenamento**. Ideal para aplicações que exigem rastreamento em tempo real, controle de acesso e histórico de movimentações de motos.

---

## 📦 Tecnologias Utilizadas

- Java 17  
- Spring Boot 3  
- Spring Data JPA  
- Hibernate  
- Banco de dados Oracle (via JDBC/EF)  
- Jakarta Validation  
- Lombok  
- Swagger / OpenAPI  
- Maven  

---

## 🧩 Estrutura do Projeto

```
br.com.fiap.iottu_api
├── config              # Configurações de inicialização e seed
├── controller          # Endpoints REST 
├── dto                 # Data Transfer Objects 
├── model               # Entidades JPA
├── repository          # Interfaces de acesso a dados
├── service             # Regras de negócio 
├── validation          # Validações personalizadas
└── IottuApiApplication.java
```

---

## 🗃️ Entidades Principais

### ✅ Moto
- `modelo`, `placa`, `ano`, `fabricante`
- `status`: `ATIVA` ou `DESATIVADA` (usando enum)
- Relacionamento `@OneToOne` com `TagMoto`

### 🎫 TagMoto
- Informações de RFID, coordenadas e observações
- Vinculada a uma moto e a um pátio

### 🏢 Patio
- Dados de localização e capacidade
- Relacionamentos com `TagMoto` e `Antena`

### 📡 Antena
- Responsável por rastreamento de localização

---

## 🚀 Como Executar

1. **Clone o repositório**
   ```bash
   git clone https://github.com/caioliang/iottu-api.git
   cd iottu-api
   ```

2. **Compile e execute**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Acesse o Swagger (OpenAPI)**
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

---

## 🧪 Populando o Banco com Dados de Teste

A classe `DatabaseSeeder` é executada automaticamente na inicialização e insere:

- 3 pátios  
- 10 motos com tags associadas  
- 3 antenas  
- Status das motos: aleatório entre `ATIVA` e `DESATIVADA`  

---

## 📌 Melhorias Futuras

- CRUD completo via REST (Moto, Tag, Antena, Pátio)  
- Histórico de movimentação por antena  
- Dashboard com localização em tempo real  
- Integração com hardware (leitores RFID)  
- Autenticação e segurança (JWT)  

---

## 📄 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

## 👨‍💻 Autores

Desenvolvido por [Caio Liang](https://github.com/caioliang), [Allan Brito](https://github.com/Allanbm100) e [Levi Magni](https://github.com/levmn) - Projeto acadêmico Mottu - FIAP - 2025
