package model;

public class Telefone {
    public int DDD;
    public String numero;

    public String numeroCompleto = "("+DDD+") "+numero;

    public Telefone(){}
    public Telefone(int ddd, String numero){
        this.DDD = ddd;
        this.numero = numero;
    }

}
