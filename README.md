# Sistema de Cotação de Moedas

Sistema Java em arquitetura MVC para consultar cotações de moedas em tempo real usando a API AwesomeAPI.

## Funcionalidades

- Consulta de cotações de diversas moedas (USD, EUR, GBP, JPY, etc.)
- Exibição de valores de compra, venda e variação
- Interface console simples e intuitiva
- Arquitetura MVC bem definida

## Tecnologias

- Java 8+
- HttpURLConnection para requisições HTTP
- JSON.org para parsing de JSON
- Ant para build

## Como Executar

### Pré-requisitos
- Java JDK 8 ou superior
- Apache Ant
- Conexão com internet

### Passos para execução:

1. **Clone o repositório:**
   ```bash
   git clone <url-do-repositorio>
   cd cotacao-moedas
2. Baixe a biblioteca JSON:

bash
mkdir lib
# Baixe o json-20231013.jar e coloque na pasta lib/

3. Compile e execute:

bash
ant run
