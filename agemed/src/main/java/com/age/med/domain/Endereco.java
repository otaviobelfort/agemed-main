package com.age.med.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "endereco", schema = "public")
public class Endereco {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_id_seq")
    @SequenceGenerator(name = "endereco_id_seq",sequenceName = "ENDERECO_ID_SEQ",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "cep")
    private String cep;
    @Column(name = "uf")
    private String uf;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "logradouro")
    private String logradouro;
    @Column(name = "numero")
    private String numero;
    @Column(name = "complemento")
    private String complemento;
    @OneToOne
    @JoinColumn(name = "fk_pessoa",referencedColumnName = "id")
    private Pessoa pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
