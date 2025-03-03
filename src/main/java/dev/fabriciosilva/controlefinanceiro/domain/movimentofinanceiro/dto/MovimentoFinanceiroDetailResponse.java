package dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.ParcelaDTO;

import java.util.LinkedList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovimentoFinanceiroDetailResponse {

    private MovimentoFinanceiroSummaryResponse movimentoFinanceiro;

    @JsonManagedReference
    private List<ParcelaDTO> parcelas;

    public List<ParcelaDTO> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<ParcelaDTO> parcelas) {
        this.parcelas = parcelas;
    }

    public MovimentoFinanceiroSummaryResponse getMovimentoFinanceiro() {
        return movimentoFinanceiro;
    }

    public void setMovimentoFinanceiro(MovimentoFinanceiroSummaryResponse movimentoFinanceiro) {
        this.movimentoFinanceiro = movimentoFinanceiro;
    }

    public void addParcela(ParcelaDTO parcelaDTO) {
        if (this.parcelas == null) {
            this.parcelas = new LinkedList<>();
        }
        this.parcelas.add(parcelaDTO);
    }
}
