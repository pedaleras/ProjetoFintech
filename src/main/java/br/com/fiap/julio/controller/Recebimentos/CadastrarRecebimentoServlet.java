package br.com.fiap.julio.controller.Recebimentos;

import br.com.fiap.julio.service.RecebimentoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cadastrarRecebimento")
public class CadastrarRecebimentoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecebimentoService serv = new RecebimentoService();
        boolean sucesso = serv.salvarRecebimento(request);

        if (sucesso) {
            response.sendRedirect("listarRecebimentos");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar recebimento.");
        }
    }
}
