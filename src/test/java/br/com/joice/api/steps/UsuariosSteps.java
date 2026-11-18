package br.com.joice.api.steps;

import br.com.joice.api.model.Usuario;
import br.com.joice.api.support.TestContext;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UsuariosSteps {

    private final TestContext ctx;

    public UsuariosSteps(TestContext ctx) {
        this.ctx = ctx;
    }

    @Dado("que tenho os dados de um novo usuário administrador")
    public void dadosDeNovoUsuario() {
        ctx.usuario = Usuario.aleatorio(true);
    }

    @Dado("que existe um usuário cadastrado")
    public void existeUsuarioCadastrado() {
        ctx.usuario = cadastrar(Usuario.aleatorio(true));
    }

    @E("o campo {string} está vazio")
    public void campoVazio(String campo) {
        switch (campo) {
            case "nome": ctx.usuario.setNome(""); break;
            case "email": ctx.usuario.setEmail(""); break;
            case "password": ctx.usuario.setPassword(""); break;
            default: throw new IllegalArgumentException("Campo não mapeado: " + campo);
        }
    }

    @Quando("envio a requisição de cadastro de usuário")
    public void envioCadastro() {
        ctx.resposta = ctx.api.post("/usuarios", ctx.usuario);
        if (ctx.resposta.statusCode() == 201) {
            ctx.usuariosCriados.add(ctx.resposta.path("_id"));
        }
    }

    @Quando("tento cadastrar outro usuário com o mesmo e-mail")
    public void cadastroDuplicado() {
        Usuario duplicado = Usuario.aleatorio(false);
        duplicado.setEmail(ctx.usuario.getEmail());
        ctx.resposta = ctx.api.post("/usuarios", duplicado);
    }

    @Quando("consulto o usuário pelo id")
    public void consultoPorId() {
        ctx.resposta = ctx.api.get("/usuarios/" + ctx.usuario.getId());
    }

    @Quando("excluo o usuário pelo id")
    public void excluoPorId() {
        ctx.resposta = ctx.api.delete("/usuarios/" + ctx.usuario.getId());
        ctx.usuariosCriados.remove(ctx.usuario.getId());
    }

    @E("os dados retornados devem corresponder ao usuário cadastrado")
    public void dadosCorrespondem() {
        assertThat(ctx.resposta.path("nome"), equalTo(ctx.usuario.getNome()));
        assertThat(ctx.resposta.path("email"), equalTo(ctx.usuario.getEmail()));
        assertThat(ctx.resposta.path("administrador"), equalTo(ctx.usuario.getAdministrador()));
    }

    /** Cadastra o usuário via API e registra o id para limpeza no @After. */
    Usuario cadastrar(Usuario usuario) {
        Response r = ctx.api.post("/usuarios", usuario);
        assertThat("Falha ao preparar massa de usuário: " + r.asString(), r.statusCode(), equalTo(201));
        usuario.setId(r.path("_id"));
        ctx.usuariosCriados.add(usuario.getId());
        return usuario;
    }
}
