package dev.fabriciosilva.gerenciadordespesas.repository;

import dev.fabriciosilva.gerenciadordespesas.domain.Subcategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Long> {

    @Query("SELECT s FROM Subcategoria s WHERE s.categoria.id = :categoria")
    List<Subcategoria> findByCategoria(Long categoria);

}
