package com.age.med.domain;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "documentos", schema = "public")
public class Documentos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "documentos_id_seq")
    @SequenceGenerator(name = "documentos_id_seq",sequenceName = "DOCUMENTOS_ID_SEQ",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "rg")
    private String rg;
    @Column(name = "sus")
    private String sus;
    @Column(name = "cnh")
    private String cnh;
    @OneToOne
    @JoinColumn(name = "fk_pessoa",referencedColumnName = "id")
    private Pessoa pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getSus() {
        return sus;
    }

    public void setSus(String sus) {
        this.sus = sus;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
/*
package br.gov.ma.ssp.sic.domain.sic;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Audited
@AuditTable(value = "filiacao_aud", schema = "auditoria")
@Entity
@Table(name = "filiacao", schema = "public")
public class Filiacao implements Serializable {
    private static final long serialVersionUID = 1L;
    //@NotAudited
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filiacao_id_seq")
    @SequenceGenerator(name = "filiacao_id_seq",sequenceName = "FILIACAO_ID_SEQ",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome_pai")
    private String	nomePai;
    @Column(name = "nome_mae")
    private String	nomeMae;
    @ManyToOne
    @JoinColumn(name = "fk_pessoa",referencedColumnName = "id")
    private Pessoa pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
 */