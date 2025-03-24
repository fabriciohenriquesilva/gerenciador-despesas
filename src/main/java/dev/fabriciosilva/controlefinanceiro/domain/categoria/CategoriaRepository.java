package dev.fabriciosilva.controlefinanceiro.domain.categoria;

import dev.fabriciosilva.controlefinanceiro.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Optional<Categoria> findByIdAndUsuario(Integer id, User usuario);

    Page<Categoria> findAllByUsuario(Pageable pageable, User user);
}
