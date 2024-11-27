package dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro;

public enum TipoMovimentoFinanceiro {

    RECEITA("Receita"),
    DESPESA("Despesa"),
    INVESTIMENTO("Investimento");

    private String descricao;

    TipoMovimentoFinanceiro(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
