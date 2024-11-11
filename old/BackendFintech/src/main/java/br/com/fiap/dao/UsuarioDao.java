package br.com.fiap.dao;

import br.com.fiap.dao.abstracts.GenericDao;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Telefone;
import br.com.fiap.model.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

        // mapeia o Telefone na classe
        String telefoneCompleto = rs.getString("telefone");
        telefoneCompleto = telefoneCompleto.replaceAll("[()\\s]", "");
        String ddd = telefoneCompleto.substring(0, 2);  // "12"
        String numero = telefoneCompleto.substring(2);   // "934567890"
        Telefone telefone = new Telefone(Integer.parseInt(ddd), numero);
        usuario.setTelefone(telefone);

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

    public List<Usuario> listarTodos() throws SQLException {
        String sql = "SELECT * FROM usuario ORDER BY nomeusuario";
        return super.listarTodos(sql);
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
