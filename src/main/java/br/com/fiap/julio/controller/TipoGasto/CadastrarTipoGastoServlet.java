package br.com.fiap.julio.controller.TipoGasto;

import br.com.fiap.julio.service.TipoGastoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cadastrarTipoGasto")
public class CadastrarTipoGastoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TipoGastoService service = new TipoGastoService();
        boolean sucesso = service.salvarTipoGasto(request, false);

        if (sucesso) {
            response.sendRedirect("listarTipoGasto");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar tipo de gasto.");
        }
    }
}
