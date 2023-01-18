package dev.fabriciosilva.gerenciadordespesas.service;

import dev.fabriciosilva.gerenciadordespesas.domain.Categoria;
import dev.fabriciosilva.gerenciadordespesas.domain.Despesa;
import dev.fabriciosilva.gerenciadordespesas.domain.Pessoa;
import dev.fabriciosilva.gerenciadordespesas.domain.Subcategoria;
import dev.fabriciosilva.gerenciadordespesas.dto.DespesaDto;
import dev.fabriciosilva.gerenciadordespesas.exception.FormValidationException;
import dev.fabriciosilva.gerenciadordespesas.exception.RecursoInexistenteException;
import dev.fabriciosilva.gerenciadordespesas.repository.CategoriaRepository;
import dev.fabriciosilva.gerenciadordespesas.repository.DespesaRepository;
import dev.fabriciosilva.gerenciadordespesas.repository.PessoaRepository;
import dev.fabriciosilva.gerenciadordespesas.repository.SubcategoriaRepository;
import dev.fabriciosilva.gerenciadordespesas.request.DespesaPostRequestForm;
import dev.fabriciosilva.gerenciadordespesas.request.DespesaPutRequestForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    @Autowired
    private PessoaRepository credorRepository;

    public Despesa novo(DespesaPostRequestForm form){
        Despesa despesa = form.toDespesa();

        Long categoriaId = Long.valueOf(form.getCategoria());
        Long subcategoriaId = Long.valueOf(form.getSubcategoria());
        Long credorId = Long.valueOf(form.getCredor());

        definirCategoria(despesa, categoriaId);
        definirSubcategoria(despesa, subcategoriaId);
        definirCredor(despesa, credorId);

        despesaRepository.save(despesa);
        return despesa;
    }

    public Page<Despesa> listarTodos(Pageable pageable){
        return despesaRepository.findAll(pageable);
    }

    public void excluir(Integer id){
        Optional<Despesa> optional = despesaRepository.findById(id);
        if(optional.isEmpty()){
            throw new RecursoInexistenteException("A despesa de id " + id + " não foi encontrada na base de dados");
        }
        despesaRepository.deleteById(id);
    }

    public DespesaDto detalhar(Integer id) {
        Optional<Despesa> optional = despesaRepository.findById(id);
        if(optional.isEmpty()){
            throw new RecursoInexistenteException("A despesa de id " + id + " não foi encontrada na base de dados");
        }
        return new DespesaDto(optional.get());
    }

    public DespesaPutRequestForm buscarPorId(Integer id){
        Optional<Despesa> optional = despesaRepository.findById(id);
        if(optional.isEmpty()){
            throw new RecursoInexistenteException("A despesa de id " + id + " não foi encontrada na base de dados");
        }

        Despesa despesa = optional.get();
        return new DespesaPutRequestForm(despesa);
    }

    public void editar(DespesaPutRequestForm form){
        String despesaId = form.getId();
        Optional<Despesa> optional = despesaRepository.findById(Integer.valueOf(despesaId));

        if(optional.isEmpty()){
            throw new RecursoInexistenteException("A despesa de id " + despesaId + " não foi encontrada na base de dados");
        }

        Despesa despesa = new Despesa(form);

        Long categoriaId = Long.valueOf(form.getCategoria());
        Long subcategoriaId = Long.valueOf(form.getSubcategoria());
        Long credorId = Long.valueOf(form.getCredorNome());

        definirCategoria(despesa, categoriaId);
        definirSubcategoria(despesa, subcategoriaId);
        definirCredor(despesa, credorId);

        despesaRepository.save(despesa);
    }

    private void definirCategoria(Despesa despesa, Long categoriaId) {
        if(categoriaId > 0) {
            Optional<Categoria> optionalCategoria = categoriaRepository.findById(categoriaId);

            if (optionalCategoria.isPresent()) {
                Categoria categoria = optionalCategoria.get();
                despesa.setCategoria(categoria);
            }
            else {
                throw new FormValidationException("A categoria " + categoriaId + " não foi encontrada na base de dados");
            }
        }
    }

    private void definirSubcategoria(Despesa despesa, Long subcategoriaId) {
        if(subcategoriaId > 0) {
            Optional<Subcategoria> optionalSubcategoria = subcategoriaRepository.findById(subcategoriaId);

            if (optionalSubcategoria.isPresent()) {
                Subcategoria subcategoria = optionalSubcategoria.get();
                despesa.setSubcategoria(subcategoria);
            }
            else {
                throw new FormValidationException("A subcategoria " + subcategoriaId + " não foi encontrada na base de dados");
            }
        }
    }

    private void definirCredor(Despesa despesa, Long credorId) {
        if(credorId > 0) {
            Optional<Pessoa> optionalCredor = credorRepository.findById(credorId);

            if (optionalCredor.isPresent()) {
                Pessoa credor = optionalCredor.get();
                despesa.setCredor(credor);
            }
            else {
                throw new FormValidationException("O credor de id " + credorId + " não foi encontrada na base de dados");
            }
        }
    }

    public Page<Despesa> listarPorCategoria(Pageable paginacao, String categoria) {
        return despesaRepository.findByCategoria(paginacao, categoria);
    }

    public Page<Despesa> listarPorDescricao(Pageable paginacao, String descricao) {
        return despesaRepository.findByDescricao(paginacao, descricao);
    }

    public Page<Despesa> listarPorCredor(Pageable paginacao, String credor) {
        boolean matches = credor.matches("\\d+");
        if(matches){
            Long credorId = Long.valueOf(credor);
            boolean exists = credorRepository.existsById(credorId);
            if(exists) {
                return despesaRepository.findByCredor(paginacao, credorId);
            }
            throw new RecursoInexistenteException("Credor não existe");
        }
        throw new IllegalArgumentException("URL incorreta");

    }
}
