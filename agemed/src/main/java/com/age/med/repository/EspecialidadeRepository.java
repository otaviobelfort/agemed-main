package com.age.med.repository;

import com.age.med.domain.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
}
