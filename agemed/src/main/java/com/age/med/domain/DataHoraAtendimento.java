package com.age.med.domain;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "data_hora_atendimento", schema = "public")
public class DataHoraAtendimento  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "data_hora_atendimento_id_seq")
    @SequenceGenerator(name = "data_hora_atendimento_id_seq",sequenceName = "DATA_HORA_ATENDIMENTO_ID_SEQ",allocationSize = 1)
    @Column(name = "id")
    private Long id;
   /* @Column(name = "medico")
    private Medico medico;*/
    @Column(name = "inicio_atendimento")
    private LocalDateTime inicioAtendimento;
    @Column(name = "fim_atendimento")
    private LocalDateTime fimAtendimento;
    @Column(name = "ativo")
    private Boolean ativo;

  /*  public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }*/

    public LocalDateTime getInicioAtendimento() {
        return inicioAtendimento;
    }

    public void setInicioAtendimento(LocalDateTime inicioAtendimento) {
        this.inicioAtendimento = inicioAtendimento;
    }

    public LocalDateTime getFimAtendimento() {
        return fimAtendimento;
    }

    public void setFimAtendimento(LocalDateTime fimAtendimento) {
        this.fimAtendimento = fimAtendimento;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
