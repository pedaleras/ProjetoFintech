package br.com.fiap.julio.dao;

import br.com.fiap.julio.dao.abstracts.GenericDao;
import br.com.fiap.julio.model.Gastos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GastosDao extends GenericDao<Gastos> {

    public GastosDao() throws SQLException {
        super();
    }

    @Override
    protected Gastos mapResultSetToEntity(ResultSet rs) throws SQLException {
        Gastos gastos = new Gastos();
        gastos.setId(rs.getInt("id"));
        gastos.setValor(rs.getDouble("valor"));
        gastos.setData(rs.getDate("data").toLocalDate());
        gastos.setUsuario(new UsuarioDao().buscarPorId(rs.getInt("usuario_id")));
        gastos.setTipoGasto(new TipoGastoDao().buscarPorId(rs.getInt("tipogasto_id")));
        return gastos;
    }

    public void cadastrar(Gastos gastos) throws SQLException {
        String sql = "INSERT INTO gastos (valor, data, usuario_id, tipogasto_id) VALUES (?, ?, ?, ?)";
        super.cadastrar(sql, gastos.getValor(), gastos.getData(), gastos.getUsuario().getId(), gastos.getTipoGasto().getId());
    }

    public Gastos buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM gastos WHERE id = ?";
        return super.buscarPorId(sql, id);
    }

    public List<Gastos> listarTodos() throws SQLException {
        String sql = "SELECT * FROM gastos ORDER BY data DESC";
        return super.listarTodos(sql);
    }

    // Novo método para obter valores de gastos agrupados por mês
    public Map<String, List<Double>> obterGastosPorMes() throws SQLException {
        String sql = "SELECT TO_CHAR(data, 'MM-YYYY') AS mes, SUM(valor) AS total_valor FROM gastos GROUP BY TO_CHAR(data, 'MM-YYYY') ORDER BY mes";
        Map<String, List<Double>> resultado = new HashMap<>();
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String mes = rs.getString("mes");
                double totalValor = rs.getDouble("total_valor");
                resultado.computeIfAbsent(mes, k -> new ArrayList<>()).add(totalValor);
            }
        }
        finally {
            super.fecharConexao();
        }

        return resultado;
    }

    // Método para obter distribuição dos tipos de gastos
    public Map<String, Double> obterDistribuicaoPorTipo() throws SQLException {
        String sql = "SELECT max(t.descricao) as descricao, sum(g.valor) AS total FROM gastos g \n" +
                "join tipogasto t on g.tipogasto_id = t.id \n" +
                "GROUP BY g.tipogasto_id";
        Map<String, Double> resultado = new HashMap<>();
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String tipoGasto = rs.getString("descricao");
                Double total = rs.getDouble("total");
                resultado.put(tipoGasto, total);
            }
        }
        finally {
            super.fecharConexao();
        }
        return resultado;
    }

    public void atualizar(Gastos gastos) throws SQLException {
        String sql = "UPDATE gastos SET valor = ?, data = ?, usuario_id = ?, tipogasto_id = ? WHERE id = ?";
        super.atualizar(sql, gastos.getValor(), gastos.getData(), gastos.getUsuario().getId(), gastos.getTipoGasto().getId(), gastos.getId());
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM gastos WHERE id = ?";
        super.deletar(sql, id);
    }
}
