package br.com.fiap.julio.controller.Recebimentos;

import br.com.fiap.julio.dao.RecebimentosDao;
import br.com.fiap.julio.dao.TipoRendaDao;
import br.com.fiap.julio.model.Recebimentos;
import br.com.fiap.julio.model.TipoRenda;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listarRecebimentos")
public class ListarRecebimentosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dataInicio = request.getParameter("dataInicio");
        String dataFim = request.getParameter("dataFim");

        List<Recebimentos> listaRecebimentos = null;
        List<TipoRenda> listaTipoRenda = null;
        try {
            RecebimentosDao recebimentosDao = new RecebimentosDao();
            TipoRendaDao tipoRendaDao = new TipoRendaDao();

            // Carregar lista de recebimentos
            if (dataInicio != null && dataFim != null && !dataInicio.isEmpty() && !dataFim.isEmpty()) {
                listaRecebimentos = recebimentosDao.listarTodos(dataInicio, dataFim);
            } else {
                listaRecebimentos = recebimentosDao.listarTodos();
            }

            // Carregar lista de Tipos de Renda
            listaTipoRenda = tipoRendaDao.listarTodos();
        } catch (SQLException e) {
            throw new ServletException("Erro ao listar recebimentos ou carregar tipos de renda", e);
        }

        request.setAttribute("listaRecebimentos", listaRecebimentos);
        request.setAttribute("listaTipoRenda", listaTipoRenda);
        request.getRequestDispatcher("recebimentos.jsp").forward(request, response);
    }
}
