package br.com.fiap.julio.controller.TipoRenda;

import br.com.fiap.julio.dao.TipoRendaDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deletarTipoRenda")
public class DeletarTipoRendaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        // Exclui o objeto do banco de dados usando o DAO
        TipoRendaDao dao = null;
        try {
            dao = new TipoRendaDao();
            dao.deletar(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Redireciona para a página de listagem (ou conforme necessário)
        response.sendRedirect("listarTipoRenda");
    }
}
