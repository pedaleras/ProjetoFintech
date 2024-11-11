package br.com.fiap.julio.controller.Gastos;

import br.com.fiap.julio.dao.GastosDao;
import br.com.fiap.julio.dao.TipoGastoDao;
import br.com.fiap.julio.model.Gastos;
import br.com.fiap.julio.model.TipoGasto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listarGastos")
public class ListarGastosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dataInicio = request.getParameter("dataInicio");
        String dataFim = request.getParameter("dataFim");

        List<Gastos> listaGastos = null;
        List<TipoGasto> listaTipoGastos = null;
        try {
            GastosDao gastosDao = new GastosDao();
            TipoGastoDao tipoGastoDao = new TipoGastoDao();

            // Carregar lista de gastos
            if (dataInicio != null && dataFim != null && !dataInicio.isEmpty() && !dataFim.isEmpty()) {
                listaGastos = gastosDao.listarTodos(dataInicio, dataFim);
            } else {
                listaGastos = gastosDao.listarTodos();
            }

            // Carregar lista de Tipos de Gasto
            listaTipoGastos = tipoGastoDao.listarTodos();
        } catch (SQLException e) {
            throw new ServletException("Erro ao listar gastos ou carregar tipos de gasto", e);
        }

        request.setAttribute("listaGastos", listaGastos);
        request.setAttribute("listaTipoGastos", listaTipoGastos);
        request.getRequestDispatcher("gastos.jsp").forward(request, response);
    }
}
