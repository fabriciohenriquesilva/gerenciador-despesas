package dev.fabriciosilva.gerenciadordespesas.repository;

import dev.fabriciosilva.gerenciadordespesas.domain.Subcategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Long> {

}
