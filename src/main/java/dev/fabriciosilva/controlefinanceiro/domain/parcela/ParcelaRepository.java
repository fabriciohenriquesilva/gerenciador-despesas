package dev.fabriciosilva.controlefinanceiro.domain.parcela;

import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.MovimentoFinanceiro;
import dev.fabriciosilva.controlefinanceiro.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Integer> {

    List<Parcela> findAllByMovimentoFinanceiro(MovimentoFinanceiro movimentoFinanceiro);

    @Query("SELECT p FROM Parcela p WHERE p.id = :id AND p.movimentoFinanceiro.usuario = :usuario")
    Optional<Parcela> findByIdAndUser(Integer id, User usuario);
}
