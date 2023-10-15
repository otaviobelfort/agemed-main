package com.age.med.controller;

import com.age.med.domain.Pessoa;
import com.age.med.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa criarPessoa(@RequestBody Pessoa pessoa) {
        return pessoaService.criarPessoa(pessoa);
    }

    @GetMapping("/lista")

    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa> obterTodasPessoas() {
        return pessoaService.obterTodasPessoas();
    }

    @GetMapping("/{id}")

    @ResponseStatus(HttpStatus.OK)
    public Pessoa obterPessoaPorId(@PathVariable Long id) {
        return pessoaService.obterPessoaPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pessoa atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizada) {
        return pessoaService.atualizarPessoa(id, pessoaAtualizada);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirPessoa(@PathVariable Long id) {
        pessoaService.excluirPessoa(id);
    }
}
