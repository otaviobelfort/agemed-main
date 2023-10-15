package com.age.med.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "especialidade", schema = "public")
public class EspecialidadeMedico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "especialidade_id_seq")
    @SequenceGenerator(name = "especialidade_id_seq",sequenceName = "ESPECIALIDADE_ID_SEQ",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "fk_especialidade",referencedColumnName = "id")
    private Especialidade especialidade;


}
