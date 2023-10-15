package com.age.med.service;

import com.age.med.domain.Especialidade;

import java.util.List;

public interface EspecialidadeService {

    Especialidade criarEspecialidade(Especialidade especialidade);

    List<Especialidade> obterTodasEspecialidades();

    Especialidade obterEspecialidadePorId(Long id);

    Especialidade atualizarEspecialidade(Long id, Especialidade especialidadeAtualizada);

    void excluirEspecialidade(Long id);
}
