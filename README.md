# Conversor de Moedas em Java

Um aplicativo de console Java para conversão de moedas que utiliza a ExchangeRate-API para obter taxas de câmbio atualizadas em tempo real.

## Sobre o Projeto

Este conversor de moedas permite aos usuários converter valores entre diferentes moedas, como Dólar Americano (USD), Real Brasileiro (BRL), Euro (EUR), Libra Esterlina (GBP) e Dólar Australiano (AUD). O aplicativo consulta a API ExchangeRate-API para obter as taxas de câmbio mais recentes, garantindo conversões precisas e atualizadas.

## Funcionalidades Principais

- Conversão entre múltiplos pares de moedas
- Obtenção de taxas de câmbio em tempo real via API
- Interface de usuário interativa via console
- Sistema de logs para rastreamento de atividades
- Histórico de conversões realizadas

## Estrutura do Projeto

O projeto segue princípios de arquitetura limpa e orientação a objetos:

```
src/
├── com/
│   └── teixeira/
│       └── currencyconverter/
│           ├── app/
│           │   └── CurrencyConverterApplication.java
│           ├── model/
│           │   ├── Currency.java
│           │   └── ExchangeRateApiResponse.java
│           └── service/
│               ├── AbstractApiClient.java
│               ├── CurrencyConversionService.java
│               ├── ExchangeRateApiClient.java
│               └── LoggerService.java
```

### Descrição dos Componentes

- **CurrencyConverterApplication**: Classe principal com o método `main` e interface do usuário
- **Currency**: Modelo para representar uma moeda com código e nome
- **ExchangeRateApiResponse**: Modelo para desserializar a resposta da API
- **AbstractApiClient**: Classe abstrata que fornece funcionalidades comuns para clientes de API
- **CurrencyConversionService**: Serviço responsável pela lógica de negócio da conversão de moedas
- **ExchangeRateApiClient**: Cliente que se comunica com a ExchangeRate-API
- **LoggerService**: Serviço para registro de logs de atividades e erros

### Padrões de Design Utilizados

- **Herança**: `ExchangeRateApiClient` herda de `AbstractApiClient`
- **Encapsulamento**: Atributos privados com getters/setters quando necessário
- **Separação de Responsabilidades**: Divisão clara entre interface do usuário, lógica de negócio e acesso a dados

## Tecnologias Utilizadas

- **Java 21:** Linguagem de programação principal utilizada no desenvolvimento.
- **GSON:** Biblioteca para serialização/desserialização de JSON.
- **ExchangeRate-API:** API externa para obtenção de taxas de câmbio atualizadas.
- **java.time:** API para manipulação de data e hora no sistema de logs.
- **java.net.http:** Cliente HTTP para comunicação com APIs externas.

## Chave da API (ExchangeRate-API)

O projeto utiliza a ExchangeRate-API para obter as taxas de câmbio. Para executar o aplicativo, é necessário obter uma chave de API gratuita em [https://www.exchangerate-api.com/](https://www.exchangerate-api.com/).

A chave da API deve ser configurada na constante `API_KEY` na classe `ExchangeRateApiClient.java`.

## Funcionalidades Avançadas

### Sistema de Logs

O aplicativo implementa um sistema de logs que registra:
- Início e encerramento da aplicação
- Seleções de menu e valores inseridos pelo usuário
- Chamadas à API e respostas recebidas
- Conversões realizadas com detalhes de valores e taxas
- Erros e exceções que possam ocorrer

Os logs são armazenados no arquivo `currency_converter_activity.log` com timestamps precisos usando `java.time.LocalDateTime` e formatados com `DateTimeFormatter`.

Exemplo de entrada de log:
```
[2024-05-15T14:30:22.123] [INFO] Aplicação Conversor de Moedas Iniciada.
[2024-05-15T14:30:25.456] [INFO] Opção do menu selecionada: 1
[2024-05-15T14:30:28.789] [INFO] Valor a ser convertido: 100.0
[2024-05-15T14:30:29.012] [INFO] Conversão de USD para BRL
[2024-05-15T14:30:30.345] [INFO] Taxa de conversão obtida para BRL: 5.0123
[2024-05-15T14:30:30.456] [INFO] Valor convertido: 501.23 BRL
```

### Histórico de Conversões

O aplicativo mantém um histórico das conversões realizadas durante a sessão, permitindo ao usuário visualizar suas atividades anteriores. Cada registro do histórico inclui:

- Moeda de origem e destino
- Valor original e valor convertido
- Taxa de câmbio utilizada
- Data e hora da conversão

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests com melhorias.

## Licença

Este projeto está licenciado sob a licença MIT - veja o arquivo LICENSE para detalhes.
