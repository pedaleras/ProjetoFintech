package br.com.fiap.dao;

import br.com.fiap.dao.abstracts.GenericDao;
import br.com.fiap.model.Recebimentos;
import br.com.fiap.model.TipoRenda;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RecebimentosDao extends GenericDao<Recebimentos> {

    public RecebimentosDao() throws SQLException {
        super();
    }

    @Override
    protected Recebimentos mapResultSetToEntity(ResultSet rs) throws SQLException {
        Recebimentos recebimentos = new Recebimentos();
        recebimentos.setId(rs.getInt("id"));
        recebimentos.setValor(rs.getDouble("valor"));
        recebimentos.setData(rs.getDate("data"));
        recebimentos.setUsuario(new UsuarioDao().buscarPorId(rs.getInt("usuario_id")));
        recebimentos.setTipoRenda(new TipoRendaDao().buscarPorId(rs.getInt("tipo_renda_id")));
        return recebimentos;
    }

    public void cadastrar(Recebimentos recebimentos) throws SQLException {
        String sql = "INSERT INTO recebimentos (valor, data, usuario_id, tipo_renda_id) VALUES (?, ?, ?, ?)";
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

    public void atualizar(Recebimentos recebimentos) throws SQLException {
        String sql = "UPDATE recebimentos SET valor = ?, data = ?, usuario_id = ?, tipo_renda_id = ? WHERE id = ?";
        super.atualizar(sql, recebimentos.getValor(), recebimentos.getData(), recebimentos.getUsuario().getId(), recebimentos.getTipoRenda().getId(), recebimentos.getId());
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM recebimentos WHERE id = ?";
        super.deletar(sql, id);
    }
}
