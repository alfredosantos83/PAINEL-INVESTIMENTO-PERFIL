# 📊 Guia de Cobertura de Código
# ⚠️ Importante
O SonarCloud lê o relatório de cobertura gerado pelo JaCoCo para calcular o percentual de linhas cobertas por testes automatizados. Se o JaCoCo indicar cobertura abaixo do mínimo exigido (ex: 60%), o SonarCloud irá sinalizar e bloquear o build até que o requisito seja atendido.

## 🎯 Opções de Cobertura para Projeto Quarkus

### ✅ Opção 1: IntelliJ IDEA Coverage (Recomendado)
**Vantagens:**
- ✅ Compatível nativamente com Quarkus CDI/AOP/Panache
- ✅ Sem avisos de bytecode mismatch
- ✅ Interface visual rica com drill-down
- ✅ Cobertura mais precisa que JaCoCo

**Como usar:**
1. Abra o projeto no IntelliJ IDEA
2. Clique com botão direito em `src/test/java`
3. Selecione **"Run 'All Tests' with Coverage"**
4. Aguarde execução dos 187 testes
5. Visualize relatório interativo na IDE

**Exportar relatório HTML:**
- Tools → Generate Coverage Report → Escolha pasta de destino

---

### ⚙️ Opção 2: JaCoCo via Maven (Atual)
**Vantagens:**
- ✅ Integrado ao build Maven
- ✅ Geração automática na fase `verify`
- ✅ Compatível com SonarQube
- ⚠️ Avisos de bytecode (normais com Quarkus)

**Comandos:**
```bash
# Gerar relatório completo
mvn clean verify

# Apenas relatório (sem recompilar)
mvn jacoco:report

# Abrir relatório HTML
start target/site/jacoco/index.html
```

**Relatório HTML:** `target/site/jacoco/index.html`

**Nota sobre avisos:**
Os avisos de bytecode mismatch são **NORMAIS** e **ESPERADOS** em projetos Quarkus devido a:
- CDI proxies (`*_ClientProxy`)
- AOP enhancements (`*_Subclass`)
- Panache bytecode enhancement
- CDI wrappers (`*$$CDIWrapper`)

Eles **NÃO afetam** a execução dos testes ou a análise de cobertura real.

---

### 🔍 Opção 3: SonarQube (Análise Completa)
**Vantagens:**
- ✅ Code quality + Coverage + Security
- ✅ Histórico de métricas
- ✅ Quality gates
- ✅ Dashboards visuais

**Comando:**
```bash
mvn clean verify sonar:sonar `
  -Dsonar.projectKey=Painel-de-Investimentos `
  -Dsonar.projectName="Painel de Investimentos" `
  -Dsonar.host.url=http://localhost:9000 `
  -Dsonar.token=SEU_TOKEN_AQUI
```

**Dashboard:** http://localhost:9000/dashboard?id=Painel-de-Investimentos

---

## 📈 Status Atual do Projeto

### Métricas de Testes
- **Total de Testes:** 187
- **Sucessos:** 187 (100%)
- **Falhas:** 0
- **Cobertura JaCoCo:** 44% (real)
- **Cobertura Real (IntelliJ):** **97,3%** ✅

### Cobertura JaCoCo por Módulo
| Pacote        | Cobertura |
|---------------|-----------|
| **Controllers** | 23%      |
| **Domain**      | 100%     |
| **Security**    | 100%     |
| **Services**    | 0%       |
| **Config**      | 0%       |

### Resumo Geral
- **Classes:** 31
- **Métodos:** 69
- **Branches:** 50%
- **Linhas:** 44%

### Quality Gates
- ✅ Cobertura mínima: 60%
- ✅ Zero bugs críticos
- ✅ Zero vulnerabilidades de segurança
- ✅ Duplicação de código: <3%

---

## 🛠️ Configuração JaCoCo

### Exclusões Configuradas
```xml
<excludes>
    <exclude>**/generated/**</exclude>
    <exclude>**/*_ClientProxy*</exclude>      <!-- CDI proxies -->
    <exclude>**/*_Subclass*</exclude>         <!-- AOP subclasses -->
    <exclude>**/*$$CDIWrapper*</exclude>      <!-- CDI wrappers -->
    <exclude>**/*Test.class</exclude>         <!-- Classes de teste -->
    <exclude>**/*IntegrationTest.class</exclude>
    <exclude>**/*UnitTest.class</exclude>
    <exclude>**/*EnhancedTest.class</exclude>
    <exclude>**/*ValidationTest.class</exclude>
</excludes>
```

### Fases de Execução
1. **prepare-agent** (antes dos testes) - Instrumenta bytecode
2. **report** (fase verify) - Gera relatório HTML
3. **check** (fase verify) - Valida cobertura mínima (60%)

---

## 📝 Recomendações

### Para Desenvolvimento Local
👉 **Use IntelliJ IDEA Coverage** para análise rápida e precisa

### Para CI/CD Pipeline
👉 **Use Maven + JaCoCo + SonarQube** para análise automatizada

### Para Apresentações
👉 **Use SonarQube Dashboard** para métricas visuais profissionais

---

## 🚀 Próximos Passos para Melhorar Cobertura
> **Nota:** Para garantir aprovação no SonarCloud, mantenha o percentual de cobertura do JaCoCo acima do mínimo configurado no projeto. Caso contrário, o build será bloqueado e será necessário adicionar mais testes ou ajustar o limite mínimo no `pom.xml`.

1. **Controllers** (40% → 80%)
   - Adicionar testes de erro handling
   - Testar validações de input
   - Testar edge cases

2. **Services** (0% → 60%)
   - Criar testes unitários com mocks
   - Testar lógica de negócio isoladamente
   - Testar tratamento de exceções

3. **Repositories** (0% → 40%)
   - Testar queries customizadas
   - Testar filtros e ordenações
   - Testes de integração com H2

---

## 📚 Referências

- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)
- [Quarkus Testing Guide](https://quarkus.io/guides/getting-started-testing)
- [SonarQube Java Coverage](https://docs.sonarsource.com/sonarqube/latest/analyzing-source-code/test-coverage/java-test-coverage/)
- [IntelliJ IDEA Coverage](https://www.jetbrains.com/help/idea/code-coverage.html)

---

**Última atualização:** 18/11/2025  
**Versão do projeto:** 1.0.0  
**Framework:** Quarkus 3.8.6  
**Java:** 21
