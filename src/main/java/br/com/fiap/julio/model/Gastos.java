package br.com.fiap.julio.model;


import br.com.fiap.julio.model.abstracts.Transacao;

import java.time.LocalDate;
import java.util.Date;

public class Gastos extends Transacao {
    private TipoGasto tipoGasto;


    public Gastos() {
        super();
    }

    @Override
    public String getMensagemTransacao() {
        return "O usuário " + getUsuario().getNomeUsuario() +
                " gastou R$ " + getValor() +
                " no dia " + getDataString() +
                " em " + getTipoGasto().getDescricao();
    }

    public Gastos (double valor, LocalDate data, Usuario usuario, TipoGasto tipoGasto){
        super(valor,data,usuario);
        this.tipoGasto = tipoGasto;
    }

    public TipoGasto getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(TipoGasto tipoGasto) {
        this.tipoGasto = tipoGasto;
    }
}
