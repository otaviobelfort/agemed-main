package com.age.med.domain;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name = "especialidade", schema = "public")
public class Especialidade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "especialidade_id_seq")
    @SequenceGenerator(name = "especialidade_id_seq",sequenceName = "ESPECIALIDADE_ID_SEQ",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "especialidade")
    private String especialidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
