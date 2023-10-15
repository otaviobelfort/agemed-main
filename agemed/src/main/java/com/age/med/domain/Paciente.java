package com.age.med.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "paciente", schema = "public")
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_id_seq")
    @SequenceGenerator(name = "paciente_id_seq",sequenceName = "PACIENTE_ID_SEQ",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "tipo_sanguineo")
    private Integer cpf;
    @Column(name = "altura")
    private String altura;
    @Column(name = "peso")
    private Boolean peso;
    @Column(name = "alergico")
    private Boolean alergico;
    @Column(name = "descricao_alergico")
    private String descricaoAlergico;
    @Column(name = "tipo_plano")
    private String tipoPlano;
    @Column(name = "numero_plano")
    private String numeroPlano;
    @ManyToOne
    @JoinColumn(name = "fk_pessoa",referencedColumnName = "id")
    private Pessoa pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public Boolean getPeso() {
        return peso;
    }

    public void setPeso(Boolean peso) {
        this.peso = peso;
    }

    public Boolean getAlergico() {
        return alergico;
    }

    public void setAlergico(Boolean alergico) {
        this.alergico = alergico;
    }

    public String getDescricaoAlergico() {
        return descricaoAlergico;
    }

    public void setDescricaoAlergico(String descricaoAlergico) {
        this.descricaoAlergico = descricaoAlergico;
    }

    public String getTipoPlano() {
        return tipoPlano;
    }

    public void setTipoPlano(String tipoPlano) {
        this.tipoPlano = tipoPlano;
    }

    public String getNumeroPlano() {
        return numeroPlano;
    }

    public void setNumeroPlano(String numeroPlano) {
        this.numeroPlano = numeroPlano;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
