---

# 📊 Derivação Numérica em Java

Este projeto é uma aplicação Java que implementa a derivação numérica de funções matemáticas, utilizando métodos de diferenciação. A interface gráfica é construída com Swing, permitindo ao usuário selecionar métodos de derivação, inserir funções e visualizar os resultados. Este projeto foi desenvolvido no contexto da disciplina de **Matemática Computacional**.

## 📑 Índice

1. [Descrição do Projeto](#-descrição-do-projeto)
2. [Funcionalidades](#%EF%B8%8F-funcionalidades)
3. [Tecnologias Utilizadas](#-tecnologias-utilizadas)
4. [Pré-requisitos](#-pré-requisitos)
5. [Instalação](#-instalação)
6. [Como Usar](#-como-usar)
7. [Limitações](#-limitações)
8. [Autoria](#-autoria)
9. [Contribuições](#-contribuições)
10. [Licença](#-licença)

## 📝 Descrição do Projeto

Este projeto foi desenvolvido para fornecer uma ferramenta prática e interativa para o cálculo de derivadas numéricas. Os usuários podem escolher entre diferentes métodos de derivação, como diferença finita e interpolação numérica. A aplicação é útil para estudantes e profissionais que desejam explorar conceitos de cálculo diferencial. Este projeto pode ser visto como uma continuação de um projeto anterior, com melhorias e novas funcionalidades.

## ⚙️ Funcionalidades

- **Escolha de Métodos**: O usuário pode selecionar entre diferentes métodos de derivação, incluindo:
  - Derivada por Interpolação Numérica
  - Derivada por Diferença Finita (Taylor)
  - Segunda Derivada por Diferença Finita
  - Expressões Derivadas - Representação
    
- **Entrada de Funções**: O usuário pode digitar funções matemáticas utilizando a notação apropriada (apenas números e a variável `x`).
- **Cálculo de Derivadas**: Realiza cálculos para a derivada primeira e segunda das funções inseridas.
- **Interface Gráfica**: Apresenta uma interface intuitiva para facilitar a interação do usuário com a aplicação.
- **Exibição de Resultados**: Mostra os resultados das derivadas e erros relativos de maneira clara e organizada.

## 🛠 Tecnologias Utilizadas

- **Java**: Linguagem de programação utilizada para o desenvolvimento da aplicação.
- **Swing**: Biblioteca gráfica para construção da interface do usuário.
- **Java Math**: Utilizada para operações matemáticas como potência e funções exponenciais.

## 📝 Pré-requisitos
Para executar este projeto, você precisará de:
- JDK 11 ou superior instalado.
- IDE de sua escolha (ex: IntelliJ IDEA, Eclipse, NetBeans).
- O Java Math Library é parte do JDK, então não requer instalação adicional.

## 🚀 Instalação
1. **Clone o repositório:**
   ```bash
   git clone https://github.com/smuelp/derivacao-numerica.git
   ```

2. **Navegue até o diretório do projeto:**
   ```bash
   cd derivacao-numerica
   ```

3. **Abra o projeto em sua IDE.**

4. **Compile e execute a aplicação:**
   - Na sua IDE, execute a classe `DerivacaoNumerica.java` para iniciar a aplicação.

## 📚 Como Usar

1. **Selecionar um Método**: No menu suspenso, escolha o método de derivação desejado.
2. **Inserir Função**: Digite a função que você deseja derivar no campo apropriado.
3. **Inserir Valores de `x` e `h`**: Informe os valores para `x` e `h` se necessário, de acordo com o método selecionado.
4. **Calcular**: Clique no botão "Calcular" para realizar a derivação.
5. **Ver Resultados**: Os resultados aparecerão na área de texto abaixo.

## ⚠️ Limitações

- **Cálculo de Derivadas Trigonométricas**: O método de "Expressões Derivadas - Representação" **não realiza cálculos de derivadas trigonométricas**, pois a implementação atual pode ser complexa e imprecisa. Recomenda-se utilizar outras abordagens para funções trigonométricas.

## 👤 Autoria
Desenvolvido por:
- **Samuel I. Pena**

## 🤝 Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou um pull request. Para contribuir, siga os passos abaixo:
1. Faça um fork do repositório.
2. Crie uma branch para sua feature (`git checkout -b minha-feature`).
3. Faça commit das suas alterações (`git commit -m 'Adiciona nova feature'`).
4. Envie suas alterações para o repositório remoto (`git push origin minha-feature`).
5. Abra um pull request.

## 📄 Licença
Este projeto é licenciado sob a [MIT License](LICENSE).

---
