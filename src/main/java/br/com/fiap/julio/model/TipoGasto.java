package br.com.fiap.julio.model;


import br.com.fiap.julio.model.abstracts.TipoComum;

public class TipoGasto extends TipoComum {
    public TipoGasto() {
        super();
    }

    public TipoGasto (String descricao){
        super(descricao);
    }
}
