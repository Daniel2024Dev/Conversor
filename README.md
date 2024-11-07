# Conversor JAVA

Esta aplicação JavaFX foi desenvolvida para converter entre unidades de medida, especificamente "newton-metro" (Nm) e "quilograma-força" (kgf). Além da funcionalidade de conversão, a aplicação armazena um histórico das conversões realizadas em um banco de dados SQLite e exibe o histórico ao usuário. O fluxo de dados e a navegação na interface gráfica são bem definidos e organizados. Aqui está um resumo das principais funcionalidades e estrutura do código:
Estrutura Principal

    Interface Gráfica (JavaFX):
        A aplicação usa JavaFX para criar uma interface gráfica com componentes como TextField, Button, CheckBox, e Alert para interação com o usuário.
        As views (telas) são definidas em arquivos FXML e carregadas dinamicamente.
        O usuário pode inserir valores, escolher as unidades de conversão e ver os resultados de forma intuitiva.

    Banco de Dados SQLite:
        Utiliza o SQLite como banco de dados para armazenar o histórico das conversões realizadas.
        O banco de dados é armazenado em um diretório específico e criado automaticamente, caso não exista.
        As tabelas do banco de dados são criadas no início da aplicação, se ainda não existirem, e o histórico é mantido na tabela History.

    Conversão e Histórico:
        A conversão entre as unidades é feita de acordo com os valores inseridos pelo usuário, e o histórico dessas conversões é exibido de forma clara.
        O histórico é armazenado no banco de dados e pode ser visualizado em uma tela separada, onde o usuário pode revisar as conversões realizadas anteriormente.

    Validações e Alertas:
        O código realiza validações nas entradas do usuário, garantindo que apenas valores válidos sejam processados.
        Alertas são exibidos automaticamente para informar o usuário sobre erros ou confirmações, e os alertas são fechados automaticamente após um tempo definido.

    Classes e Funcionalidade:
        MainApplication: A classe principal da aplicação, que inicializa a interface gráfica e gerencia a janela principal da aplicação.
        Dao: Gerencia a conexão com o banco de dados SQLite, cria diretórios e tabelas necessários.
        HistoryDao: Lida com as operações de leitura e gravação no histórico de conversões no banco de dados.
        ConversionModel: Representa o modelo de dados de uma conversão, contendo informações como título e descrição.
        HomeViewController: Controla a tela principal de conversão, onde o usuário interage com os campos de entrada e realiza a conversão.
        MultiViewController: Gerencia a navegação entre diferentes views (como a tela de histórico).
        Alerts: Utilitário para exibir alertas ao usuário.
        Constraints: Fornece funções auxiliares, como mascarar a entrada de números decimais nos campos de texto.

Fluxo de Uso

    O usuário insere um valor e escolhe as unidades que deseja converter (de Nm para kgf ou vice-versa).
    O sistema realiza a conversão e adiciona o resultado ao histórico.
    O histórico pode ser acessado para visualizar as conversões passadas.
    Alertas são exibidos para feedback sobre o processo, e entradas inválidas são tratadas com mensagens apropriadas.
