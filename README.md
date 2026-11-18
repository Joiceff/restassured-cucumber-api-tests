# ☕ REST Assured + Cucumber — Testes de API com BDD

[![API Tests](https://github.com/Joiceff/restassured-cucumber-api-tests/actions/workflows/tests.yml/badge.svg)](https://github.com/Joiceff/restassured-cucumber-api-tests/actions/workflows/tests.yml)

Automação de testes da API REST [ServeRest](https://serverest.dev) em **Java**, com cenários escritos em **Gherkin (português)**, validação de **contrato com JSON Schema** e execução no **GitHub Actions**.

## 🧰 Tecnologias

| Ferramenta | Uso |
|---|---|
| Java 11+ / Maven | Linguagem e build |
| REST Assured 5 | Requisições e validações HTTP |
| Cucumber 7 (BDD) | Cenários em Gherkin `# language: pt` |
| JUnit 5 Platform | Execução da suíte |
| PicoContainer | Injeção de dependência entre os steps |
| JSON Schema Validator | Testes de contrato |
| GitHub Actions | CI com relatório publicado como artifact |

## 📁 Estrutura

```
src/test/
├── java/br/com/joice/api/
│   ├── model/        # POJOs (Usuario, Produto) com geração de massa única
│   ├── runner/       # RunCucumberTest (JUnit Platform Suite)
│   ├── steps/        # Step definitions + @After para limpeza da massa
│   └── support/      # ApiClient (RequestSpec centralizado) e TestContext
└── resources/
    ├── features/     # usuarios.feature, login_produtos.feature
    └── schemas/      # contratos JSON Schema
```

## ✅ Cenários (12)

- **Usuários:** cadastro válido + contrato, e-mail duplicado, consulta por id, exclusão e campos obrigatórios (*Esquema do Cenário*)
- **Login:** credenciais válidas + contrato do token, senha inválida
- **Produtos:** cadastro por administrador, sem token (401) e por usuário comum (403)

Toda massa criada é removida no hook `@After`, garantindo cenários **independentes e repetíveis**.

## ▶️ Como executar

```bash
mvn test

# apenas cenários smoke
mvn test -Dcucumber.filter.tags="@smoke"

# apontando para outro ambiente
mvn test -Dbase.url=http://localhost:3000
```

Relatório HTML: `target/cucumber-report.html`

---
👩‍💻 Desenvolvido por **Joice Ferreira** — QA Sênior | [LinkedIn](https://www.linkedin.com/in/joice-ferreira-a04227126)
