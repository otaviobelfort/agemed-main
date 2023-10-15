package com.age.med.controller;

import com.age.med.domain.DataHoraAtendimento;
import com.age.med.service.DataHoraAtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atendimentos")
public class DataHoraAtendimentoController {

    @Autowired
    private DataHoraAtendimentoService atendimentoService;

    @PostMapping("/cadastro")
    public DataHoraAtendimento criarAtendimento(@RequestBody DataHoraAtendimento atendimento) {
        return atendimentoService.criarAtendimento(atendimento);
    }

    @GetMapping("/lista")
    public List<DataHoraAtendimento> obterTodosAtendimentos() {
        return atendimentoService.obterTodosAtendimentos();
    }

}
