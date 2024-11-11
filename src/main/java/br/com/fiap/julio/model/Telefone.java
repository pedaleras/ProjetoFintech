package br.com.fiap.julio.model;

public class Telefone {
    private int ddd; // Atributo DDD
    private String numero; // Atributo número

    // Construtor padrão
    public Telefone() {}

    // Construtor com parâmetros
    public Telefone(int ddd, String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }

    // Métodos getters e setters
    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    // Método para obter o número completo
    public String getNumeroCompleto() {
        return String.format("(%d) %s", ddd, numero);
    }
}
