package com.age.med.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "contato", schema = "public")
public class Contato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contato_id_seq")
    @SequenceGenerator(name = "contato_id_seq",sequenceName = "CONTATO_ID_SEQ",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "ddd_celular")
    private Integer dddCelular;
    @Column(name = "celular")
    private Long celular;
    @Column(name = "ddd_telefone")
    private Integer dddTelefone;
    @Column(name = "telefone")
    private Long telefone;
    @Column(name = "email")
    private String email;
    @OneToOne
    @JoinColumn(name = "fk_pessoa",referencedColumnName = "id")
    private Pessoa pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDddCelular() {
        return dddCelular;
    }

    public void setDddCelular(Integer dddCelular) {
        this.dddCelular = dddCelular;
    }

    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long celular) {
        this.celular = celular;
    }

    public Integer getDddTelefone() {
        return dddTelefone;
    }

    public void setDddTelefone(Integer dddTelefone) {
        this.dddTelefone = dddTelefone;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
