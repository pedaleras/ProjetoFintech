package br.com.fiap.julio.service;

import br.com.fiap.julio.dao.UsuarioDao;
import br.com.fiap.julio.model.Usuario;

import java.sql.SQLException;

public class UsuarioService {
    private UsuarioDao usuarioDao;

    public UsuarioService() throws SQLException {
        this.usuarioDao = new UsuarioDao();
    }

    public Usuario autenticarUsuario(String nomeUsuario, String senha) throws SQLException {
        return usuarioDao.autenticarUsuario(nomeUsuario, senha);
    }
}
