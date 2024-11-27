# Gerenciador de Despesas e Contas

## Descrição
**Gerenciador de Despesas e Contas** é um aplicativo Android desenvolvido para ajudar os usuários a gerenciar suas finanças pessoais de forma simples e eficiente. O aplicativo oferece funcionalidades para registrar, atualizar e excluir contas, além de alertar os usuários sobre contas próximas do vencimento, ajudando a evitar atrasos nos pagamentos.

O aplicativo é desenvolvido com base nas melhores práticas de design e usabilidade, utilizando o **Material Design** e tecnologias robustas como **SQLite** para persistência de dados e **Firebase** para autenticação e notificações.

## Funcionalidades
- **CRUD Completo**: Criação, leitura, atualização e exclusão de registros financeiros.
- **Persistência de Dados**: Armazenamento local de dados utilizando SQLite.
- **Interface de Usuário Intuitiva**: Design baseado no **Material Design**, com navegação fluída e adaptação a diferentes tamanhos de tela.
- **Validação de Dados**: Validação de campos obrigatórios como valor e data de vencimento.
- **Notificações Locais**: Alertas personalizados para lembrar o usuário de contas próximas do vencimento.

## Requisitos

### Requisitos Funcionais
- **Funcionalidade CRUD Completa**: O aplicativo permite a manipulação de registros financeiros (criação, leitura, atualização e exclusão).
- **Persistência de Dados com SQLite**: Utiliza SQLite para armazenamento local dos dados, garantindo acessibilidade contínua.
- **Interface do Usuário (UI)**: Design fluído e acessível com base no Material Design.
- **Validação de Dados**: O sistema valida os dados inseridos pelo usuário, garantindo preenchimento adequado dos campos obrigatórios.
- **Notificações Locais**: Notificações de vencimento de contas, configuráveis conforme preferência do usuário.

### Requisitos Não Funcionais
- **Performance e Otimização**: O aplicativo será otimizado para garantir desempenho fluido, mesmo em dispositivos de menor capacidade.
- **Compatibilidade**: O aplicativo será compatível com dispositivos Android a partir da versão 6.0 (API 23).
- **Usabilidade**: Interface intuitiva e de fácil navegação.
- **Segurança**: Proteção de dados do usuário com criptografia e validação de entradas.
- **Manutenibilidade**: Arquitetura modular e documentação do código.
- **Escalabilidade**: Suporte à evolução e integração com serviços em nuvem no futuro.

## Tecnologias Utilizadas

- **Android SDK**: O Android SDK é utilizado como base para o desenvolvimento, permitindo a criação de aplicativos Android nativos com acesso a APIs e recursos essenciais.
- **IDE Android Studio**: Android Studio é a IDE oficial para o desenvolvimento de aplicativos Android, proporcionando um ambiente eficiente para codificação, depuração e design da interface.
- **Frameworks e Bibliotecas**:
    - **Room**: Biblioteca de persistência de dados que facilita o gerenciamento do banco de dados SQLite.
    - **Retrofit**: Framework para integração com APIs RESTful, simplificando a comunicação com servidores.
    - **LiveData e ViewModel**: Componentes da arquitetura MVVM, garantindo uma UI reativa e separação das responsabilidades de negócios e apresentação.
    - **Material Components**: Biblioteca que implementa o Material Design para criar interfaces consistentes e acessíveis.
- **Firebase**: Firebase é utilizado para autenticação de usuários e notificações em tempo real.
- **GitHub**: GitHub é usado para versionamento de código e colaboração no desenvolvimento.

## Instalação

### Pré-requisitos
- Android Studio instalado.
- Java 8 ou superior.
- SDK do Android configurado.
- Emulador ou dispositivo Android para testes.

### Passos para instalação
1. **Clone este repositório**:
    ```bash
    git clone https://github.com/rraffaa/GerenciadorDeDespesas.git
    cd AndroidStudioProjects/Gerenciamento_Despesas
    ```

2. **Abra o projeto no Android Studio**.

3. **Sincronize o projeto com o Gradle** clicando em **Sync Now**.

4. **Conecte um dispositivo Android ou inicie um emulador**.

5. **Execute o aplicativo** clicando em **Run** no Android Studio.

### Configuração do Firebase
1. Acesse o [Firebase Console](https://console.firebase.google.com/) e crie um novo projeto.
2. Adicione o arquivo `google-services.json` ao diretório `app` do projeto.
3. Ative o **Firebase Authentication** e configure os métodos de login desejados (por exemplo, login por e-mail e senha).
4. Configure o **Firebase Cloud Messaging** para enviar notificações.

### Configuração do Banco de Dados SQLite
O aplicativo utiliza **SQLite** para persistência local dos dados. Certifique-se de que a estrutura do banco de dados esteja devidamente configurada conforme a especificação no código.



## Contribuições
Contribuições são bem-vindas! Se você tiver sugestões de melhorias ou correções, por favor, faça um fork deste repositório e envie um pull request. Certifique-se de seguir as diretrizes de codificação e adicionar testes quando necessário.

1. Fork o repositório.
2. Crie uma branch para sua modificação:
    ```bash
    git checkout -b minha-modificacao
    ```
3. Faça commit das suas alterações:
    ```bash
    git commit -am 'Adicionando nova funcionalidade'
    ```
4. Envie sua branch para o repositório remoto:
    ```bash
    git push origin minha-modificacao
    ```
5. Crie um pull request.

## Licença

Este projeto está licenciado sob a **Licença Apache 2.0** - veja o arquivo [LICENSE](./LICENSE) para mais detalhes.

### Atribuição

De acordo com a Licença Apache 2.0, qualquer uso do código, seja modificado ou não, deve incluir a atribuição ao autor original. Se você fizer modificações, é necessário incluir uma menção a isso e referir o nome do autor original (no caso, você) conforme especificado na seção de atribuição da licença.




