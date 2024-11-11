package br.com.fiap.julio.service;

import br.com.fiap.julio.dao.RecebimentosDao;
import br.com.fiap.julio.model.Recebimentos;
import br.com.fiap.julio.model.TipoRenda;
import br.com.fiap.julio.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class RecebimentoService {

    public boolean salvarRecebimento(HttpServletRequest request) {
        try {
            Recebimentos recebimento = parseRecebimentoFromRequest(request);

            RecebimentosDao dao = new RecebimentosDao();
            if (recebimento.getId() != 0) {
                dao.atualizar(recebimento);
            } else {
                dao.cadastrar(recebimento);
            }
            return true;
        } catch (SQLException e) {
            // Log the exception (use a proper logging framework)
            e.printStackTrace();
            return false;
        } catch (NumberFormatException | DateTimeParseException e) {
            // Log input parsing errors and return failure
            e.printStackTrace();
            return false;
        }
    }

    private Recebimentos parseRecebimentoFromRequest(HttpServletRequest request) {
        Recebimentos recebimento = new Recebimentos();
        String idParam = request.getParameter("id");
        String valorParam = request.getParameter("valor");
        String dataParam = request.getParameter("data");
        String tipoRendaIdParam = request.getParameter("tipoRendaId");

        recebimento.setId(idParam != null && !idParam.isEmpty() ? Integer.parseInt(idParam) : 0);
        recebimento.setValor(valorParam != null && !valorParam.isEmpty() ? Double.parseDouble(valorParam) : 0.0);
        recebimento.setData(dataParam != null && !dataParam.isEmpty() ? LocalDate.parse(dataParam) : null);

        TipoRenda tipoRenda = new TipoRenda();
        tipoRenda.setId(tipoRendaIdParam != null && !tipoRendaIdParam.isEmpty() ? Integer.parseInt(tipoRendaIdParam) : 0);
        recebimento.setTipoRenda(tipoRenda);


        Usuario usuario = new Usuario();
        usuario.setId(2);
        recebimento.setUsuario(usuario);

        return recebimento;
    }
}