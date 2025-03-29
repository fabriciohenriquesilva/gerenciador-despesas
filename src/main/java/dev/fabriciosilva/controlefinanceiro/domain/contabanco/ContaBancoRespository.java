package dev.fabriciosilva.controlefinanceiro.domain.contabanco;

import dev.fabriciosilva.controlefinanceiro.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaBancoRespository extends JpaRepository<ContaBanco, Integer> {

    Optional<ContaBanco> findByIdAndUsuario(Integer id, User usuario);

    Page<ContaBanco> findAllByUsuario(Pageable paginacao, User usuario);
}
