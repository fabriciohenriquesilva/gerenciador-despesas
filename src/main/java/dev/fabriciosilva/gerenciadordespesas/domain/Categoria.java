package dev.fabriciosilva.gerenciadordespesas.domain;

import dev.fabriciosilva.gerenciadordespesas.dto.CategoriaDto;
import dev.fabriciosilva.gerenciadordespesas.request.CategoriaForm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<Subcategoria> subcategorias = new ArrayList<>();

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<Despesa> despesas = new ArrayList<>();

    public Categoria() { }

    public Categoria(CategoriaForm form) {
        this.nome = form.getNome();
    }

    public Categoria(CategoriaDto dto) {
        this.id = Long.valueOf(dto.getId());
        this.nome = dto.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
