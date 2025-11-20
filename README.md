# Sistema de Controle de Recursos Humanos

Sistema completo de gestão de recursos humanos desenvolvido com Spring Boot, oferecendo uma interface web moderna e intuitiva para gerenciar funcionários, cargos e departamentos.

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Funcionalidades](#funcionalidades)
- [Modelo de Dados](#modelo-de-dados)
- [Como Executar](#como-executar)
- [Endpoints da API](#endpoints-da-api)
- [Interface Web](#interface-web)
- [Regras de Negócio](#regras-de-negócio)
- [Tratamento de Exceções](#tratamento-de-exceções)

## 🎯 Sobre o Projeto

Sistema de gestão de recursos humanos que permite o cadastro, edição, exclusão e consulta de funcionários, cargos e departamentos. O sistema foi desenvolvido seguindo o padrão MVC (Model-View-Controller) e arquitetura em camadas, proporcionando uma base sólida e escalável.

### Características Principais

- ✅ Interface web responsiva com Bootstrap 5
- ✅ API REST completa com documentação Swagger
- ✅ Tratamento centralizado de exceções
- ✅ Validação de dados no backend
- ✅ Paginação e filtros de busca
- ✅ Relacionamentos entre entidades (JPA)
- ✅ Banco de dados H2 em memória

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Data JPA** - Persistência de dados
- **H2 Database** - Banco de dados em memória
- **Lombok** - Redução de boilerplate
- **MapStruct** - Mapeamento de objetos
- **SpringDoc OpenAPI** - Documentação Swagger

### Frontend
- **HTML5**
- **CSS3** (Bootstrap 5.3.0)
- **JavaScript (ES6+)** - Vanilla JS
- **Bootstrap Icons** - Ícones

## 📁 Estrutura do Projeto

```
rh-sistema/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/empresa/rh/
│   │   │       ├── config/              # Configurações
│   │   │       ├── controller/          # Controllers REST
│   │   │       │   ├── dtos/            # Data Transfer Objects
│   │   │       │   ├── mapper/           # Mappers (MapStruct)
│   │   │       │   └── swagger/          # Documentação Swagger
│   │   │       ├── exception/           # Tratamento de exceções
│   │   │       ├── model/                # Entidades JPA
│   │   │       ├── repository/           # Repositórios JPA
│   │   │       ├── service/              # Lógica de negócio
│   │   │       └── RhSistemaApplication.java
│   │   └── resources/
│   │       ├── application.properties    # Configurações
│   │       └── static/                   # Frontend
│   │           ├── css/
│   │           ├── js/
│   │           ├── funcionarios/
│   │           ├── cargos/
│   │           ├── departamentos/
│   │           └── index.html
│   └── test/                             # Testes
└── pom.xml
```

## 🚀 Funcionalidades

### Funcionários
- ✅ Cadastrar funcionário
- ✅ Editar funcionário
- ✅ Excluir funcionário
- ✅ Procurar funcionário por ID
- ✅ Listar funcionários com paginação
- ✅ Filtrar funcionários por nome
- ✅ Associar funcionário a departamento, cargo e chefe

### Cargos
- ✅ Cadastrar cargo
- ✅ Editar cargo
- ✅ Excluir cargo
- ✅ Procurar cargo por ID
- ✅ Listar cargos com paginação
- ✅ Filtrar cargos por nome
- ✅ Listar todos os cargos (para dropdowns)

### Departamentos
- ✅ Cadastrar departamento
- ✅ Editar departamento
- ✅ Excluir departamento
- ✅ Procurar departamento por ID
- ✅ Listar departamentos com paginação
- ✅ Filtrar departamentos por nome
- ✅ Listar todos os departamentos (para dropdowns)

## 🗄️ Modelo de Dados

### Entidades e Relacionamentos

```
┌─────────────────┐
│   Funcionário   │
├─────────────────┤
│ id (PK)         │
│ nome            │
│ email           │
│ salario         │
│ departamento_id │──┐
│ cargo_id        │──┤
│ chefe_id        │──┼──┐
└─────────────────┘  │  │
                     │  │
        ┌────────────┘  │
        │               │
        ▼               │
┌──────────────┐        │
│ Departamento │        │
├──────────────┤        │
│ id (PK)      │        │
│ nome         │        │
│ descricao    │        │
└──────────────┘        │
                        │
        ┌───────────────┘
        │
        ▼
┌──────────────┐
│    Cargo     │
├──────────────┤
│ id (PK)      │
│ nome         │
│ descricao    │
└──────────────┘
```

### Relacionamentos

- **Funcionário ↔ Departamento**: Many-to-One (Muitos funcionários para um departamento)
- **Funcionário ↔ Cargo**: Many-to-One (Muitos funcionários para um cargo)
- **Funcionário ↔ Funcionário (Chefe)**: Many-to-One (Auto-relacionamento - um funcionário pode ter um chefe)

## 🏃 Como Executar

### Pré-requisitos

- Java 21 ou superior
- Maven 3.6+ (ou use o wrapper incluído: `mvnw`)

### Passo a Passo

1. **Clone o repositório** (ou navegue até a pasta do projeto)

2. **Compile o projeto**
   ```bash
   mvn clean install
   ```
   Ou usando o wrapper:
   ```bash
   ./mvnw clean install
   ```

3. **Execute a aplicação**
   ```bash
   mvn spring-boot:run
   ```
   Ou usando o wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Acesse a aplicação**
   - **Interface Web**: http://localhost:8080/index.html
   - **Swagger UI**: http://localhost:8080/swagger-ui/index.html
   - **H2 Console**: http://localhost:8080/h2-console
     - JDBC URL: `jdbc:h2:mem:rhdb`
     - Username: `sa`
     - Password: `sa`

## 📡 Endpoints da API

### Funcionários

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/rh/funcionario/listar` | Lista funcionários (com paginação e filtro por nome) |
| GET | `/rh/funcionario/{id}` | Busca funcionário por ID |
| POST | `/rh/funcionario` | Cria novo funcionário |
| PUT | `/rh/funcionario/{id}` | Atualiza funcionário |
| DELETE | `/rh/funcionario/{id}` | Exclui funcionário |

**Parâmetros de Listagem:**
- `page` (default: 0) - Número da página
- `size` (default: 10) - Tamanho da página
- `sortBy` (default: "id") - Campo para ordenação
- `nome` (opcional) - Filtro por nome

**Exemplo de Request (POST):**
```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "salario": 3500.0,
  "departamentoId": 1,
  "cargoId": 1,
  "chefeId": null
}
```

### Cargos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/rh/cargos/listarCargos` | Lista cargos (com paginação e filtro por nome) |
| GET | `/rh/cargos/todos` | Lista todos os cargos (sem paginação) |
| GET | `/rh/cargos/{id}` | Busca cargo por ID |
| POST | `/rh/cargos` | Cria novo cargo |
| PUT | `/rh/cargos/{id}` | Atualiza cargo |
| DELETE | `/rh/cargos/{id}` | Exclui cargo |

**Exemplo de Request (POST):**
```json
{
  "nome": "Desenvolvedor Backend",
  "descricao": "Responsável por implementar APIs e lógica de negócio"
}
```

### Departamentos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/rh/departamento/listar` | Lista departamentos (com paginação e filtro por nome) |
| GET | `/rh/departamento/todos` | Lista todos os departamentos (sem paginação) |
| GET | `/rh/departamento/{id}` | Busca departamento por ID |
| POST | `/rh/departamento` | Cria novo departamento |
| PUT | `/rh/departamento/{id}` | Atualiza departamento |
| DELETE | `/rh/departamento/{id}` | Exclui departamento |

**Exemplo de Request (POST):**
```json
{
  "nome": "Recursos Humanos",
  "descricao": "Responsável pela gestão de pessoas e processos internos"
}
```

## 🖥️ Interface Web

### Páginas Disponíveis

1. **Página Inicial** (`/index.html`)
   - Dashboard com cards de navegação
   - Acesso rápido às funcionalidades

2. **Funcionários**
   - `/funcionarios/listar.html` - Listagem com paginação e busca
   - `/funcionarios/cadastrar.html` - Formulário de cadastro
   - `/funcionarios/editar.html` - Formulário de edição
   - `/funcionarios/procurar.html` - Busca por ID

3. **Cargos**
   - `/cargos/listar.html` - Listagem com paginação e busca
   - `/cargos/cadastrar.html` - Formulário de cadastro
   - `/cargos/editar.html` - Formulário de edição
   - `/cargos/procurar.html` - Busca por ID

4. **Departamentos**
   - `/departamentos/listar.html` - Listagem com paginação e busca
   - `/departamentos/cadastrar.html` - Formulário de cadastro
   - `/departamentos/editar.html` - Formulário de edição
   - `/departamentos/procurar.html` - Busca por ID

### Características da Interface

- ✅ Design responsivo (mobile-first)
- ✅ Navegação intuitiva
- ✅ Mensagens de sucesso/erro amigáveis
- ✅ Modais de confirmação para exclusões
- ✅ Validação de formulários
- ✅ Paginação automática
- ✅ Busca em tempo real

## 📐 Regras de Negócio

### Funcionário
- ✅ Nome e email são obrigatórios
- ✅ Salário deve ser positivo
- ✅ Um funcionário pode pertencer a um departamento (opcional)
- ✅ Um funcionário pode ter um cargo (opcional)
- ✅ Um funcionário pode ter um chefe (opcional)
- ✅ Um funcionário não pode ser chefe de si mesmo
- ✅ CPF deve ser único (quando implementado)
- ✅ Data de admissão não pode ser futura (quando implementado)

### Cargo
- ✅ Nome é obrigatório
- ✅ Descrição é opcional

### Departamento
- ✅ Nome é obrigatório
- ✅ Descrição é opcional

## ⚠️ Tratamento de Exceções

O sistema possui tratamento centralizado de exceções através do `GlobalExceptionHandler`:

### Exceções Customizadas

- **ResourceNotFoundException**: Quando um recurso não é encontrado (404)
- **BusinessException**: Para erros de regra de negócio (400)

### Respostas de Erro Padronizadas

```json
{
  "timestamp": "2025-11-20T01:04:54.3638335",
  "status": 404,
  "error": "Recurso não encontrado",
  "message": "Funcionário com ID 77 não encontrado",
  "path": "/rh/funcionario/77"
}
```

### Tipos de Erro Tratados

- ✅ Validação de dados (`MethodArgumentNotValidException`)
- ✅ Violação de constraints (`ConstraintViolationException`)
- ✅ Tipo de parâmetro inválido (`MethodArgumentTypeMismatchException`)
- ✅ Recurso não encontrado (`ResourceNotFoundException`)
- ✅ Erros de negócio (`BusinessException`)
- ✅ Erros genéricos (`RuntimeException`, `Exception`)

## 🧪 Testando a API

### Usando Swagger UI

1. Acesse: http://localhost:8080/swagger-ui/index.html
2. Explore os endpoints disponíveis
3. Teste as operações diretamente pela interface

### Usando cURL

**Criar Funcionário:**
```bash
curl -X POST http://localhost:8080/rh/funcionario \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao@email.com",
    "salario": 3500.0,
    "departamentoId": 1,
    "cargoId": 1,
    "chefeId": null
  }'
```

**Listar Funcionários:**
```bash
curl http://localhost:8080/rh/funcionario/listar?page=0&size=10
```

**Buscar por ID:**
```bash
curl http://localhost:8080/rh/funcionario/1
```

## 📝 Estrutura de Dados

### FuncionarioRequest
```json
{
  "nome": "string (obrigatório)",
  "email": "string (obrigatório)",
  "salario": "number (obrigatório, > 0)",
  "departamentoId": "number (opcional)",
  "cargoId": "number (opcional)",
  "chefeId": "number (opcional)"
}
```

### FuncionarioResponse
```json
{
  "id": "number",
  "nome": "string",
  "email": "string",
  "salario": "number",
  "departamentoId": "number | null",
  "cargoId": "number | null",
  "chefeId": "number | null"
}
```

### CargoRequest / CargoResponse
```json
{
  "id": "number",
  "nome": "string",
  "descricao": "string | null"
}
```

### DepartamentoRequest / DepartamentoResponse
```json
{
  "id": "number",
  "nome": "string",
  "descricao": "string | null"
}
```

## 🔧 Configurações

### application.properties

```properties
spring.application.name=rh-sistema

# H2 Database
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:mem:rhdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=sa
```

## 📚 Documentação Adicional

- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **H2 Console**: http://localhost:8080/h2-console

## 🎨 Tecnologias Frontend

- **Bootstrap 5.3.0** - Framework CSS
- **Bootstrap Icons 1.10.0** - Biblioteca de ícones
- **Vanilla JavaScript** - Sem frameworks, JavaScript puro
- **Fetch API** - Para requisições HTTP

## 🏗️ Arquitetura

O projeto segue o padrão **MVC (Model-View-Controller)** com separação em camadas:

1. **Model** - Entidades JPA (`Funcionario`, `Cargo`, `Departamento`)
2. **Repository** - Camada de persistência (JpaRepository)
3. **Service** - Lógica de negócio
4. **Controller** - Endpoints REST
5. **DTO** - Objetos de transferência de dados
6. **Mapper** - Conversão entre entidades e DTOs (MapStruct)
7. **View** - Interface web (HTML/CSS/JS)

## 🔒 Segurança e Validações

- Validação de dados no backend usando `@NotBlank` e `@Valid`
- Tratamento centralizado de exceções
- Mensagens de erro amigáveis
- Validação de tipos e formatos

## 📊 Banco de Dados

O sistema utiliza **H2 Database** em memória, o que significa:
- ✅ Não requer instalação de banco de dados
- ✅ Dados são perdidos ao reiniciar a aplicação
- ✅ Ideal para desenvolvimento e testes
- ✅ Console H2 disponível para consultas SQL

### Tabelas Criadas Automaticamente

- `FUNCIONARIO` - Armazena dados dos funcionários
- `CARGO` - Armazena cargos disponíveis
- `DEPARTAMENTO` - Armazena departamentos

## 🚀 Melhorias Futuras

- [ ] Autenticação e autorização
- [ ] Validação de CPF único
- [ ] Validação de data de admissão
- [ ] Relatórios e estatísticas
- [ ] Exportação de dados (PDF/Excel)
- [ ] Histórico de alterações
- [ ] Filtros avançados na listagem
- [ ] Upload de fotos de funcionários
- [ ] Integração com banco de dados PostgreSQL/MySQL

## 👥 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👨‍💻 Autor

Desenvolvido como projeto acadêmico para disciplina de Java OOP2.

---

**Desenvolvido com ❤️ usando Spring Boot e Bootstrap**

