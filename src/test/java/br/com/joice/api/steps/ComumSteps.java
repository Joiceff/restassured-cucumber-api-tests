package br.com.joice.api.steps;

import br.com.joice.api.support.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ComumSteps {

    private final TestContext ctx;

    public ComumSteps(TestContext ctx) {
        this.ctx = ctx;
    }

    @Então("o status code deve ser {int}")
    public void statusCode(int esperado) {
        assertThat("Corpo da resposta: " + ctx.resposta.asString(),
                ctx.resposta.statusCode(), equalTo(esperado));
    }

    @E("a mensagem deve ser {string}")
    public void mensagem(String esperada) {
        assertThat(ctx.resposta.path("message"), equalTo(esperada));
    }

    @E("o campo {string} deve retornar a mensagem {string}")
    public void mensagemDoCampo(String campo, String esperada) {
        assertThat(ctx.resposta.path(campo), equalTo(esperada));
    }

    @E("a resposta deve respeitar o contrato {string}")
    public void contrato(String schema) {
        ctx.resposta.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/" + schema));
    }

    /** Remove a massa de dados criada no cenário. */
    @After
    public void limparMassa() {
        if (ctx.token != null) {
            ctx.produtosCriados.forEach(id -> ctx.api.delete("/produtos/" + id, ctx.token));
        }
        ctx.usuariosCriados.forEach(id -> ctx.api.delete("/usuarios/" + id));
    }
}
