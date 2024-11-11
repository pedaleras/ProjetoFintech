package br.com.fiap.julio.model;

import br.com.fiap.julio.model.abstracts.TipoComum;

public class TipoInvestimento extends TipoComum {
    public TipoInvestimento() {
        super();
    }

    public TipoInvestimento(String descricao){
        super(descricao);
    }
}

