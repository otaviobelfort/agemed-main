package com.age.med.service;

import com.age.med.domain.Pessoa;

import java.util.List;

public interface PessoaService{

    Pessoa criarPessoa(Pessoa pessoa);

    List<Pessoa> obterTodasPessoas();

    Pessoa obterPessoaPorId(Long id);

    Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada);
    void excluirPessoa(Long id);
}
