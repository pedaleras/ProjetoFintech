package br.com.fiap.model;

import br.com.fiap.model.abstracts.TipoComum;

public class TipoInvestimento extends TipoComum {
    public TipoInvestimento() {
        super();
    }

    public TipoInvestimento(String descricao){
        super(descricao);
    }
}

