package br.com.fiap.dao;

import br.com.fiap.dao.abstracts.GenericDao;
import br.com.fiap.model.TipoRenda;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TipoRendaDao extends GenericDao<TipoRenda> {

    public TipoRendaDao() throws SQLException {
        super();
    }

    @Override
    protected TipoRenda mapResultSetToEntity(ResultSet rs) throws SQLException {
        TipoRenda tipoRenda = new TipoRenda();
        tipoRenda.setId(rs.getInt("id"));
        tipoRenda.setDescricao(rs.getString("descricao"));
        return tipoRenda;
    }

    public void cadastrar(TipoRenda tipoRenda) throws SQLException {
        String sql = "INSERT INTO tiporenda (descricao) VALUES (?)";
        super.cadastrar(sql, tipoRenda.getDescricao());
    }

    public TipoRenda buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tiporenda WHERE id = ?";
        return super.buscarPorId(sql, id);
    }

    public List<TipoRenda> listarTodos() throws SQLException {
        String sql = "SELECT * FROM tiporenda ORDER BY descricao";
        return super.listarTodos(sql);
    }

    public void atualizar(TipoRenda tipoRenda) throws SQLException {
        String sql = "UPDATE tiporenda SET descricao = ? WHERE id = ?";
        super.atualizar(sql, tipoRenda.getDescricao(), tipoRenda.getId());
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM tiporenda WHERE id = ?";
        super.deletar(sql, id);
    }
}
