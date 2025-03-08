package dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro;

import dev.fabriciosilva.controlefinanceiro.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovimentoFinanceiroRepository extends JpaRepository<MovimentoFinanceiro, Integer> {

    Optional<MovimentoFinanceiro> findByIdAndUsuario(Integer id, User usuario);

    Page<MovimentoFinanceiro> findAllByUsuario(Pageable pageable, User usuario);

}
