package br.com.fiap.model;

import br.com.fiap.model.abstracts.Transacao;

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

    public Gastos (double valor, Date data, Usuario usuario, TipoGasto tipoGasto){
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
