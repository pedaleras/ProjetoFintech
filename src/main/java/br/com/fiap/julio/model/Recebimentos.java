package br.com.fiap.julio.model;

import br.com.fiap.julio.model.abstracts.Transacao;

import java.time.LocalDate;

public class Recebimentos extends Transacao {
    private TipoRenda tipoRenda;

    public Recebimentos() {
        super();
    }

    @Override
    public String getMensagemTransacao() {
        return "O usuário " + getUsuario().getNomeUsuario() +
                " recebeu R$ " + getValor() +
                " no dia " + getDataString() +
                " de " + getTipoRenda().getDescricao();
    }

    public Recebimentos (double valor, LocalDate data, Usuario usuario, TipoRenda tipoRenda){
        super(valor,data,usuario);
        this.tipoRenda = tipoRenda;
    }

    public TipoRenda getTipoRenda() {
        return tipoRenda;
    }

    public void setTipoRenda(TipoRenda tipoRenda) {
        this.tipoRenda = tipoRenda;
    }


}

