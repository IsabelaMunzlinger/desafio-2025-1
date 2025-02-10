# Desafio Dev 2025-1

## Estrutura do Projeto
O projeto está organizado da seguinte forma:

![image](https://github.com/user-attachments/assets/9175b72e-c7a4-4671-8fde-e8f87d5a21ce)


## Pré-requisitos
Antes de rodar a aplicação, certifique-se de ter os seguintes requisitos instalados:
- **Java Development Kit (JDK) 8 ou superior**
- **Apache Maven**
- **MySQL**

## Passos para Executar a Aplicação

### Clonar o Repositório
```bash
git clone https://github.com/IsabelaMunzlinger/desafio-2025-1.git
cd desafio-2025-1
```

### Configurar o Banco de Dados
Edite o arquivo `application.properties` para definir as configurações do banco de dados MySQL.

### Executar a Aplicação
```bash
mvn spring-boot:run
```

## Funcionalidades
Atualmente, a aplicação permite:
- **Cadastro de Pessoas, Cursos e Endereços, definindo um menu de opções diferente para cada tipo de usuário**

## Observações
- Não foram implementados tratamentos de exceção para operações que podem falhar, como CPFs repetidos.
- O front-end da aplicação e a tela de login ainda não foram desenvolvidos.

## Dependências Utilizadas
O projeto utiliza **Maven** para gerenciamento de dependências. As principais dependências utilizadas são:
- `spring-boot-starter`
- `spring-boot-starter-test`
- `jackson-databind`
- `spring-boot-starter-data-jpa`
- `mysql-connector-j`
- `mysql-connector-java`
- `spring-boot-starter-web`
- `spring-boot-maven-plugin`
