package com.tss.webapps.projectbasepoc.domain.transaction;

public enum CategoriaMoviment {
    ALIMENTACAO("Alimentação"),
    TRANSPORTE("Transporte"),
    SAUDE("Saúde"),
    EDUCACAO("Educação"),
    LAZER("Lazer"),
    INVESTIMENTOS("Investimentos"),
    RESGATES("Resgates"),
    SALARIO("Salário"),
    IMOVEL("Imóvel"),
    FINANCIAMENTO("Financiamento"),
    SERVICOS("Serviços"),
    OUTROS("Outros");

    private String descricao;

    CategoriaMoviment(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
