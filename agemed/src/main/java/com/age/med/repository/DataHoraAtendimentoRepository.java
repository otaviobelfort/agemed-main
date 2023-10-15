package com.age.med.repository;

import com.age.med.domain.DataHoraAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataHoraAtendimentoRepository extends JpaRepository<DataHoraAtendimento, Long> {
}
