package br.com.fiap.dao;

import br.com.fiap.dao.abstracts.GenericDao;
import br.com.fiap.model.Investimentos;
import br.com.fiap.model.TipoInvestimento;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InvestimentosDao extends GenericDao<Investimentos> {

    public InvestimentosDao() throws SQLException {
        super();
    }

    @Override
    protected Investimentos mapResultSetToEntity(ResultSet rs) throws SQLException {
        Investimentos investimentos = new Investimentos();
        investimentos.setId(rs.getInt("id"));
        investimentos.setValor(rs.getDouble("valor"));
        investimentos.setData(rs.getDate("data"));
        investimentos.setUsuario(new UsuarioDao().buscarPorId(rs.getInt("usuario_id")));
        investimentos.setTipoInvestimento(new TipoInvestimentoDao().buscarPorId(rs.getInt("tipo_investimento_id")));
        return investimentos;
    }

    public void cadastrar(Investimentos investimentos) throws SQLException {
        String sql = "INSERT INTO investimentos (valor, data, usuario_id, tipo_investimento_id) VALUES (?, ?, ?, ?)";
        super.cadastrar(sql, investimentos.getValor(), investimentos.getData(), investimentos.getUsuario().getId(), investimentos.getTipoInvestimento().getId());
    }

    public Investimentos buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM investimentos WHERE id = ?";
        return super.buscarPorId(sql, id);
    }

    public List<Investimentos> listarTodos() throws SQLException {
        String sql = "SELECT * FROM investimentos ORDER BY data DESC";
        return super.listarTodos(sql);
    }

    public void atualizar(Investimentos investimentos) throws SQLException {
        String sql = "UPDATE investimentos SET valor = ?, data = ?, usuario_id = ?, tipo_investimento_id = ? WHERE id = ?";
        super.atualizar(sql, investimentos.getValor(), investimentos.getData(), investimentos.getUsuario().getId(), investimentos.getTipoInvestimento().getId(), investimentos.getId());
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM investimentos WHERE id = ?";
        super.deletar(sql, id);
    }
}
