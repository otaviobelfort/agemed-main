package com.age.med.service.impl;

import com.age.med.domain.Especialidade;
import com.age.med.repository.EspecialidadeRepository;
import com.age.med.service.EspecialidadeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadeServiceImpl implements EspecialidadeService {
    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public Especialidade criarEspecialidade(Especialidade especialidade) {
        return especialidadeRepository.save(especialidade);
    }

    public List<Especialidade> obterTodasEspecialidades() {
        return especialidadeRepository.findAll();
    }

    public Especialidade obterEspecialidadePorId(Long id) {
        return especialidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Especialidade não encontrada com o ID: " + id));
    }
    public Especialidade atualizarEspecialidade(Long id, Especialidade especialidadeAtualizada) {
        Especialidade especialidadeExistente = obterEspecialidadePorId(id);
        especialidadeExistente.setEspecialidade(especialidadeAtualizada.getEspecialidade());
        return especialidadeRepository.save(especialidadeExistente);
    }
    public void excluirEspecialidade(Long id) {
        especialidadeRepository.deleteById(id);

    }
}