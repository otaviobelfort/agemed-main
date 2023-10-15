package com.age.med.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "usuario", schema = "public")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq")
    @SequenceGenerator(name = "usuario_id_seq",sequenceName = "USUARIO_ID_SEQ",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "cpf")
    private Integer cpf;
    @Column(name = "senha")
    private String senha;
    @Column(name = "senha_temporaria")
    private Boolean senhaTemporaria;
    @Column(name = "ativo")
    private Boolean ativo;
    @Column(name = "email")
    private String email;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getSenhaTemporaria() {
        return senhaTemporaria;
    }

    public void setSenhaTemporaria(Boolean senhaTemporaria) {
        this.senhaTemporaria = senhaTemporaria;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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
