package br.com.joice.api.support;

import br.com.joice.api.model.Usuario;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Estado compartilhado entre os steps de um mesmo cenário (injetado pelo PicoContainer).
 */
public class TestContext {

    public final ApiClient api = new ApiClient();

    public Usuario usuario;
    public Response resposta;
    public String token;

    /** IDs criados durante o cenário, removidos no @After para manter os testes independentes. */
    public final List<String> usuariosCriados = new ArrayList<>();
    public final List<String> produtosCriados = new ArrayList<>();
}
