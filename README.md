# SmartCitySim – Módulo de Coleta de Lixo Inteligente

Primeiramente quero esclarecer que o motivo do trabalho não ter a apresentação gravada eslides é o fato dos outros dois membros terem desistido da matéria ou algo do tipo. De qualquer forma, nenhum deles me ajudou com o trabalho, o que me obrigou a fazer a parte deles. O que envolvia código, documentação e slides. Como tive que correr já que nem mesmo avisar que não iam fazer nada eles avisaram, acabei tendo que completar tudo as pressas. O código está comentado classe por classe, de forma que um terceiro, como no seu caso, possa entender facilmente a estrutura e métodos delas.
Peço compreensão dada a situação causada pela falta de comprometimento dos outros integrantes.

Autor do projeto: João Vitor Justiniano Tolentino Barbosa - 202200528

## 🧠 Escopo do Trabalho

Este projeto é um **submódulo do simulador de cidade inteligente (SmartCitySim)**, com foco exclusivo no **Sistema de Coleta de Lixo Inteligente**. O objetivo do módulo é demonstrar a aplicação de princípios de **Programação Orientada a Objetos (POO)** e padrões de projeto em um cenário urbano automatizado.

---

## 🎯 Propósito do Sistema

### 💡 Finalidade

O sistema simula uma **coleta inteligente de lixo urbano** através de sensores de monitoramento e caminhões coletores. O sistema observa o acúmulo de lixo nos sensores e reage de forma autônoma ou acionada pelo usuário, realizando coletas otimizadas.

### 🛠️ Funcionalidades Implementadas

- **Monitoramento de Sensores:** Cada sensor detecta o nível de lixo acumulado.
- **Aumento de Nível de Lixo:** Simula o acúmulo de resíduos com valores aleatórios.
- **Alerta Visual:** Sensores ficam vermelhos e notificam quando estão cheios.
- **Coleta de Lixo:** Caminhões percorrem os sensores e esvaziam os cheios.
- **Escolha de Estratégia de Coleta:**
  - *Sequencial:* coleta na ordem dos sensores.
  - *Por nível mais alto:* coleta os mais cheios primeiro.
- **Animação da Coleta:** Mostra o caminhão passando sensor a sensor com delay.
- **Restauração de Estado:** Utiliza o padrão *Memento* para desfazer alterações.
- **Interface Gráfica (Swing):** Todos os recursos são acessados via UI interativa.

---

## ▶️ Instruções para Execução

### ✅ Pré-requisitos

- Java JDK 11+ instalado
- Git (opcional)
- VS Code ou terminal

### 📥 Clonar, compilar e executar o projeto

```bash
git clone https://github.com/JeyVitella/SmartCitySim-Projeto-POO.git
cd SmartCitySim-Projeto-POO
mkdir -p bin
find . -name "*.java" > sources.txt
javac -d bin @sources.txt
java -cp bin ui.JanelaPrincipal


