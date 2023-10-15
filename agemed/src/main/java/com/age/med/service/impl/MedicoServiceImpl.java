package com.age.med.service.impl;
/*
import com.age.med.domain.Medico;
import com.age.med.repository.MedicoRepository;
import com.age.med.service.MedicoService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico criarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public List<Medico> obterTodosMedicos() {
        return medicoRepository.findAll();
    }
    public Medico atualizarMedico(Long id, Medico medicoAtualizado) {
        Medico medicoExistente = medicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado com o ID: " + id));
        medicoExistente.setCrm(medicoAtualizado.getCrm());
        medicoExistente.setAtivo(medicoAtualizado.getAtivo());
        //medicoExistente.setPessoa(medicoAtualizado.getPessoa());
        //medicoExistente.setEspecialidade(medicoAtualizado.getEspecialidade());

        return medicoRepository.save(medicoExistente);
    }

}
*/
