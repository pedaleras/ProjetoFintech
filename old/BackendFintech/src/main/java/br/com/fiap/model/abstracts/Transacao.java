package br.com.fiap.model.abstracts;

import br.com.fiap.model.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Transacao {
    private int id;
    private double valor;
    private Date data;
    private Usuario usuario;

    public Transacao() {

    }

    public Transacao(double valor, Date data, Usuario usuario) {
        this.valor = valor;
        this.data = data;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor= valor;
    }

    public Date getData() {
        return data;
    }

    public String getDataString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(data);
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public abstract String getMensagemTransacao();
}

