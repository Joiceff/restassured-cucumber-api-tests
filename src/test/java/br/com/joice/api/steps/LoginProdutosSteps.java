package br.com.joice.api.steps;

import br.com.joice.api.model.Produto;
import br.com.joice.api.model.Usuario;
import br.com.joice.api.support.TestContext;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;

import java.util.Map;

public class LoginProdutosSteps {

    private final TestContext ctx;
    private final UsuariosSteps usuarios;

    public LoginProdutosSteps(TestContext ctx, UsuariosSteps usuarios) {
        this.ctx = ctx;
        this.usuarios = usuarios;
    }

    @Quando("realizo login com as credenciais do usuário")
    public void loginComCredenciais() {
        ctx.resposta = login(ctx.usuario.getEmail(), ctx.usuario.getPassword());
    }

    @Quando("realizo login com a senha {string}")
    public void loginComSenha(String senha) {
        ctx.resposta = login(ctx.usuario.getEmail(), senha);
    }

    @Dado("que estou autenticado como administrador")
    public void autenticadoComoAdmin() {
        autenticar(true);
    }

    @Dado("que estou autenticado como usuário comum")
    public void autenticadoComoComum() {
        autenticar(false);
    }

    @Quando("cadastro um novo produto")
    public void cadastroProduto() {
        ctx.resposta = ctx.api.post("/produtos", Produto.aleatorio(), ctx.token);
        if (ctx.resposta.statusCode() == 201) {
            ctx.produtosCriados.add(ctx.resposta.path("_id"));
        }
    }

    @Quando("cadastro um novo produto sem token")
    public void cadastroProdutoSemToken() {
        ctx.resposta = ctx.api.post("/produtos", Produto.aleatorio());
    }

    private void autenticar(boolean administrador) {
        ctx.usuario = usuarios.cadastrar(Usuario.aleatorio(administrador));
        ctx.token = login(ctx.usuario.getEmail(), ctx.usuario.getPassword()).path("authorization");
    }

    private Response login(String email, String senha) {
        return ctx.api.post("/login", Map.of("email", email, "password", senha));
    }
}
