package br.com.fiap.julio.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.julio.dao.abstracts.GenericDao;
import br.com.fiap.julio.model.Telefone;
import br.com.fiap.julio.model.Usuario;

public class UsuarioDao extends GenericDao<Usuario> {

    public UsuarioDao() throws SQLException {
        super();
    }

    @Override
    protected Usuario mapResultSetToEntity(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setNomeUsuario(rs.getString("nomeusuario"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setEmail(rs.getString("email"));

        // Mapeia o Telefone na classe
        String telefoneCompleto = rs.getString("telefone");
        if (telefoneCompleto != null) {
            telefoneCompleto = telefoneCompleto.replaceAll("[()\\s]", "");
            String ddd = telefoneCompleto.substring(0, 2);  // "12"
            String numero = telefoneCompleto.substring(2);   // "934567890"
            Telefone telefone = new Telefone(Integer.parseInt(ddd), numero);
            usuario.setTelefone(telefone);
        }

        return usuario;
    }

    public void cadastrar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nomeusuario, senha, email, telefone) VALUES (?, ?, ?, ?)";
        super.cadastrar(sql, usuario.getNomeUsuario(), usuario.getSenha(), usuario.getEmail(), usuario.getTelefone().getNumeroCompleto());
    }

    public Usuario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        return super.buscarPorId(sql, id);
    }

    public Usuario autenticarUsuario(String nomeUsuario, String senha) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE nomeusuario = ? AND senha = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeUsuario);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToEntity(rs); // Mapeia o usuário encontrado no banco
            }
        }
        return null; // Retorna null se não encontrar o usuário
    }

    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nomeusuario = ?, senha = ?, email = ?, telefone = ? WHERE id = ?";
        super.atualizar(sql, usuario.getNomeUsuario(), usuario.getSenha(), usuario.getEmail(), usuario.getTelefone().getNumeroCompleto(), usuario.getId());
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        super.deletar(sql, id);
    }
}
