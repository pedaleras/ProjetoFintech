package br.com.fiap.julio.controller.TipoRenda;

import br.com.fiap.julio.service.TipoRendaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cadastrarTipoRenda")
public class CadastrarTipoRendaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TipoRendaService service = new TipoRendaService();
        boolean sucesso = service.salvarTipoRenda(request);

        if (sucesso) {
            response.sendRedirect("listarTipoRenda");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao salvar tipo de renda.");
        }
    }
}
