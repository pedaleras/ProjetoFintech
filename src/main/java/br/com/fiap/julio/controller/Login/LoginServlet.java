package br.com.fiap.julio.controller.Login;

import br.com.fiap.julio.model.Usuario;
import br.com.fiap.julio.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioService usuarioService;

    public LoginServlet() {
        try {
            this.usuarioService = new UsuarioService();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao iniciar o serviço de usuário", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeUsuario = request.getParameter("username");
        String senha = request.getParameter("password");

        try {
            Usuario usuario = usuarioService.autenticarUsuario(nomeUsuario, senha);
            if (usuario != null) {
                request.getSession().setAttribute("usuarioLogado", usuario);
                response.sendRedirect("home"); // Redireciona para a página de sucesso (ajuste conforme necessário)
            } else {
                request.setAttribute("mensagemErro", "Usuário ou senha inválidos.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Erro ao autenticar usuário", e);
        }
    }
}
