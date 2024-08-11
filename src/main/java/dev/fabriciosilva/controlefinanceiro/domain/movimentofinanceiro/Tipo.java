package dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro;

public enum Tipo {

    RECEITA("Receita"),
    DESPESA("Despesa"),
    INVESTIMENTO("Investimento");

    private String descricao;

    Tipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
