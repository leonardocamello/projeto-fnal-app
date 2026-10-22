Prompt do lovable: Adicione ao aplicativo Android existente (Kotlin + Jetpack Compose + Room) duas NOVAS telas de "login" de fazenda, SEM modificar as telas de armazém (estoque) e cadastro de itens que já existem. Apenas integre-as ao fluxo, conforme descrito.

O QUE ADICIONAR:

1) TELA DE LOGIN (tela inicial do app)
- Campos de texto: "Nome do proprietário", "Nome da fazenda" e "Localidade".
- Validação: os três campos são obrigatórios; ao tocar em "Entrar" com campo vazio, mostrar erro "Informe: [nome do campo]" abaixo do campo.
- Botão "Entrar": salva um novo registro de fazenda (proprietário, fazenda, localidade) no Room, mostra mensagem de sucesso e navega para a tela de lista de logins.
- Botão/link secundário "Ver logins" que leva à tela de lista de logins sem salvar.
- Essa deve ser a tela aberta ao iniciar o app.

2) TELA DE LISTA DE LOGINS (Fazendas)
- Título "Fazendas", subtítulo "Logins", e botão "Novo login" que volta para a tela de login.
- Lista todos os logins salvos, cada um em um cartão mostrando: nome da fazenda (título), nome do proprietário e localidade (com ícones de usuário e localização).
- Se a lista estiver vazia, mostrar "Nenhum login cadastrado" com botão "Fazer login".
- Ao tocar em um login, ele passa a ser a fazenda atual e o app navega para a tela de armazém (estoque) JÁ EXISTENTE, que deve passar a listar somente os itens vinculados a essa fazenda.

O QUE AJUSTAR (mínimo necessário, sem reescrever as telas existentes):
- Entidade Fazenda no Room: id (Int, auto-incremento a partir de 1), proprietario (String), fazenda (String), localidade (String).
- Entidade ItemEstoque existente: adicionar campo fazendaId (Int, nullable para itens antigos) para vincular cada item à sua fazenda.
- DAO: consultas para inserir/listar fazendas e para filtrar os itens de estoque por fazendaId.
- A tela de estoque existente deve exibir o nome da fazenda atual no título e um link "Trocar farmácia/fazenda" que volta para a lista de logins.
- Perguntar a fazenda atual ao abrir o estoque; se nenhuma estiver selecionada, ir para a lista de logins.
- Itens antigos sem fazenda vinculada não aparecem em nenhuma fazenda (comportamento esperado).

REQUISITOS GERAIS:
- Não alterar visual, textos nem lógica das telas de armazém e cadastro de itens, exceto o filtro por fazenda e o título descritos acima.
- Snackbars/toasts para sucesso e erro.
- Visual igual ao app: cantos arredondados, cartões com borda sutil, tipografia limpa.
- Tudo em português (pt-BR).

