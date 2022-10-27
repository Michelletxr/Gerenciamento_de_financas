# Gerenciamento_de_financas

*Instruções para rodar o projeto*

- Clone o repositório do projeto/root
- Abra o projeto em seu editor de código (recomendamos o intellij para facilitar o uso)
- Após abrir o projeto certifique-se que as dependências foram instaladas corretamente
- Todas as dependências utilizadas estão no arquivo pom.xml, mas para verificar as dependências do projeto você pode utilizar a aba do maven.

*Configurando banco de dados*

- Crie um banco de dados localmente e o nomeie como finances_db
- Acesse o arquivo application.properties no diretório raiz do projeto
- Aqui estão as configurações do projeto, escolha uma porta para rodar o banco (a minha é a 5432), e coloque as informações de username e password do seu banco.

*Acessando a documentação*

- A documentação da API é feita via swagger, para acessa-la navegue até a página http://localhost:8080/swagger-ui.html
