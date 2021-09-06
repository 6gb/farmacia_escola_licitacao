package br.ufsm.csi.model;

public class MateriaPrima {
    int id;
    String descricao;
    String cas;
    String dcb;
    String dci;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public String getDcb() {
        return dcb;
    }

    public void setDcb(String dcb) {
        this.dcb = dcb;
    }

    public String getDci() {
        return dci;
    }

    public void setDci(String dci) {
        this.dci = dci;
    }
}
