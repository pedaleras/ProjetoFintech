package br.com.fiap.julio.dao;

import br.com.fiap.julio.dao.abstracts.GenericDao;
import br.com.fiap.julio.model.TipoInvestimento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TipoInvestimentoDao extends GenericDao<TipoInvestimento> {

    public TipoInvestimentoDao() throws SQLException {
        super();
    }

    // Implementação do mapeamento do ResultSet para a entidade TipoInvestimento
    @Override
    protected TipoInvestimento mapResultSetToEntity(ResultSet rs) throws SQLException {
        TipoInvestimento tipoInvestimento = new TipoInvestimento();
        tipoInvestimento.setId(rs.getInt("id"));
        tipoInvestimento.setDescricao(rs.getString("descricao"));
        return tipoInvestimento;
    }

    // Cadastrar novo TipoInvestimento
    public void cadastrar(TipoInvestimento tipoInvestimento) throws SQLException {
        String sql = "INSERT INTO tipoinvestimento (descricao) VALUES (?)";
        super.cadastrar(sql, tipoInvestimento.getDescricao());
    }

    // Buscar TipoInvestimento por ID
    public TipoInvestimento buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, descricao FROM tipoinvestimento WHERE id = ?";
        return super.buscarPorId(sql, id);
    }

    // Listar todos os TipoInvestimento
    public List<TipoInvestimento> listarTodos() throws SQLException {
        String sql = "SELECT id, descricao FROM tipoinvestimento ORDER BY descricao";
        return super.listarTodos(sql);
    }

    // Atualizar TipoInvestimento
    public void atualizar(TipoInvestimento tipoInvestimento) throws SQLException {
        String sql = "UPDATE tipoinvestimento SET descricao = ? WHERE id = ?";
        super.atualizar(sql, tipoInvestimento.getDescricao(), tipoInvestimento.getId());
    }

    // Deletar TipoInvestimento
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM tipoinvestimento WHERE id = ?";
        super.deletar(sql, id);
    }
}
