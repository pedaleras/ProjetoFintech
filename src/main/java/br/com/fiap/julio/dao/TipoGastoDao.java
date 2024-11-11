package br.com.fiap.julio.dao;

import br.com.fiap.julio.dao.abstracts.GenericDao;
import br.com.fiap.julio.model.TipoGasto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TipoGastoDao extends GenericDao<TipoGasto> {

    public TipoGastoDao() throws SQLException {
        super();
    }

    @Override
    protected TipoGasto mapResultSetToEntity(ResultSet rs) throws SQLException {
        TipoGasto tipoGasto = new TipoGasto();
        tipoGasto.setId(rs.getInt("id"));
        tipoGasto.setDescricao(rs.getString("descricao"));
        return tipoGasto;
    }

    public void cadastrar(TipoGasto tipoGasto) throws SQLException {
        String sql = "INSERT INTO tipogasto (descricao) VALUES (?)";
        super.cadastrar(sql, tipoGasto.getDescricao());
    }

    public TipoGasto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tipogasto WHERE id = ?";
        return super.buscarPorId(sql, id);
    }

    public List<TipoGasto> listarTodos() throws SQLException {
        String sql = "SELECT * FROM tipogasto ORDER BY descricao";
        return super.listarTodos(sql);
    }

    public void atualizar(TipoGasto tipoGasto) throws SQLException {
        String sql = "UPDATE tipogasto SET descricao = ? WHERE id = ?";
        super.atualizar(sql, tipoGasto.getDescricao(), tipoGasto.getId());
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM tipogasto WHERE id = ?";
        super.deletar(sql, id);
    }
}
