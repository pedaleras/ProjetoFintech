package br.com.fiap.julio.controller.Recebimentos;

import br.com.fiap.julio.dao.RecebimentosDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deletarRecebimento")
public class DeletarRecebimentoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            RecebimentosDao dao = new RecebimentosDao();
            dao.deletar(id);

            response.sendRedirect("listarRecebimentos"); // Redireciona para a lista de recebimentos
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir recebimento.");
        }
    }
}
