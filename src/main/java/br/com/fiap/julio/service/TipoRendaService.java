package br.com.fiap.julio.service;

import br.com.fiap.julio.dao.TipoRendaDao;
import br.com.fiap.julio.model.TipoRenda;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;

public class TipoRendaService {

    public boolean salvarTipoRenda(HttpServletRequest request) {
        try {
            String idParam = request.getParameter("id");
            int id = (idParam != null && !idParam.trim().isEmpty()) ? Integer.parseInt(idParam) : 0;

            String descricao = request.getParameter("descricao");
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new IllegalArgumentException("Descrição não pode ser nula ou vazia.");
            }

            TipoRenda tipoRenda = new TipoRenda(descricao);
            tipoRenda.setId(id);

            TipoRendaDao dao = new TipoRendaDao();
            if (id != 0) {
                dao.atualizar(tipoRenda);
            } else {
                dao.cadastrar(tipoRenda);
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace(); // Substitua por um logger adequado
            return false;
        } catch (IllegalArgumentException e) {
            e.printStackTrace(); // Substitua por um logger adequado
            return false;
        }
    }
}
