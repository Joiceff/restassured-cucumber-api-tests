# language: pt
@usuarios
Funcionalidade: Gerenciamento de usuários
  Como administrador da loja
  Quero cadastrar, consultar e excluir usuários
  Para controlar quem acessa o sistema

  @smoke
  Cenário: Cadastrar usuário com dados válidos
    Dado que tenho os dados de um novo usuário administrador
    Quando envio a requisição de cadastro de usuário
    Então o status code deve ser 201
    E a mensagem deve ser "Cadastro realizado com sucesso"
    E a resposta deve respeitar o contrato "usuario-cadastro.json"

  Cenário: Não permitir cadastro com e-mail já utilizado
    Dado que existe um usuário cadastrado
    Quando tento cadastrar outro usuário com o mesmo e-mail
    Então o status code deve ser 400
    E a mensagem deve ser "Este email já está sendo usado"

  Cenário: Consultar usuário cadastrado por id
    Dado que existe um usuário cadastrado
    Quando consulto o usuário pelo id
    Então o status code deve ser 200
    E os dados retornados devem corresponder ao usuário cadastrado

  Cenário: Excluir usuário cadastrado
    Dado que existe um usuário cadastrado
    Quando excluo o usuário pelo id
    Então o status code deve ser 200
    E a mensagem deve ser "Registro excluído com sucesso"

  Esquema do Cenário: Validar campo obrigatório "<campo>" no cadastro
    Dado que tenho os dados de um novo usuário administrador
    E o campo "<campo>" está vazio
    Quando envio a requisição de cadastro de usuário
    Então o status code deve ser 400
    E o campo "<campo>" deve retornar a mensagem "<mensagem>"

    Exemplos:
      | campo    | mensagem                         |
      | nome     | nome não pode ficar em branco     |
      | email    | email não pode ficar em branco    |
      | password | password não pode ficar em branco |
