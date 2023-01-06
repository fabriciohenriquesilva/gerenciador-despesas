package dev.fabriciosilva.gerenciadordespesas.service;

import dev.fabriciosilva.gerenciadordespesas.domain.Pessoa;
import dev.fabriciosilva.gerenciadordespesas.dto.PessoaDto;
import dev.fabriciosilva.gerenciadordespesas.exception.RecursoInexistenteException;
import dev.fabriciosilva.gerenciadordespesas.repository.PessoaRepository;
import dev.fabriciosilva.gerenciadordespesas.request.PessoaRequestForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Page<PessoaDto> listarTodos(Pageable pageable){
        Page<PessoaDto> pessoasDto = pessoaRepository.findAll(pageable).map(PessoaDto::new);
        return pessoasDto;
    }

    public PessoaDto novo(PessoaRequestForm form){
        Pessoa pessoa = pessoaRepository.save(new Pessoa(form));
        return new PessoaDto(pessoa);
    }

    public PessoaDto buscarPorId(Long id) {
        if(id == null || id == 0){
            return new PessoaDto();
        }

        Optional<Pessoa> optional = pessoaRepository.findById(id);
        if(optional.isEmpty()) {
            throw new RecursoInexistenteException("Não encontramos a pessoa de id: " + id);
        }
        Pessoa pessoa = optional.get();
        return new PessoaDto(pessoa);
    }

    public PessoaDto atualizar(Long id, PessoaRequestForm form) {
        Optional<Pessoa> optional = pessoaRepository.findById(id);
        if(optional.isEmpty()) {
            throw new RecursoInexistenteException("Não encontramos a pessoa de id: " + id);
        }

        Pessoa savedPessoa = optional.get();
        Pessoa pessoa = new Pessoa(form);
        pessoa.setId(savedPessoa.getId());
        pessoa.setDataCriacao(savedPessoa.getDataCriacao());

        pessoaRepository.save(pessoa);
        return new PessoaDto(pessoa);
    }

    public void excluir(Long id) {
        Optional<Pessoa> optional = pessoaRepository.findById(id);
        if(optional.isEmpty()) {
            throw new RecursoInexistenteException("Não encontramos a pessoa de id: " + id);
        }
        Pessoa pessoa = optional.get();
        pessoaRepository.delete(pessoa);
    }
}
