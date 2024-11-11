package br.com.fiap.julio.dao;

import br.com.fiap.julio.dao.abstracts.GenericDao;
import br.com.fiap.julio.model.Recebimentos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecebimentosDao extends GenericDao<Recebimentos> {

    public RecebimentosDao() throws SQLException {
        super();
    }

    @Override
    protected Recebimentos mapResultSetToEntity(ResultSet rs) throws SQLException {
        Recebimentos recebimentos = new Recebimentos();
        recebimentos.setId(rs.getInt("id"));
        recebimentos.setValor(rs.getDouble("valor"));
        recebimentos.setData(rs.getDate("data").toLocalDate());
        recebimentos.setUsuario(new UsuarioDao().buscarPorId(rs.getInt("usuario_id")));
        recebimentos.setTipoRenda(new TipoRendaDao().buscarPorId(rs.getInt("tiporenda_id")));
        return recebimentos;
    }

    public void cadastrar(Recebimentos recebimentos) throws SQLException {
        String sql = "INSERT INTO recebimentos (valor, data, usuario_id, tiporenda_id) VALUES (?, ?, ?, ?)";
        super.cadastrar(sql, recebimentos.getValor(), recebimentos.getData(), recebimentos.getUsuario().getId(), recebimentos.getTipoRenda().getId());
    }

    public Recebimentos buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM recebimentos WHERE id = ?";
        return super.buscarPorId(sql, id);
    }

    public List<Recebimentos> listarTodos() throws SQLException {
        String sql = "SELECT * FROM recebimentos ORDER BY data DESC";
        return super.listarTodos(sql);
    }

    // Novo método para obter valores de recebimentos agrupados por mês
    public Map<String, List<Double>> obterRecebimentosPorMes() throws SQLException {
        String sql = "SELECT TO_CHAR(data, 'MM-YYYY') AS mes, SUM(valor) AS total_valor FROM recebimentos GROUP BY TO_CHAR(data, 'MM-YYYY') ORDER BY mes";
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

    public void atualizar(Recebimentos recebimentos) throws SQLException {
        String sql = "UPDATE recebimentos SET valor = ?, data = ?, usuario_id = ?, tiporenda_id = ? WHERE id = ?";
        super.atualizar(sql, recebimentos.getValor(), recebimentos.getData(), recebimentos.getUsuario().getId(), recebimentos.getTipoRenda().getId(), recebimentos.getId());
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM recebimentos WHERE id = ?";
        super.deletar(sql, id);
    }
}
