package com.age.med.controller;

import com.age.med.domain.Especialidade;
import com.age.med.service.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @PostMapping("/cadastro")
    public Especialidade criarEspecialidade(@RequestBody Especialidade especialidade) {
        return especialidadeService.criarEspecialidade(especialidade);
    }

    @GetMapping("/lista")
    public List<Especialidade> obterTodasEspecialidades() {
        return especialidadeService.obterTodasEspecialidades();
    }

    @GetMapping("/{id}")
    public Especialidade obterEspecialidadePorId(@PathVariable Long id) {
        return especialidadeService.obterEspecialidadePorId(id);
    }

    @PutMapping("/{id}")
    public Especialidade atualizarEspecialidade(@PathVariable Long id, @RequestBody Especialidade especialidadeAtualizada) {
        return especialidadeService.atualizarEspecialidade(id, especialidadeAtualizada);
    }

    @DeleteMapping("/{id}")
    public void excluirEspecialidade(@PathVariable Long id) {
        especialidadeService.excluirEspecialidade(id);
    }
}
