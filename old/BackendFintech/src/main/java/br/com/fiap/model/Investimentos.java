package br.com.fiap.model;

import br.com.fiap.model.abstracts.Transacao;

import java.util.Date;

public class Investimentos extends Transacao {
    private TipoInvestimento tipoInvestimento;

    public Investimentos() {
        super();
    }

    @Override
    public String getMensagemTransacao() {
        return "O usuário " + getUsuario().getNomeUsuario() +
                " recebeu R$ " + getValor() +
                " no dia " + getDataString() +
                " de " + getTipoInvestimento().getDescricao();
    }

    public Investimentos(double valor, Date data, Usuario usuario, TipoInvestimento tipoInvestimento){
        super(valor,data,usuario);
        this.tipoInvestimento = tipoInvestimento;
    }

    public TipoInvestimento getTipoInvestimento() {
        return tipoInvestimento;
    }

    public void setTipoInvestimento(TipoInvestimento tipoInvestimento) {
        this.tipoInvestimento = tipoInvestimento;
    }


}

