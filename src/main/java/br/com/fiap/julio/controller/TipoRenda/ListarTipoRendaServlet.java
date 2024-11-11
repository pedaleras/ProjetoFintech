package br.com.fiap.julio.controller.TipoRenda;

import br.com.fiap.julio.dao.TipoRendaDao;
import br.com.fiap.julio.model.TipoRenda;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listarTipoRenda")
public class ListarTipoRendaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TipoRendaDao tipoRendaDao = new TipoRendaDao();
            // Obtém a lista de tipos de renda do DAO
            List<TipoRenda> listaTipoRenda = tipoRendaDao.listarTodos();

            // Adiciona a lista no request para que possa ser acessada na JSP
            request.setAttribute("listaTipoRenda", listaTipoRenda);

            // Redireciona para a página JSP onde a lista será exibida
            RequestDispatcher dispatcher = request.getRequestDispatcher("tipoRenda.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao acessar o banco de dados");
        }
    }
}

