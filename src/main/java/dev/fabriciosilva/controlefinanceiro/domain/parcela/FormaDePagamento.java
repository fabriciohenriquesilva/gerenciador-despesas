package dev.fabriciosilva.controlefinanceiro.domain.parcela;

public enum FormaDePagamento {

    DINHEIRO("Dinheiro"),
    PIX("PIX"),
    CARTAO_DEBITO("Cartão de Débito"),
    CARTAO_CREDITO("Cartão de Crédito"),
    CHEQUE("Cheque");

    private String descricao;

    FormaDePagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
