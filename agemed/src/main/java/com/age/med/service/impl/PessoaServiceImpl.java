package com.age.med.service.impl;

import com.age.med.domain.Pessoa;
import com.age.med.repository.PessoaRepository;
import com.age.med.service.PessoaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PessoaServiceImpl implements PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa criarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> obterTodasPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa obterPessoaPorId(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com o ID: " + id));
    }
    public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
        Pessoa pessoaExistente = obterPessoaPorId(id);
        pessoaExistente.setNome(pessoaAtualizada.getNome());
        pessoaExistente.setDataNascimento(pessoaAtualizada.getDataNascimento());
        pessoaExistente.setSexo(pessoaAtualizada.getSexo());
        pessoaExistente.setMae(pessoaAtualizada.getMae());/*
        pessoaExistente.setEndereco(pessoaAtualizada.getEndereco());
        pessoaExistente.setDocumentos(pessoaAtualizada.getDocumentos());
        pessoaExistente.setContato(pessoaAtualizada.getContato());*/
        return pessoaRepository.save(pessoaExistente);
    }

    public void excluirPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }
}