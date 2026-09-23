**Documento de requisitos do produto | Grupo B3 Pizza | 3º ano B do Ensino Médio**

**Versão 1.0 do documento • 23/09/2026 • Em revisão pelo grupo e pelo professor**

O Agro Play organiza o estoque de produtos e insumos da fazenda em um dispositivo Android.

Este documento define o que a primeira versão precisa fazer, como verificar cada requisito e o que ainda depende de decisão do grupo.

| Informação | Definição |
|---|---|
| **Aplicativo** | Agro Play |
| **Grupo** | B3 Pizza — Leonardo, Raymison e Josue |
| **Repositório** | https://github.com/leonardocamello/projeto-final-app |
| **Canvas preenchido em** | 09/09/2026 |
| **Entrega final** | 10/12/2026 |
| **Status do PRD** | Em revisão; aprovação do professor pendente |

---

# 1. Visão do produto

**Pitch:** O Agro Play ajuda produtores e funcionários de fazendas a controlar demandas, quantidades de produtos e locais de armazenamento de forma simples e organizada.

**Problema:** O controle em papel, planilhas, mensagens ou de memória dificulta saber quanto há de cada produto e onde ele está guardado. Isso pode provocar desperdício, compras desnecessárias e falta de insumos importantes.

**Resultado esperado:** Quem trabalha na fazenda consulta o estoque e atualiza informações no momento em que recebe, guarda, retira ou confere um item. Os dados ficam organizados para a próxima consulta.

---

# 2. Público e cenário de uso

**Usuários principais:** produtores rurais, administradores e funcionários responsáveis pelo estoque e pelas demandas da fazenda.

O pai de Leonardo, que trabalha em uma fazenda, é um possível participante do teste externo; o convite e o teste ainda precisam ser confirmados.

**História de uso:** Durante uma conferência no depósito, uma funcionária abre o Agro Play, encontra um insumo na lista, consulta o local de armazenamento e corrige a quantidade depois de uma retirada.

Ao voltar à tela principal, ela vê o estoque atualizado.

Quando não há itens cadastrados, o app explica como começar.

---

# 3. Objetivos e não objetivos

## Objetivos da versão 1.0

1. Cadastrar, consultar, editar e excluir produtos ou insumos.
2. Registrar e atualizar a quantidade disponível de cada item.
3. Registrar e consultar o local de armazenamento de cada item.

Se houver tempo após os objetivos essenciais, registrar demandas de reposição e indicar estoque baixo.

## Fora do escopo

- Login e cadastro de usuários.
- Sincronização em nuvem entre dispositivos.
- Compras, pagamentos e contato automático com fornecedores.

---

# 4. Requisitos funcionais

**Prioridade Must:** necessária para a entrega.  
**Could:** prevista apenas se houver tempo.

| ID | História de usuário | Critério de aceite | Prioridade |
|---|---|---|---|
| **RF01** | Como responsável pelo estoque, quero ver os itens cadastrados para conferir a situação atual. | Ao abrir o app, cada item salvo mostra nome, quantidade e local; sem itens, aparece “Nenhum item cadastrado”. | Must |
| **RF02** | Como responsável, quero cadastrar um produto ou insumo para iniciar seu controle. | Ao confirmar nome, quantidade válida e local, o item aparece na lista e continua lá após reabrir o app. | Must |
| **RF03** | Como responsável, quero editar ou excluir um item para corrigir o cadastro. | Ao salvar uma edição, a lista mostra os novos dados; ao confirmar a exclusão, o item deixa de aparecer e não volta após reabrir. | Must |
| **RF04** | Como responsável, quero atualizar a quantidade disponível para registrar entradas e retiradas. | Ao informar uma quantidade inteira igual ou maior que zero e salvar, a lista exibe o novo valor; valores inválidos são recusados. | Must |
| **RF05** | Como responsável, quero registrar o local de armazenamento para localizar o item. | O cadastro exige um local; a edição aceita mudança de local e a lista exibe o valor salvo. | Must |
| **RF06** | Como responsável, quero registrar demandas de reposição e identificar estoque baixo para planejar compras. | Se implementado, o app registra uma demanda e sinaliza o item abaixo do limite definido; o limite será definido antes da implementação. | Could |

---

# 5. Requisitos não funcionais

| ID | Requisito | Verificação |
|---|---|---|
| **RNF01** | O app não fecha sozinho durante o uso normal. | Usar por 5 minutos em dois aparelhos Android, cadastrando, editando, consultando e excluindo itens. |
| **RNF02** | Operações do banco local tratam falhas com try/catch na camada adequada. | Revisar o código e simular falha de leitura ou gravação; conferir se o app mantém a tela e mostra aviso. |
| **RNF03** | Entradas inválidas recebem orientação clara, sem perda dos dados digitados. | Tentar nome ou local vazio e quantidade negativa ou não numérica. |
| **RNF04** | O app funciona a partir da versão Android definida no projeto. | Definir minSdk no Gradle, registrar o valor neste PRD e instalar em aparelho compatível. |
| **RNF05** | Textos visíveis ficam em strings.xml. | Revisar telas e recursos do projeto. |
| **RNF06** | Arquivos criados pelo grupo possuem comentários de fronteira. | Revisar os arquivos do pacote do app. |
| **RNF07** | Cada integrante consegue abrir o projeto e fazer uma pequena alteração. | Realizar uma mudança ao vivo por integrante, antes da entrega. |
| **RNF08** | O estoque salvo permanece no dispositivo após fechar e abrir o app. | Cadastrar item, encerrar o processo e reabrir o app; conferir os dados. |

---

# 6. Telas e navegação

## Fluxo principal

**Tela Principal → botão “Adicionar” → Cadastro → salvar → Tela Principal**

Ao tocar em um item:

**Tela Principal → Detalhe e Edição → salvar ou excluir → Tela Principal**

A lista vazia mostra uma mensagem e acesso ao cadastro.

| Tela | O que mostra | Ações disponíveis |
|---|---|---|
| **Principal** | Lista com nome, quantidade e local de cada item; mensagem de estado vazio quando necessário. | Abrir item; iniciar cadastro. |
| **Cadastro** | Campos de nome, quantidade e local de armazenamento. | Salvar ou cancelar; validar campos. |
| **Detalhe e edição** | Dados atuais do item e campos editáveis. | Alterar dados, salvar, excluir com confirmação ou voltar. |

**Rascunhos das telas:** pendentes.

Após a criação, salvar as imagens em `docs/telas/` e incluir seus nomes no repositório.

Não há imagens de telas confirmadas no canvas.

---
## 7. Dados

**Persistência:** Room, em banco local no dispositivo. Modelo inicial proposto para atender F1 a F3; os nomes finais dos campos podem ser ajustados pelo grupo antes da implementação.

| Entidade    | Campo              | Tipo   | Regra                                         |
| ----------- | ------------------ | ------ | --------------------------------------------- |
| ItemEstoque | id                 | Long   | Chave primária autogerada.                    |
| ItemEstoque | nome               | String | Obrigatório; não aceitar apenas espaços.      |
| ItemEstoque | quantidade         | Int    | Obrigatório; inteiro igual ou maior que zero. |
| ItemEstoque | localArmazenamento | String | Obrigatório; não aceitar apenas espaços.      |

**Operações necessárias:** inserir, listar, atualizar e excluir.

Cada alteração persistida deve aparecer na lista após o salvamento e continuar disponível ao reabrir o app.

Não há API, Retrofit nem chave de acesso nesta versão.

Dados da funcionalidade opcional de demandas e limite de estoque: definir o modelo apenas se RF06 entrar na versão 1.0.

## 8. Arquitetura e tecnologias

| Item                  | Escolha                                                                  |
| --------------------- | ------------------------------------------------------------------------ |
| Linguagem e interface | Kotlin, Jetpack Compose e Material 3                                     |
| Navegação             | Navigation Compose                                                       |
| Arquitetura           | MVVM com ViewModel e repositório                                         |
| Persistência          | Room, com entidade, DAO e banco local                                    |
| Trabalho assíncrono   | Kotlin Coroutines                                                        |
| Rede                  | Não utilizada nesta versão                                               |
| applicationId         | `br.edu.ifpe.agroplay`                                                   |
| minSdk e targetSdk    | A definir pelo grupo conforme os aparelhos de teste e o projeto Android. |

**Organização prevista:**

* `ui/` para telas e estado de interface;
* `data/` para entidade, DAO, banco e repositório;
* `MainActivity.kt` como ponto de entrada.

A estrutura final deve manter responsabilidades separadas conforme MVVM.

## 9. Tratamento de erros

Validações de formulário mostram mensagens de campo. Falhas de leitura e gravação do Room são capturadas para manter a tela utilizável.

| Situação                                   | Comportamento do app                                                         | Mensagem ao usuário                                                         |
| ------------------------------------------ | ---------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| Lista vazia                                | Exibe estado vazio e botão para cadastrar; não é erro técnico.               | Nenhum item cadastrado. Adicione o primeiro produto ou insumo.              |
| Nome ou local em branco                    | Impede o salvamento e mantém os dados digitados.                             | Preencha o nome e o local de armazenamento.                                 |
| Quantidade inválida                        | Impede o salvamento e mantém os demais campos.                               | Informe uma quantidade inteira igual ou maior que zero.                     |
| Falha ao salvar, editar ou excluir no Room | Mantém a tela e os dados editados, sem afirmar que a operação foi concluída. | Não foi possível concluir a operação. Verifique os dados e tente novamente. |
| Falha ao consultar o Room                  | Mantém a tela e oferece nova tentativa.                                      | Não foi possível carregar os itens. Tente novamente.                        |

Falhas de internet ou de API não se aplicam ao MVP local.

## 10. Identidade visual e publicação

| Item            | Definição                                                 | Arquivo previsto     |
| --------------- | --------------------------------------------------------- | -------------------- |
| Nome exibido    | Agro Play                                                 | `strings.xml`        |
| Cor principal   | `#73E841`                                                 | `Color.kt`           |
| Cor secundária  | A definir pelo grupo                                      | `Color.kt`           |
| Ícone 512 × 512 | Celeiro com lista de checagem; arte ainda será produzida. | `loja/icone-512.png` |
| applicationId   | `br.edu.ifpe.agroplay`                                    | `build.gradle.kts`   |
| Versão inicial  | `versionName 1.0; versionCode 1`                          | `build.gradle.kts`   |

### Material previsto para a loja e para a entrega

| Artefato                            | Conteúdo previsto ou situação                                                                                                 |
| ----------------------------------- | ----------------------------------------------------------------------------------------------------------------------------- |
| Título (até 30 caracteres)          | Agro Play                                                                                                                     |
| Descrição curta (até 80 caracteres) | Controle produtos, quantidades e locais de armazenamento da fazenda.                                                          |
| Descrição completa                  | A redigir em `loja/descricao.md` após validar a implementação.                                                                |
| Imagem de destaque 1024 × 500       | A produzir em `loja/destaque-1024x500.png`.                                                                                   |
| Capturas de tela (mínimo 2)         | A produzir em `loja/screenshots/` com telas reais.                                                                            |
| Esboço de privacidade               | A documentar em `loja/privacidade.md`. O MVP prevê banco local e não prevê nuvem; confirmar práticas reais antes de publicar. |
| AAB de release                      | A gerar em `loja/app-release.aab` após os testes.                                                                             |


## 11. Plano de testes

| ID | Cenário e passos                                                                                | Resultado esperado                                                          | Situação |
| -- | ----------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------- | -------- |
| T1 | Instalar e abrir sem dados cadastrados.                                                         | Tela principal abre e explica o estado vazio.                               | Pendente |
| T2 | Cadastrar item com nome, quantidade e local; fechar e reabrir o app.                            | Item aparece na lista e continua salvo.                                     | Pendente |
| T3 | Editar nome, quantidade e local; depois excluir com confirmação.                                | Mudanças aparecem na lista; exclusão persiste.                              | Pendente |
| T4 | Tentar salvar com nome ou local vazio, quantidade negativa ou texto não numérico.               | Mensagem clara; app permanece aberto e preserva os campos.                  | Pendente |
| T5 | Simular falha de leitura ou gravação do banco durante revisão técnica.                          | Mensagem de erro; nenhuma confirmação falsa e nenhum fechamento inesperado. | Pendente |
| T6 | Duas pessoas de fora do grupo instalam o APK e completam o cadastro e a atualização de um item. | As duas concluem as ações; dificuldades são registradas.                    | Pendente |

**Observação:** Aparelhos e versões de Android usados devem ser registrados após os testes em pelo menos dois aparelhos. A pessoa indicada no canvas é um possível testador; nenhum teste externo foi confirmado.

---

## 12. Cronograma

| Marco                                 | Prazo | Responsáveis                      | Situação em 23/09/2026                            |
| ------------------------------------- | ----- | --------------------------------- | ------------------------------------------------- |
| M1 — Canvas e repositório             | 16/09 | Grupo                             | Canvas fornecido; commit no main a confirmar.     |
| M2 — PRD aprovado e telas rascunhadas | 30/09 | Grupo                             | PRD em revisão; rascunhos e aprovação pendentes.  |
| M3 — Funcionalidade base              | 21/10 | Leonardo e Raymison               | Planejado: lista, uma ação e tratamento de falha. |
| M4 — Dados completos com Room         | 11/11 | Raymison, com integração do grupo | Planejado: operações de dados e erros tratados.   |
| M5 — Identidade e APK testado         | 25/11 | Josue, com testes do grupo        | Planejado: ícone, cores e duas pessoas externas.  |
| M6 — AAB, loja e README               | 02/12 | Grupo                             | Planejado: pasta loja/ e README completo.         |
| Entrega e apresentação                | 10/12 | Grupo                             | Planejado: tag v1.0 no repositório.               |

---

## 13. Riscos

| Risco                                            | Impacto                                                     | Plano B                                                                                              |
| ------------------------------------------------ | ----------------------------------------------------------- | ---------------------------------------------------------------------------------------------------- |
| Erros na integração com Room                     | Atraso no controle do estoque.                              | Testar operações simples; usar dados em memória durante os ajustes e concluir Room antes da entrega. |
| Tempo insuficiente para todas as funcionalidades | Demandas e alerta de estoque baixo podem ficar incompletos. | Priorizar cadastro e edição, quantidades e locais (F1 a F3); adiar F4.                               |

---

## 14. Orientação da implementação com IA

O grupo revisará, compreenderá e testará as sugestões da IA antes de aceitá-las. O uso será registrado em `docs/USO_DE_IA.md`.

### Regras propostas para AGENTS.md

* Explicar cada alteração e indicar os arquivos modificados.
* Manter MVVM, usar nomes claros e evitar código desnecessário.
* Não remover funcionalidades existentes sem autorização do grupo.

### Combinados

Cada integrante explica sua parte, todos revisam o código e participam dos testes, e ninguém envia dados sensíveis à IA.

### Responsabilidades

| Parte                                  | Responsável principal |
| -------------------------------------- | --------------------- |
| Interface, componentes e navegação     | Leonardo              |
| Room, entidade, DAO e repositório      | Raymison              |
| Cores, ícone e padronização visual     | Josue                 |
| Documentação, build, APK, AAB e testes | Os três integrantes   |

Registrar os recursos de IA usados e as decisões do grupo em `docs/USO_DE_IA.md` ao longo do projeto.

---

## 15. Histórico de versões deste documento

**Versão 1.0 • 23/09/2026**

Grupo B3 Pizza — Preenchimento inicial com base no canvas de 09/09/2026.

**Status:** Revisão e validação pendentes.
