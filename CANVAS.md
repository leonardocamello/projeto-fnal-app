| Informação | Detalhes |

| **Grupo** | B3 Pizza |
| **Integrantes** | 3 |
| **Turma** | 3º ano B — Ensino Médio |
| **Repositório** | [GitHub] (https://github.com/leonardocamello/projeto-final-app) |
| **Data de preenchimento** | 09/09/2026 |
| **Entrega final** | 10/12/2026 |
## 🧩 Bloco 1 — Nome e pitch do app

**Nome do app:** Agro Play

**Pitch em uma frase:**

> O **Agro Play** ajuda produtores e funcionários de fazendas a controlar demandas, quantidades de produtos e locais de armazenamento de forma simples e organizada.

---

## 😖 Bloco 2 — Problema

- Fazendas podem ter dificuldade para acompanhar a quantidade disponível de produtos, insumos e materiais.
- A falta de organização pode causar desperdícios, compras desnecessárias, falta de itens importantes e dificuldade para localizar produtos armazenados.

**Como esse problema é resolvido hoje (sem o app)?**

- Por meio de anotações em papel, planilhas, mensagens ou controle baseado apenas na memória dos responsáveis.

---

## 👥 Bloco 3 — Público-alvo

- **Perfil principal:** Produtores rurais, administradores e funcionários responsáveis pelo estoque e pelas demandas da fazenda.
- **Quando/onde usam:** Durante a rotina de trabalho, principalmente ao receber, armazenar, retirar ou conferir produtos e insumos.
- **Uma pessoa real que testaria o app:** O pai do integrante Leonardo que trabalha em uma fazenda.

---

## 💡 Bloco 4 — Solução em uma tela

- **A tela principal lista:** Produtos e insumos cadastrados, quantidades disponíveis, locais de armazenamento e demandas pendentes.
- **A ação principal do usuário é:** Cadastrar produtos e atualizar suas quantidades, locais de armazenamento e necessidades de reposição.
- **Depois de agir, o usuário vê:** Os dados atualizados e a situação atual do estoque.

---

## ✅ Bloco 5 — Funcionalidades do MVP

| # | Funcionalidade | Essencial? | Quem faz |
|---|---|---|---|
| F1 | Cadastrar, editar e excluir produtos ou insumos | Sim | Leonardo |
| F2 | Registrar e atualizar a quantidade disponível | Sim | Raymison |
| F3 | Informar o local de armazenamento de cada item | Sim | Josue |
| F4 | Registrar demandas de reposição e indicar estoque baixo | Não | os 3 |

---

## 🚫 Bloco 6 — Fora do escopo

- ❌ Login e cadastro de usuários.
- ❌ Sincronização em nuvem entre vários dispositivos.
- ❌ Compras, pagamentos ou contato automático com fornecedores.

---

## ⚙️ Bloco 7 — Caminho técnico

- [x] **Opção A — Room**
- [ ] **Opção B — Retrofit**
- [ ] **Opção C — API + dados locais**

**Se escolheu B ou C — qual API?** Não se aplica.

**Bibliotecas que o grupo vai usar:** Jetpack Compose, Material 3, Navigation Compose, Room, ViewModel e Kotlin Coroutines.

**Onde entra o `try/catch`?**

- **Pode falhar:** Cadastro, edição, exclusão ou consulta dos dados no banco local; campos obrigatórios também podem ficar vazios ou receber valores inválidos.
- **O usuário vê a mensagem:** “Não foi possível concluir a operação. Verifique os dados e tente novamente.”

---

## 🎨 Bloco 8 — Identidade visual

| Item | Definição do grupo |
|---|---|
| Nome exibido (`strings.xml`) | Agro Play |
| Cor principal (hex, em `Color.kt`) | `#2E7D32` |
| Ideia do ícone (512×512) | Folha verde combinada com um celeiro e um símbolo de controle de estoque |
| `applicationId` | `br.edu.ifpe.agroplay` |
| Versão inicial | `1.0` (versionCode `1`) |

---

## 👤 Bloco 9 — Equipe, papéis e riscos

| Integrante | Papel principal | Responsável por |
|---|---|---|
| leonardo | Dev / telas | Interface, componentes e navegação |
| Raymison | Dev / dados | Room, entidades, DAO e repositório |
| Josue | Design e identidade visual | Cores, ícone e padronização visual |
| os 3 | Documentação, build e entrega | README, testes, APK e AAB |

**Riscos — o que pode dar errado e o plano B:**

| Risco | Plano B |
|---|---|
| A integração com o Room apresentar erros | Testar primeiro operações simples e manter dados temporários em memória durante os ajustes |
| O grupo não concluir todas as funcionalidades | Priorizar F1, F2 e F3 e deixar F4 para uma versão posterior |

---

## 🤖 Bloco 10 — Acordo de trabalho com IA

**Três regras que vamos escrever no nosso `AGENTS.md`:**

1. A IA deve explicar as alterações realizadas e indicar os arquivos modificados.
2. A IA deve manter o padrão MVVM, usar nomes claros e evitar código desnecessário.
3. A IA não deve remover funcionalidades existentes sem autorização do grupo.

**Combinados do grupo:**

- [ ] Ninguém aceita uma alteração gerada por IA sem revisar o código completo.
- [ ] Quem aceitar o código deverá compreender e explicar seu funcionamento.
- [ ] Antes de cada marco, o grupo revisará em conjunto as partes implementadas.
- [ ] Nenhuma chave de API, senha ou dado sensível será enviado à IA.
- **Outro combinado nosso:** Todos os integrantes devem participar dos testes antes da entrega.

**Como vamos garantir que todos entendem tudo:**

- Cada integrante apresentará aos demais a parte em que trabalhou, e o grupo revisará os códigos e pull requests em conjunto.

---

## 🗓️ Bloco 11 — Marcos até 10/12


| Marco | Prazo | Como se comprova no GitHub |
|---|---|---|
| M1 — Canvas preenchido + repositório criado | 16/09 | `CANVAS.md` no `main` |
| M2 — PRD aprovado + telas rascunhadas | 30/09 | `PRD.md` + imagens em `docs/` |
| M3 — Funcionalidade base rodando | 21/10 | Tela principal lista dados + uma ação + `try/catch` |
| M4 — Dados completos com Room e erros tratados | 11/11 | Commits da camada de dados |
| M5 — Identidade visual + `.apk` de release testado | 25/11 | Ícone, cores e `.apk` testado por duas pessoas de fora |
| M6 — `.aab` + material de loja + `README.md` | 02/12 | Pasta `loja/` + `README.md` completo |
| **Entrega e apresentação** | **10/12** | Tag `v1.0` no repositório |

---

## 🏁 Bloco 12 — Definição de pronto

- [ ] O app abre e permanece funcionando durante o uso.
- [ ] A tela principal mostra dados reais cadastrados pelo usuário.
- [ ] A ação principal funciona e o resultado aparece na tela.
- [ ] Quando algo falha, aparece uma mensagem clara e o app não fecha sozinho.
- [ ] O app possui nome, ícone e cores próprios.
- [ ] Duas pessoas de fora do grupo instalaram e utilizaram o `.apk`.
- [ ] O `README.md` explica o app, as tecnologias utilizadas e como gerar o build.
- [ ] O `docs/USO_DE_IA.md` e o `AGENTS.md` estão preenchidos.
- [ ] Cada integrante consegue abrir o projeto e realizar uma pequena alteração.
- [ ] Os arquivos criados pelo grupo possuem comentários de fronteira.

---

## ✍️ Validação do professor

| | |
|---|---|
| Data | |
| Situação | ( ) Aprovado  ( ) Aprovado com ajustes  ( ) Refazer |
| Observações | |
