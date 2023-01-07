package dev.fabriciosilva.gerenciadordespesas.repository;

import dev.fabriciosilva.gerenciadordespesas.domain.Despesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Integer> {

    @Query(value = "SELECT * FROM despesa WHERE descricao LIKE %:descricao%", nativeQuery = true)
    Page<Despesa> findByDescricao(Pageable paginacao, String descricao);

    @Query(value = "SELECT d FROM Despesa d WHERE d.categoria.nome = :categoria")
    Page<Despesa> findByCategoria(Pageable paginacao, String categoria);

    @Query(value = "SELECT d FROM Despesa d WHERE d.credor.id = :credor")
    Page<Despesa> findByCredor(Pageable paginacao, Long credor);
}
