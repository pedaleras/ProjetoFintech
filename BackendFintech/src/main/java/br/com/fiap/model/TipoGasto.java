package br.com.fiap.model;

import br.com.fiap.model.abstracts.TipoComum;

public class TipoGasto extends TipoComum {
    public TipoGasto() {
        super();
    }

    public TipoGasto (String descricao){
        super(descricao);
    }
}
