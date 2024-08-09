//semelhante ao Login()
public class Usuario {

    private int id;
    private String nomeUsuario;
    private String senha;
    private String email;
    private Telefone telefone;

    // Construtor padrão
    public Usuario() {

    }

    // Construtor com parâmetros obrigatórios
    public Usuario(String nomeUsuario, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha= senha;
    }


    public void fazerLogin() {
        // Lógica para realizar o login
        System.out.println("Realizando login para o usuário: " + nomeUsuario);
    }

}