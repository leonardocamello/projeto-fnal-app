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
