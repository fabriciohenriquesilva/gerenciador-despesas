package dev.fabriciosilva.gerenciadordespesas.repository;

import dev.fabriciosilva.gerenciadordespesas.domain.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Integer> {

}
