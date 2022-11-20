package dev.fabriciosilva.gerenciadordespesas.service;

import dev.fabriciosilva.gerenciadordespesas.domain.Pessoa;
import dev.fabriciosilva.gerenciadordespesas.dto.PessoaDto;
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
        Page<Pessoa> pessoas = pessoaRepository.findAll(pageable);
        Page<PessoaDto> pessoasDto = pessoas.map(PessoaDto::new);
        return pessoasDto;
    }

    public PessoaDto novo(PessoaRequestForm form){
        Pessoa pessoa = form.toPessoa();
        pessoaRepository.save(pessoa);
        return new PessoaDto(pessoa);
    }

    public PessoaDto buscarPorId(Long id) {
        Optional<Pessoa> optional = pessoaRepository.findById(id);
        Pessoa pessoa = optional.orElseThrow();
        return new PessoaDto(pessoa);
    }

    public PessoaDto atualizar(Long id, PessoaRequestForm form) {
        Optional<Pessoa> optional = pessoaRepository.findById(id);
        Pessoa savedPessoa = optional.orElseThrow();
        Pessoa pessoa = form.toPessoa();
        pessoa.setId(savedPessoa.getId());
        pessoa.setDataCriacao(savedPessoa.getDataCriacao());

        pessoaRepository.save(pessoa);
        return new PessoaDto(pessoa);
    }

    public void excluir(Long id) {
        Optional<Pessoa> optional = pessoaRepository.findById(id);
        Pessoa pessoa = optional.orElseThrow();
        pessoaRepository.delete(pessoa);
    }
}
