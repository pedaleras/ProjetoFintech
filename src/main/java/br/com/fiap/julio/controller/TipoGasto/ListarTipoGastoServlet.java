package br.com.fiap.julio.controller.TipoGasto;

import br.com.fiap.julio.dao.TipoGastoDao;
import br.com.fiap.julio.model.TipoGasto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listarTipoGasto")
public class ListarTipoGastoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TipoGastoDao dao = new TipoGastoDao();
            List<TipoGasto> listaTipoGasto = dao.listarTodos();
            request.setAttribute("listaTipoGasto", listaTipoGasto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Despacha para a página JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("tipoGasto.jsp");
        dispatcher.forward(request, response);
    }
}
