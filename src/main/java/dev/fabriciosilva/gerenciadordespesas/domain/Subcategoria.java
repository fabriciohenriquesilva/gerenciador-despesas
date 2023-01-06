package dev.fabriciosilva.gerenciadordespesas.domain;

import dev.fabriciosilva.gerenciadordespesas.dto.SubcategoriaDto;
import dev.fabriciosilva.gerenciadordespesas.request.SubcategoriaForm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subcategoria")
public class Subcategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    @OneToMany(mappedBy = "subcategoria", fetch = FetchType.LAZY)
    private List<Despesa> despesa = new ArrayList<>();

    public Subcategoria() { }

    public Subcategoria(SubcategoriaForm form) {
        this.nome = form.getNome();
    }

    public Subcategoria(SubcategoriaDto dto) {
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public SubcategoriaDto toSubcategoriaDto() {
        SubcategoriaDto subcategoriaDto = new SubcategoriaDto();
        subcategoriaDto.setId(String.valueOf(this.id));
        subcategoriaDto.setNome(this.nome);
        subcategoriaDto.setCategoria(this.categoria != null ? categoria.getNome() : "0");

        return subcategoriaDto;
    }
}
