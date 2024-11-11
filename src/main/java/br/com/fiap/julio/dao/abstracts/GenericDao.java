package br.com.fiap.julio.dao.abstracts;

import br.com.fiap.julio.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDao<T> {

    protected Connection conexao;

    public GenericDao() throws SQLException {
        this.conexao = ConnectionFactory.getConnection();
    }

    // Método para mapear o ResultSet em um objeto T (deve ser implementado na subclasse)
    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;

    // Método genérico para cadastrar (Create)
    public void cadastrar(String sql, Object... parametros) throws SQLException {
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            setStatementParameters(stmt, parametros);
            stmt.executeUpdate();
        } finally {
            fecharConexao(); // Fechar a conexão após o processamento da operação
        }
    }

    // Método genérico para buscar por ID (Read)
    public T buscarPorId(String sql, int id) throws SQLException {
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEntity(rs);
                }
            }
        } finally {
            fecharConexao(); // Fechar a conexão após o processamento da operação
        }
        return null;
    }

    // Método genérico para listar todos (Read)
    public List<T> listarTodos(String sql, Object... parametros) throws SQLException {
        List<T> lista = new ArrayList<>();
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            setStatementParameters(stmt, parametros);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapResultSetToEntity(rs)); // Mapeia os resultados para a entidade
                }
            }
        } finally {
            fecharConexao(); // Fechar a conexão após o processamento da operação
        }
        return lista;
    }

    // Método genérico para atualizar (Update)
    public void atualizar(String sql, Object... parametros) throws SQLException {
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            setStatementParameters(stmt, parametros);
            stmt.executeUpdate();
        } finally {
            fecharConexao(); // Fechar a conexão após o processamento da operação
        }
    }

    // Método genérico para deletar (Delete)
    public void deletar(String sql, int id) throws SQLException {
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } finally {
            fecharConexao(); // Fechar a conexão após o processamento da operação
        }
    }

    // Método auxiliar para definir os parâmetros do PreparedStatement
    private void setStatementParameters(PreparedStatement stmt, Object... parametros) throws SQLException {
        for (int i = 0; i < parametros.length; i++) {
            stmt.setObject(i + 1, parametros[i]);
        }
    }

    // Fechar a conexão após o uso
    public void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
