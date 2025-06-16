# 🛒 Microsservice E-commerce

Este é um projeto de microsserviço em Java com Spring Boot 3, que simula o funcionamento de um sistema de recomendação e fidelização de clientes baseado em dados de consumo de produtos — incluindo integração com serviços externos via REST.

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.5.0
- Spring Web
- SpringDoc OpenAPI (Swagger UI)
- Maven
- RestTemplate
- Lombok

## ✨ Funcionalidades

- 🔍 Listagem de produtos e clientes via APIs REST externas
- 🧠 Recomendação de tipo de vinho com base no histórico de compras
- 🏆 Identificação dos 3 clientes mais fiéis
- 📖 Documentação automática de API com Swagger

## 📦 Como Rodar o Projeto

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/microsservice-ecom.git
cd microsservice-ecom

📖 Endpoints Disponíveis
Método	Caminho	Descrição
GET	/compras	Retorna uma lista de compras ordenadas por valor total decrescente, contendo dados dos clientes, quantidades e valores totais.
GET	/maior-compra/{ano}	Retorna a maior compra realizada no ano informado.
GET	/clientes-fieis	Retorna os 3 clientes mais fiéis com base em compras recorrentes e valor total.
GET	/recomendacao/{cpf}/tipo	Retorna uma recomendação de tipo de vinho baseado nas últimas compras do cliente com o CPF informado.

