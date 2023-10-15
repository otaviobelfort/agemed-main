package com.age.med.service;

import com.age.med.domain.DataHoraAtendimento;
import com.age.med.repository.DataHoraAtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface DataHoraAtendimentoService {
    DataHoraAtendimento criarAtendimento(DataHoraAtendimento atendimento);
    List<DataHoraAtendimento> obterTodosAtendimentos();
}
