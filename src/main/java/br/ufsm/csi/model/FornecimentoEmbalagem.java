package br.ufsm.csi.model;

public class FornecimentoEmbalagem {
    private int idLicitacao;
    private int idFornecedor;
    private int idEmbalagem;
    private double preco;
    private double media;

    public int getIdLicitacao() {
        return idLicitacao;
    }

    public void setIdLicitacao(int idLicitacao) {
        this.idLicitacao = idLicitacao;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public int getIdEmbalagem() {
        return idEmbalagem;
    }

    public void setIdEmbalagem(int idEmbalagem) {
        this.idEmbalagem = idEmbalagem;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }
}
