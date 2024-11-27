package dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoFinanceiroRepository extends JpaRepository<MovimentoFinanceiro, Integer> {
}
