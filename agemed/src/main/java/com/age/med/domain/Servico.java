package com.age.med.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "servico", schema = "public")
public class Servico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servico_id_seq")
    @SequenceGenerator(name = "servico_id_seq",sequenceName = "SERVICO_ID_SEQ",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "descricao")
    private String especialidade;
    @Column(name = "valor")
    private String valor;
    @Column(name = "ativo")
    private Boolean ativo;

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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
