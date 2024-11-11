package br.com.fiap.julio.controller.Gastos;

import br.com.fiap.julio.service.GastosService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cadastrarGastos")
public class CadastrarGastosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GastosService serv = new GastosService();
        boolean sucesso = serv.salvarGastos(request);

        if (sucesso) {
            response.sendRedirect("listarGastos");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar Gastos.");
        }
    }
}
