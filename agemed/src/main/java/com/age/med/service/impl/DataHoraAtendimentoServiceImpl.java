package com.age.med.service.impl;

import com.age.med.domain.DataHoraAtendimento;
import com.age.med.repository.DataHoraAtendimentoRepository;
import com.age.med.service.DataHoraAtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataHoraAtendimentoServiceImpl implements DataHoraAtendimentoService {

    @Autowired
    private DataHoraAtendimentoRepository atendimentoRepository;

    public DataHoraAtendimento criarAtendimento(DataHoraAtendimento atendimento) {
        return atendimentoRepository.save(atendimento);
    }

    public List<DataHoraAtendimento> obterTodosAtendimentos() {
        return atendimentoRepository.findAll();
    }

    // outras operações de serviço
}
