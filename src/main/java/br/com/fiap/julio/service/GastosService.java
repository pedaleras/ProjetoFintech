package br.com.fiap.julio.service;

import br.com.fiap.julio.dao.GastosDao;
import br.com.fiap.julio.model.Gastos;
import br.com.fiap.julio.model.TipoGasto;
import br.com.fiap.julio.model.TipoRenda;
import br.com.fiap.julio.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class GastosService {

    public boolean salvarGastos(HttpServletRequest request) {
        try {
            Gastos Gastos = parseGastosFromRequest(request);

            GastosDao dao = new GastosDao();
            if (Gastos.getId() != 0) {
                dao.atualizar(Gastos);
            } else {
                dao.cadastrar(Gastos);
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

    private Gastos parseGastosFromRequest(HttpServletRequest request) {
        Gastos gastos = new Gastos();
        String idParam = request.getParameter("id");
        String valorParam = request.getParameter("valor");
        String dataParam = request.getParameter("data");
        String tipoGastoIdParam = request.getParameter("tipoGastoId");

        gastos.setId(idParam != null && !idParam.isEmpty() ? Integer.parseInt(idParam) : 0);
        gastos.setValor(valorParam != null && !valorParam.isEmpty() ? Double.parseDouble(valorParam) : 0.0);
        gastos.setData(dataParam != null && !dataParam.isEmpty() ? LocalDate.parse(dataParam) : null);

        TipoGasto tipoGasto = new TipoGasto();
        tipoGasto.setId(tipoGastoIdParam != null && !tipoGastoIdParam.isEmpty() ? Integer.parseInt(tipoGastoIdParam) : 0);
        gastos.setTipoGasto(tipoGasto);

        Usuario usuario = new Usuario();
        usuario.setId(2);
        gastos.setUsuario(usuario);

        return gastos;
    }
}