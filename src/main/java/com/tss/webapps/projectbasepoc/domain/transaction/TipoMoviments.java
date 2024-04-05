package com.tss.webapps.projectbasepoc.domain.transaction;

public enum TipoMoviments {
    RECEITA("Receita"),
    DESPESA("Despesa");

    private String descricao;

    TipoMoviments(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
