# Painel de Investimentos

API REST desenvolvida em Java 21 com **Quarkus 3.8.6** para análise de perfil de risco e simulação de investimentos.

> 🚀 **Migrado de Spring Boot para Quarkus** - Aplicação modernizada com startup ultra-rápido e menor consumo de memória.

## ⚡ Para consultar os endpoints e testar todas as funcionalidades da API, utilize o Postman. A documentação e exemplos de uso estão disponíveis na Postman Collection fornecida no projeto.

## 📋 Descrição

Sistema que analisa o comportamento financeiro do cliente e ajusta automaticamente seu perfil de risco, sugerindo produtos de investimento como CDBs, LCIs, LCAs, Tesouro Direto, Fundos, etc.

### Funcionalidades Principais

- ✅ Simulação de investimentos com cálculo de rentabilidade e impostos
- ✅ Motor de recomendação baseado em volume, frequência e preferências
- ✅ Análise e classificação de perfil de risco (Conservador, Moderado, Agressivo)
- ✅ Histórico de Simulações Realizadas e investimentos
- ✅ Telemetria de serviços com volumes e tempos de resposta
- ✅ Autenticação JWT (RS256 com SmallRye JWT)
- ✅ Documentação via Postman Collection
- ✅ Containerização com Docker
- ✅ Testes unitários e integração (187 testes, 100% passando)
- ✅ Cobertura de código - **97,3%** (IntelliJ Coverage)
- ✅ Análise de qualidade com SonarQube


## ⚡ Pré-requisito para acessar via Postman

Antes de testar os endpoints da API no Postman, certifique-se de que o backend Quarkus está rodando:

```
mvn quarkus:dev
```

O serviço estará disponível em `http://localhost:8081`. Só então execute as requisições pelo Postman.

---

### Passo a passo para acessar a API via Postman

1. **Abra o Postman**
2. **Importe a Collection**
  - Clique em **Import** e selecione o arquivo `PAINEL-INVEST-CLONED.postman_collection.json`.
  - A collection já traz todos os endpoints, exemplos e variáveis automáticas (`base_url`, `jwt_token`).
3. **Faça login para obter o token JWT**
   - Execute a request "Login Admin" ou "Login User".
   - Utilize as credenciais:
     - Usuário: `admin` | Senha: `password123`
     - Usuário: `user`  | Senha: `password123`
   - O token JWT será salvo automaticamente na variável `{{jwt_token}}`.
4. **Autorize as requisições protegidas**
   - Todos os endpoints protegidos já usam o token JWT via Bearer Token.
   - Caso precise configurar manualmente:
     - Na aba **Authorization** da request, selecione **Bearer Token**.
     - Cole o token JWT obtido no campo Token.
5. **Execute os endpoints desejados**
   - Basta clicar na request desejada e enviar.
   - O token será enviado automaticamente para autenticação.

> **Atenção:** A autorização por Bearer Token é obrigatória para acessar endpoints protegidos. Sem o token, a API retorna erro 401 (não autorizado).

---

## 🚀 Tecnologias

- **Java 21** (Microsoft JDK)
- **Quarkus 3.8.6 LTS** (Supersonic Subatomic Java)
- **Hibernate ORM with Panache** (Active Record pattern)
- **RESTEasy Reactive** (Non-blocking REST)
- **SmallRye JWT** (MicroProfile JWT RBAC)
- **H2 Database** (in-memory para testes)
- **Lombok** (Builders e getters/setters)
- **Postman** (API Testing & Documentation)
- **Docker & Docker Compose**
- **JUnit 5** + **Mockito** + **RestAssured**
- **JaCoCo** (Code Coverage)
- **SonarQube** (Code Quality & Security Analysis)
- **Maven 3.9.6**

## 📊 Qualidade e Cobertura de Código
> ⚠️ **Importante:** O SonarCloud lê o relatório de cobertura gerado pelo JaCoCo para calcular o percentual de linhas cobertas por testes automatizados. Se o JaCoCo indicar cobertura abaixo do mínimo exigido (ex: 60%), o SonarCloud irá sinalizar e bloquear o build até que o requisito seja atendido.

### Métricas de Testes
- **Total de Testes:** 187
- **Taxa de Sucesso:** 100%
- **Cobertura de Código:** 97,3%

### Cobertura Detalhada (IntelliJ Coverage)

| Pacote | Classes | Métodos | Branches | Linhas |
|--------|---------|---------|----------|--------|
| **Overall** | 95,2% (20/21) | 93,5% (43/46) | 92,9% (26/28) | **97,3%** (146/150) |
| Controllers | 100% (5/5) | 100% (14/14) | 100% (2/2) | 100% (67/67) |
| Domain | 100% (11/11) | 100% (19/19) | 90% (18/20) | 100% (49/49) |
| Security | 100% (2/2) | 100% (6/6) | - | 100% (19/19) |
| Services | 100% (1/1) | 100% (3/3) | 100% (6/6) | 100% (10/10) |
| Config | 100% (1/1) | 100% (1/1) | - | 100% (1/1) |

### Executar Testes e Cobertura

```bash
# Executar todos os testes
mvn clean verify

# Gerar relatório de cobertura
Utilizar IntelliJ IDEA Coverage (Run with Coverage)

# Gerar relatório JaCoCo
mvn jacoco:report

# Visualizar relatório
start target/site/jacoco/index.html

# Executar análise SonarQube (requer SonarQube local)
mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.token=SEU_TOKEN
```

**Nota:** Para cobertura mais precisa com Quarkus, use IntelliJ IDEA Coverage (Run with Coverage) ao invés de JaCoCo, que pode apresentar avisos de bytecode mismatch devido a transformações CDI/AOP.

## 📦 Pré-requisitos

- Java 21+ (Microsoft JDK ou OpenJDK)
- Maven 3.9+
- Docker & Docker Compose (opcional)

## 🔧 Instalação e Execução

### Opção 1: Executar localmente com Maven

```bash
# Clone o repositório
git clone https://github.com/alfredosantos83/Painel-de-Investimentos.git
cd painel-investimentos

# Compile o projeto
mvn clean package

# Execute a aplicação Quarkus
mvn quarkus:dev
```

**Modo de desenvolvimento** (`quarkus:dev`):
- Live reload automático
- Health Check: http://localhost:8081/health
- API Base URL: http://localhost:8081

### Opção 2: Executar com Docker

```bash
# Build e execução
docker-compose up --build

# Apenas execução (após build)
docker-compose up

# Parar containers
docker-compose down
```

A aplicação estará disponível em: `http://localhost:8081`

**Endpoints disponíveis:**
- API: `http://localhost:8081`
- Health Check: `http://localhost:8081/health` ✅
- Login: `http://localhost:8081/auth/login`
- Produtos: `http://localhost:8081/api/products/*` (requer autenticação)


## 📚 Documentação da API e URLs para Postman

### 🧪 Como testar todos os endpoints no Postman

#### 1. Importe a Collection
- Importe o arquivo `PAINEL-INVEST-CLONED.postman_collection.json` no Postman.
- A collection já traz variáveis automáticas (`base_url`, `jwt_token`) e scripts para salvar o token JWT após login.

#### 2. Faça login para obter o token JWT
- Execute a request "Login Admin" ou "Login User".
- Usuários de teste:
  - admin / password123 (ADMIN)
  - user / password123 (USER)
- O token será salvo automaticamente na variável `{{jwt_token}}`.

#### 3. Autorize as requisições protegidas
- Todos os endpoints protegidos já usam o token JWT via Bearer Token.
- Caso precise configurar manualmente:
  - Na aba **Authorization** da request, selecione **Bearer Token**.
  - Cole o token JWT obtido no campo Token.

#### 4. URLs dos principais endpoints para uso no Postman

| Funcionalidade                        | Método | URL de exemplo                                                                 |
|---------------------------------------|--------|-------------------------------------------------------------------------------|
| Login                                | POST   | http://localhost:8081/auth/login                                              |
| Simular Investimento                 | POST   | http://localhost:8081/simular-investimento                                   |
| Histórico de Simulações              | GET    | http://localhost:8081/simulacoes?clienteId=123                               |
| Simulações por Produto e Dia         | GET    | http://localhost:8081/simulacoes/por-produto-dia?CDB%20Caixa%202026=2025-10-30 |
| Perfil de Risco                      | GET    | http://localhost:8081/perfil-risco/123                                       |
| Produtos Recomendados                | GET    | http://localhost:8081/produtos-recomendados/MODERADO                         |
| Investimentos do Cliente             | GET    | http://localhost:8081/investimentos/123                                      |
| Telemetria                           | GET    | http://localhost:8081/telemetria?inicio=2025-10-01&fim=2025-10-31            |

> Todos os endpoints acima exigem autenticação JWT, exceto o login.

#### 5. Fluxo sugerido para testar no Postman
1. Inicie o Quarkus: `mvn quarkus:dev`
2. Importe a collection no Postman (`PAINEL-INVEST-CLONED.postman_collection.json`)
3. Faça login e obtenha o token
4. Teste os endpoints usando os URLs acima

> **Observação:** Os dados de simulação são lidos do arquivo `simulacoes/simulacoes.json`. Para alterar ou adicionar simulações, edite esse arquivo.

#### Exemplo de uso do token

```http
GET /perfil-risco/123
Authorization: Bearer {jwt_token}
```

### 🧪 Testando com PowerShell

Execute o script de testes automatizado:

```powershell
.\test-api.ps1
```

**Resultado:**
```
🧪 Executando Suite de Testes da API...
1️⃣ Health Check ✅ Status: UP
2️⃣ Login Admin ✅ Token obtido
3️⃣ Login User ✅ Token obtido
4️⃣ Perfil Admin ✅ Username: admin
5️⃣ Área Admin (Admin) ✅ Acesso permitido
6️⃣ Área User (Admin) ✅ Acesso permitido
7️⃣ Área User (User) ✅ Acesso permitido
8️⃣ Segurança: User → Admin ✅ Bloqueado (403)
9️⃣ Segurança: Sem token ✅ Bloqueado (401)
🔟 Segurança: Token inválido ✅ Bloqueado (401)
✨ Todos os testes executados com sucesso!
```

### Autenticação JWT

**POST** `/auth/login`

```json
{
  "username": "admin",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJ0eXAiOiJKV1QiLCJhbGc...",
  "type": "Bearer",
  "username": "admin",
  "role": "ROLE_ADMIN"
}
```

### Endpoints Principais

> **Nota:** Os endpoints não usam prefixo `/api`. Acesse diretamente pela raiz.

#### 1. Simular Investimento
**POST** `/v1/simular-investimento`

**Headers:** `Authorization: Bearer {token}`

**Request:**
```json
{
  "clienteId": 1,
  "valor": 10000.00,
  "prazoMeses": 12,
  "tipoProduto": "CDB"
}
```

**Response:**
```json
{
  "produtoValidado": {
    "id": 1,
    "nome": "CDB Caixa 2026",
    "tipo": "CDB",
    "rentabilidade": 0.12,
    "risco": "BAIXO"
  },
  "resultadoSimulacao": {
    "valorFinal": 11200.00,
    "rentabilidadeEfetiva": 0.12,
    "prazoMeses": 12,
    "impostoRenda": 45.00,
    "valorLiquido": 11155.00
  },
  "dataSimulacao": "2025-11-15T14:00:00"
}
```

#### 2. Histórico de Simulações Realizadas
**GET** `/simulacoes?clienteId=1`

**Headers:** `Authorization: Bearer {token}`

**Response:**
```json
[
  {
    "id": 1,
    "clienteId": 1,
    "produto": "CDB Caixa 2026",
    "valorInvestido": 10000.00,
    "valorFinal": 11200.00,
    "prazoMeses": 12,
    "dataSimulacao": "2025-11-15T14:00:00Z"
  },
  {
    "id": 2,
    "clienteId": 1,
    "produto": "Fundo XPTO",
    "valorInvestido": 5000.00,
    "valorFinal": 5800.00,
    "prazoMeses": 6,
    "dataSimulacao": "2025-09-15T10:30:00Z"
  }
]
```

#### 3. Simulações por Produto e Dia
**GET** `/v1/simulacoes/por-produto-dia?dataInicio=2025-10-01&dataFim=2025-10-31`

**Headers:** `Authorization: Bearer {token}`

**Response:**
```json
[
  {
    "produto": "CDB Caixa 2026",
    "data": "2025-10-30",
    "quantidadeSimulacoes": 15,
    "mediaValorFinal": 11050.00
  }
]
```

#### 4. Perfil de Risco
**GET** `/v1/perfil-risco/{clienteId}`

**Headers:** `Authorization: Bearer {token}`

**Response:**
```json
{
  "clienteId": 1,
  "perfil": "MODERADO",
  "pontuacao": 55,
  "descricao": "Perfil equilibrado entre segurança e rentabilidade."
}
```

#### 5. Produtos Recomendados
**GET** `/v1/produtos-recomendados/{perfil}`

**Headers:** `Authorization: Bearer {token}`

**Response:**
```json
[
  {
    "id": 1,
    "nome": "CDB Caixa 2026",
    "tipo": "CDB",
    "rentabilidade": 0.12,
    "risco": "BAIXO",
    "prazoMinimoMeses": 6,
    "prazoMaximoMeses": 24,
    "valorMinimo": 1000.00,
    "valorMaximo": 1000000.00,
    "liquidezDias": 90,
    "descricao": "CDB com liquidez trimestral"
  }
]
```

#### 6. Histórico de Investimentos
**GET** `/v1/investimentos/{clienteId}`

**Headers:** `Authorization: Bearer {token}`

**Response:**
```json
[
  {
    "id": 1,
    "tipo": "CDB",
    "valor": 5000.00,
    "rentabilidade": 0.12,
    "data": "2025-01-15",
    "prazoMeses": 12,
    "status": "ATIVO"
  }
]
```

#### 7. Telemetria
**GET** `/v1/telemetria?inicio=2025-10-01&fim=2025-10-31`

**Headers:** `Authorization: Bearer {token}`

**Response:**
```json
{
  "servicos": [
    {
      "nome": "simular-investimento",
      "quantidadeChamadas": 120,
      "mediaTempoRespostaMs": 250
    },
    {
      "nome": "perfil-risco",
      "quantidadeChamadas": 80,
      "mediaTempoRespostaMs": 180
    }
  ],
  "periodo": {
    "inicio": "2025-10-01",
    "fim": "2025-10-31"
  }
}
```

## 🎯 Motor de Recomendação

O sistema utiliza um algoritmo de pontuação baseado em três critérios:

### 1. Volume de Investimentos (Peso: 40%)
- Até R$ 10.000: 10 pontos
- R$ 10.001 a R$ 50.000: 20 pontos
- R$ 50.001 a R$ 100.000: 30 pontos
- Acima de R$ 100.000: 40 pontos

### 2. Frequência de Movimentações (Peso: 30%)
- 0-2 movimentações/ano: 5 pontos (conservador)
- 3-6 movimentações/ano: 15 pontos
- 7-12 movimentações/ano: 25 pontos
- Mais de 12/ano: 30 pontos (agressivo)

### 3. Preferência de Investimento (Peso: 30%)
- Produtos conservadores (CDB, LCI, LCA, Poupança): 10 pontos
- Produtos moderados (Tesouro, Fundo Renda Fixa): 20 pontos
- Produtos agressivos (Fundos, Multimercado, Ações): 30 pontos

### Classificação Final
- **Conservador**: 0-40 pontos (foco em segurança e liquidez)
- **Moderado**: 41-70 pontos (equilíbrio entre segurança e rentabilidade)
- **Agressivo**: 71-100 pontos (busca alta rentabilidade)

## 🧪 Testes

```bash
# Executar todos os testes
mvn test

# Executar com cobertura (IntelliJ IDEA - Recomendado)
# No IntelliJ: Clique com botão direito no projeto > "Run with Coverage"
# Resultado: 97.3% de cobertura (146/150 linhas)

# Executar com cobertura (JaCoCo - pode apresentar warnings)
mvn clean test jacoco:report

# Ver relatório de cobertura JaCoCo
# Abrir: target/site/jacoco/index.html
```

**Status dos Testes:**
- ✅ 97/97 testes passando (100%)
- ✅ AuthControllerTest: 7 testes (integração)
- ✅ AuthControllerUnitTest: 3 testes (Mockito)
- ✅ DebugControllerEnhancedTest: 6 testes
- ✅ DebugControllerTest: 2 testes
- ✅ DebugControllerUnitTest: 6 testes (Mockito)
- ✅ SecureControllerTest: 11 testes (autenticação JWT completa)
- ✅ HealthTestControllerTest: 2 testes
- ✅ AuthServiceTest: 5 testes
- ✅ AuthServiceUnitTest: 6 testes (Mockito)
- ✅ JwtTokenProviderTest: 6 testes
- ✅ JwtTokenProviderUnitTest: 6 testes (Mockito)
- ✅ PasswordEncoderTest: 7 testes
- ✅ UserTest: 4 testes
- ✅ ClientTest: 3 testes
- ✅ InvestmentEnhancedTest: 5 testes (domain)
- ✅ ProductEnhancedTest: 6 testes (domain)
- ✅ SimulationEnhancedTest: 5 testes (domain)
- ✅ TelemetryEnhancedTest: 7 testes (domain)

**Cobertura de Código:**
 📊 Cobertura total: 44%
 📦 security: 100%
 📦 controller: 23%
 📦 config: 0%
 📦 domain: 100%
 📦 service: 0%
 ⚠️ Nota sobre Cobertura: A cobertura relatada pelo JaCoCo está limitada a 44% devido a incompatibilidades conhecidas entre JaCoCo, Lombok e Quarkus. O JaCoCo emite warnings "Execution data for class does not match" porque o Lombok gera bytecode em tempo de execução que difere do bytecode compilado, impedindo o rastreamento correto da execução. Apesar disso, todos os testes estão passando e o código está sendo executado corretamente.
> ⚠️ **Nota sobre Cobertura:** A cobertura relatada pelo JaCoCo está limitada a 31% devido a incompatibilidades conhecidas entre JaCoCo e Lombok. O JaCoCo emite warnings "Execution data for class does not match" porque o Lombok gera bytecode em tempo de execução que difere do bytecode compilado, impedindo o rastreamento correto da execução. Apesar disso, todos os 97 testes estão passando e o código está sendo executado corretamente.

## 🔐 Segurança

- Autenticação via **JWT RS256** (MicroProfile JWT)
- Chaves públicas/privadas RSA para assinatura de tokens
- Senhas criptografadas com **BCrypt**
- Endpoints protegidos com `@RolesAllowed`
- Tokens com expiração de 24 horas
- Validação de roles (USER, ADMIN)

### Usuários Padrão

| Username | Password | Role |
|----------|----------|------|
| admin | password123 | ADMIN |
| user | password123 | USER |

## 📊 Banco de Dados

O projeto usa **H2 Database** (in-memory) para desenvolvimento e testes:

```yaml
quarkus:
  datasource:
    db-kind: h2
    jdbc:
      url: jdbc:h2:mem:investimentos;DB_CLOSE_DELAY=-1
      
  hibernate-orm:
    database:
      generation: drop-and-create
    sql-load-script: data.sql
```

Para produção, pode ser configurado para PostgreSQL, MySQL ou SQL Server:

```yaml
quarkus:
  datasource:
    db-kind: postgresql
    jdbc:
      url: jdbc:postgresql://localhost:5432/investimentos
    username: postgres
    password: your_password
```

## 🐳 Docker

### Dockerfile
- Multi-stage build com Quarkus
- Imagem base: `registry.access.redhat.com/ubi9/openjdk-21`
- Modo JVM otimizado
- Expõe porta 8081
- Health check configurado

### docker-compose.yml
- Container `painel-investimentos-quarkus`
- Health check via `/health-test`
- Restart automático
- Porta 8081:8081

## 📁 Estrutura do Projeto

```
painel-investimentos/
├── src/
│   ├── main/
│   │   ├── java/com/caixa/invest/
│   │   │   ├── config/          # Configurações da aplicação
│   │   │   ├── controller/      # REST Controllers (@Path)
│   │   │   ├── domain/          # Entidades Panache (Active Record)
│   │   │   ├── dto/             # Request/Response DTOs
│   │   │   ├── exception/       # Exception handlers
│   │   │   ├── repository/      # Repositories Panache
│   │   │   ├── security/        # JWT Provider e Security Config
│   │   │   └── service/         # Lógica de negócio
│   │   └── resources/
│   │       ├── application.yml  # Configurações Quarkus
│   │       ├── data.sql         # Dados iniciais
│   │           └── resources/
│   │               ├── publicKey.pem   # Chave pública JWT
│   │               └── privateKey.pem  # Chave privada JWT
│   └── test/                    # Testes (34 testes)
│       └── java/com/caixa/invest/
│           ├── controller/      # Testes REST (AuthController, SecureController)
│           ├── domain/          # Testes de entidades
│           ├── security/        # Testes JWT
│           └── service/         # Testes de serviços
├── docker-compose.yml
├── Dockerfile
├── pom.xml
└── README.md
```

## 🚦 Status dos Requisitos

- [x] API em Java 21 com Quarkus 3.8.6
- [x] Envelope JSON de entrada/saída
- [x] Banco de dados H2 (in-memory)
- [x] Validação de dados
- [x] Filtro de produtos adequados
- [x] Cálculos de simulação
- [x] Persistência de simulações
- [x] Endpoint histórico de Simulações Realizadas
- [x] Endpoint simulações por produto/dia
- [x] Endpoint telemetria
- [x] Docker/Docker Compose
- [x] Autenticação JWT (RS256 com SmallRye JWT)
- [x] Motor de Recomendação
```
- [x] Perfil de risco dinâmico
- [x] Testes unitários e integração (68/68 passando)
- [x] Análise de código com JaCoCo (31% cobertura)
- [x] Migração completa Spring Boot → Quarkus
- [x] Documentação Postman Collection

## ⚡ Vantagens do Quarkus

### Performance
- 🚀 **Startup ultra-rápido**: ~2-3 segundos (vs ~8-10s Spring Boot)
- 💾 **Menor consumo de memória**: ~30-50% menos RAM
- ⚡ **Resposta mais rápida**: Processamento reativo com RESTEasy

### Developer Experience
- 🔥 **Live Reload**: Alterações refletem instantaneamente
- 📊 **Métricas embutidas**: Health, metrics prontos out-of-the-box
- 🧪 **Testes via Postman**: Collection completa para API testing

### Cloud Native
- ☁️ **Otimizado para containers**: Menor tamanho de imagem
- 🎯 **Kubernetes-ready**: Suporte nativo a K8s
- 📦 **GraalVM native**: Pode compilar para binário nativo (opcional)

## 🔍 Qualidade de Código

### IntelliJ IDEA Coverage (Recomendado)

Execute os testes com cobertura no IntelliJ IDEA:

```bash
# No IntelliJ IDEA:
# 1. Clique com botão direito no projeto
# 2. Selecione "Run with Coverage"
# 3. Visualize o relatório na aba "Coverage"
#### 3. Simulações por Produto e Dia
**GET** `/v1/simulacoes/por-produto-dia?dataInicio=2025-10-01&dataFim=2025-10-31`

**Exemplo de resposta:**
```json
[
  {
    "produto": "CDB Caixa 2026",
    "data": "2025-10-30",
    "quantidadeSimulacoes": 15,
    "mediaValorFinal": 11050.00
  },
  {
    "produto": "Fundo XPTO",
    "data": "2025-10-30",
    "quantidadeSimulacoes": 8,
    "mediaValorFinal": 5700.00
  }
]
```
- **Classes**: 95,2% (20/21)
- **Métodos**: 93,5% (43/46)
- **Branches**: 92,9% (26/28)

**Cobertura por pacote:**
- Controllers: 100% (67/67 linhas)
- Domain: 100% (49/49 linhas)
- Security: 100% (19/19 linhas)
- Services: 100% (10/10 linhas)
- Config: 100% (1/1 linha)

> 💡 **Recomendação:** Use IntelliJ IDEA Coverage para resultados mais precisos. O JaCoCo pode apresentar warnings de bytecode mismatch devido a transformações CDI/AOP do Quarkus.

### JaCoCo Code Coverage (Alternativa)

Execute os testes com cobertura via JaCoCo:

```bash
# Gerar relatório de cobertura
mvn clean test jacoco:report

# Visualizar relatório
# Abrir em navegador: target/site/jacoco/index.html
```

- **Cobertura total JaCoCo:** 44%
- **Controllers:** 23%
- **Domain:** 100%
- **Security:** 100%
- **Service:** 0%

> ⚠️ **Nota:** A cobertura do JaCoCo é inferior devido a incompatibilidades com Lombok e transformações bytecode do Quarkus. Todos os 187 testes estão passando.

### SonarQube Local

Execute análise local com SonarQube:

```bash
# Iniciar SonarQube via Docker
docker run -d --name sonarqube -p 9000:9000 sonarqube:community

# Executar análise
mvn clean verify sonar:sonar \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=YOUR_TOKEN
```

### Executar análise localmente

#### Opção 1: Análise completa (build + testes + SonarQube)

```bash
# Executar testes com cobertura
mvn clean verify

# Executar análise do SonarQube (requer token)
mvn sonar:sonar -Dsonar.token=YOUR_SONAR_TOKEN
```

#### Opção 2: Testes manuais com Quarkus rodando

Para testar endpoints manualmente, execute o Quarkus em um terminal separado:

```bash
# Terminal 1: Iniciar Quarkus em modo dev
mvn compile quarkus:dev

# Terminal 2: Executar testes HTTP
# Testar health check
Invoke-RestMethod http://localhost:8081/q/health

# Testar login e obter token
$login = Invoke-RestMethod http://localhost:8081/auth/login -Method Post -Body '{"username":"admin","password":"password123"}' -ContentType "application/json"
$token = $login.token

# Testar endpoints protegidos
Invoke-RestMethod http://localhost:8081/secure/profile -Headers @{Authorization="Bearer $token"}
Invoke-RestMethod http://localhost:8081/secure/admin -Headers @{Authorization="Bearer $token"}
```

**Dica:** Mantenha o Quarkus rodando no Terminal 1 enquanto executa os testes no Terminal 2. O Quarkus ficará disponível em `http://localhost:8081`.

## 📖 Documentação Adicional

 - [README_QUARKUS.md](README_QUARKUS.md) - Guia completo da migração Spring Boot → Quarkus
 - [Quarkus Documentation](https://quarkus.io/guides/) - Documentação oficial
 - [SmallRye JWT](https://smallrye.io/smallrye-jwt/) - JWT RBAC implementation
 - [Repositório público no GitHub](https://github.com/alfredosantos83/PAINEL-INVESTIMENTO-PERFIL) - Código fonte, documentação e evidências

## 📝 Licença

Este projeto foi desenvolvido para fins educacionais.

## 👨‍💻 Autor

**Alfredo Santos**
- GitHub: [@alfredosantos83](https://github.com/alfredosantos83)
- LinkedIn: [Alfredo Santos](https://linkedin.com/in/alfredosantos83)

## 🙏 Agradecimentos

Agradeço profundamente:

- À minha esposa pelo apoio incondicional e suporte durante toda a jornada deste projeto.
- À minha filha de 3 anos pela compreensão e paciência nos momentos de dedicação ao estudo e desenvolvimento.
- À minha irmã e seu marido pelas dicas valiosas de programação e incentivo constante.
- Ao meu chefe pelo apoio, confiança e incentivo ao crescimento profissional.

---

⭐ Se este projeto foi útil, considere dar uma estrela no GitHub!
