package br.com.fiap.julio.service;

import br.com.fiap.julio.dao.TipoGastoDao;
import br.com.fiap.julio.model.TipoGasto;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;

public class TipoGastoService {

    public boolean salvarTipoGasto(HttpServletRequest request, boolean isUpdate) {
        try {
            int id = 0;
            if (isUpdate) {
                id = Integer.parseInt(request.getParameter("id"));
            }

            String descricao = request.getParameter("descricao");
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new IllegalArgumentException("Descrição não pode ser nula ou vazia.");
            }

            TipoGasto tipoGasto = new TipoGasto(descricao);
            tipoGasto.setId(id);

            TipoGastoDao dao = new TipoGastoDao();
            if (isUpdate) {
                dao.atualizar(tipoGasto);
            } else {
                dao.cadastrar(tipoGasto);
            }

            return true;
        } catch (SQLException e) {
            // Log the error properly (use a logging framework)
            e.printStackTrace();
            return false;
        } catch (IllegalArgumentException e) {
            // Handle input validation errors
            e.printStackTrace();
            return false;
        }
    }
}
