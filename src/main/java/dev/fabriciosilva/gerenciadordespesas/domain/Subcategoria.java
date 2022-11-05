package dev.fabriciosilva.gerenciadordespesas.domain;

import dev.fabriciosilva.gerenciadordespesas.request.SubcategoriaPutRequestForm;

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

    public SubcategoriaPutRequestForm toSubcategoriaDto() {
        SubcategoriaPutRequestForm subcategoriaPutRequestForm = new SubcategoriaPutRequestForm();
        subcategoriaPutRequestForm.setId(String.valueOf(this.id));
        subcategoriaPutRequestForm.setNome(this.nome);
        subcategoriaPutRequestForm.setCategoria(this.categoria != null ? categoria.getNome() : "0");

        return subcategoriaPutRequestForm;
    }
}
