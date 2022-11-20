package dev.fabriciosilva.gerenciadordespesas.repository;

import dev.fabriciosilva.gerenciadordespesas.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
