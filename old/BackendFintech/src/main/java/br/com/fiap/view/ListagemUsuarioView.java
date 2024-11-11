package br.com.fiap.view;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.model.Usuario;

import java.sql.SQLException;
import java.util.List;

/// teste de select de usuario
public class ListagemUsuarioView {

    public static void main(String[] args) {
        try {
            UsuarioDao dao = new UsuarioDao();
            List<Usuario> lista = dao.listarTodos();
            // Cabeçalho da tabela
            System.out.println("-----------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-25s | %-12s |%n", "ID", "Nome", "Email", "Telefone");
            System.out.println("-----------------------------------------------------------------------------");

            // Dados dos usuários
            for (Usuario usuario : lista) {
                System.out.printf("| %-5d | %-20s | %-25s | %-12s |%n",
                        usuario.getId(),
                        usuario.getNomeUsuario(),
                        usuario.getEmail(),
                        usuario.getTelefone().getNumeroCompleto());
            }

            // Rodapé da tabela
            System.out.println("-----------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
