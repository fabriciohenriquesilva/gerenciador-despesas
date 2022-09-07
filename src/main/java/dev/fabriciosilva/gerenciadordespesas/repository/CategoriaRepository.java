package dev.fabriciosilva.gerenciadordespesas.repository;

import dev.fabriciosilva.gerenciadordespesas.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
