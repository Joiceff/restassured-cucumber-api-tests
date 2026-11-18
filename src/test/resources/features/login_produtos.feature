# language: pt
@login @produtos
Funcionalidade: Autenticação e cadastro de produtos
  Como usuário da loja
  Quero me autenticar
  Para acessar as rotas protegidas de produtos

  @smoke
  Cenário: Login com credenciais válidas
    Dado que existe um usuário cadastrado
    Quando realizo login com as credenciais do usuário
    Então o status code deve ser 200
    E a mensagem deve ser "Login realizado com sucesso"
    E a resposta deve respeitar o contrato "login.json"

  Cenário: Login com senha inválida
    Dado que existe um usuário cadastrado
    Quando realizo login com a senha "senhaErrada123"
    Então o status code deve ser 401
    E a mensagem deve ser "Email e/ou senha inválidos"

  @smoke
  Cenário: Administrador cadastra produto
    Dado que estou autenticado como administrador
    Quando cadastro um novo produto
    Então o status code deve ser 201
    E a mensagem deve ser "Cadastro realizado com sucesso"

  Cenário: Cadastro de produto sem token de acesso
    Quando cadastro um novo produto sem token
    Então o status code deve ser 401
    E a mensagem deve ser "Token de acesso ausente, inválido, expirado ou usuário do token não existe mais"

  Cenário: Usuário comum não pode cadastrar produto
    Dado que estou autenticado como usuário comum
    Quando cadastro um novo produto
    Então o status code deve ser 403
    E a mensagem deve ser "Rota exclusiva para administradores"
