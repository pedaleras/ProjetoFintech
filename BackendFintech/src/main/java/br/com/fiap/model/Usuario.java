package br.com.fiap.model;

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

    public Usuario(String nomeUsuario, String senha,String email, Telefone telefone) {
        this.nomeUsuario = nomeUsuario;
        this.senha= senha;
        this.email = email;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public void fazerLogin() {
        // Lógica para realizar o login
        System.out.println("Realizando login para o usuário: " + nomeUsuario);
    }

}