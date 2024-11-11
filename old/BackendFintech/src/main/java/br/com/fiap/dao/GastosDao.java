package br.com.fiap.dao;

import br.com.fiap.dao.abstracts.GenericDao;
import br.com.fiap.model.Gastos;
import br.com.fiap.model.TipoGasto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GastosDao extends GenericDao<Gastos> {

    public GastosDao() throws SQLException {
        super();
    }

    @Override
    protected Gastos mapResultSetToEntity(ResultSet rs) throws SQLException {
        Gastos gastos = new Gastos();
        gastos.setId(rs.getInt("id"));
        gastos.setValor(rs.getDouble("valor"));
        gastos.setData(rs.getDate("data"));
        gastos.setUsuario(new UsuarioDao().buscarPorId(rs.getInt("usuario_id")));
        gastos.setTipoGasto(new TipoGastoDao().buscarPorId(rs.getInt("tipo_gasto_id")));
        return gastos;
    }

    public void cadastrar(Gastos gastos) throws SQLException {
        String sql = "INSERT INTO gastos (valor, data, usuario_id, tipo_gasto_id) VALUES (?, ?, ?, ?)";
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

    public void atualizar(Gastos gastos) throws SQLException {
        String sql = "UPDATE gastos SET valor = ?, data = ?, usuario_id = ?, tipo_gasto_id = ? WHERE id = ?";
        super.atualizar(sql, gastos.getValor(), gastos.getData(), gastos.getUsuario().getId(), gastos.getTipoGasto().getId(), gastos.getId());
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM gastos WHERE id = ?";
        super.deletar(sql, id);
    }
}
