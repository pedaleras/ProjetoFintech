package model;

public abstract class TipoComum {
    private int id;
    private String descricao;

    public TipoComum(){

    }

    public TipoComum(String descricao){
        this.descricao = descricao;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}

