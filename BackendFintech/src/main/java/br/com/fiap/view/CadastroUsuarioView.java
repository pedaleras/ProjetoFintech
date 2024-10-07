package br.com.fiap.view;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.model.Telefone;
import br.com.fiap.model.Usuario;

import java.sql.SQLException;

/// teste de insert de usuario
public class CadastroUsuarioView {
    public static void main(String[] args) {
        try {
            Telefone telefone = new Telefone(12, "934567890");

            for (int i = 1; i <= 5; i++) {
                UsuarioDao dao = new UsuarioDao();

                String nomeUsuario = "teste"+i;
                Usuario usuario = new Usuario(nomeUsuario,
                        "teste",
                        nomeUsuario+"@teste.com",
                        telefone);

                dao.cadastrar(usuario);
                System.out.println("Usuário "+nomeUsuario+" cadastrado!");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}