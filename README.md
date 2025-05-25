# 💸 ExpensemManagement - Gerenciador Inteligente de Despesas

Este é um projeto completo de controle de despesas com **Spring Boot** no backend, **autenticação com JWT**, integração com **modelo de Machine Learning** para categorização automática e geração de **relatórios financeiros**. Ideal para quem deseja aprender ou aplicar conceitos de desenvolvimento web moderno com integração entre **Java** e **Python**.

---

## 🚀 Funcionalidades

- Cadastro e login de usuários com autenticação JWT
- CRUD completo de despesas
- Categorização automática com modelo de Machine Learning
- Relatórios e estatísticas financeiras
- Integração entre Spring Boot e Python via API Flask/FastAPI

---

## 🧠 Tecnologias Utilizadas

### **Backend (Java - Spring Boot)**
- Spring Web
- Spring Data JPA
- Spring Security + JWT
- Lombok
- PostgreSQL
- Flayway
- OpenPDF


### **Machine Learning (Python)**
- Scikit-Learn
- Pandas
- Flask ou FastAPI

---

## 🧪 Como Executar o Projeto

### Backend (Spring Boot)

```bash
# Acesse a pasta do projeto
cd expense-management

# Configure o banco de dados no application.properties
# Exemplo:
# spring.datasource.url=jdbc:postgresql://localhost:5432/expensetrack
# spring.datasource.username=seu_usuario
# spring.datasource.password=sua_senha

# Compile e execute o projeto
./mvnw spring-boot:run
````


### Machine Learning API (Python)

```bash
# Repositório do Modelo aqui (https://github.com/Gabryel-Januario/category-predicition)

# Acesse a pasta do modelo
cd category-prediction

# Crie o ambiente virtual e instale as dependências
python -m venv venv
source venv/bin/activate  # ou venv\Scripts\activate no Windows
pip install -r requirements.txt

# Execute a API Flask/FastAPI
python app.py
```

## 🛡️ Segurança
A autenticação é feita via JWT, garantindo segurança nas requisições. Apenas usuários autenticados podem acessar, criar ou modificar suas despesas.

## 📊 Dashboard e Relatórios
Endpoints para estatísticas como:

- Total de gastos por mês
- PDF dos gastos mensais

## 🤖 Categorização Automática
O modelo de Machine Learning foi treinado para sugerir automaticamente uma categoria com base na descrição e valor da despesa. Ao adicionar uma nova despesa, a API retorna a categoria sugerida antes de salvar no banco

<h2 id="colab">🤝 Colaborador</h2>

<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/161720296?s=96&v=4" width="100px;" alt="Gabryel Januario Profile Picture"/><br>
        <sub>
          <b>Gabryel Januario</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

<h2 id="contribute">📫 Contribute</h2>

Sinta-se à vontade para abrir issues e pull requests para melhorias!
