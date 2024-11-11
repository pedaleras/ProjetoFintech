package br.com.fiap.julio.model.abstracts;

import br.com.fiap.julio.model.Usuario;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public abstract class Transacao {
    private int id;
    private double valor;
    private LocalDate data;
    private Usuario usuario;

    public Transacao() {

    }

    public Transacao(double valor, LocalDate data, Usuario usuario) {
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

    public LocalDate getData() {
        return data;
    }

    public String getDataString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(data);
    }

    public void setData(LocalDate data) {
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

