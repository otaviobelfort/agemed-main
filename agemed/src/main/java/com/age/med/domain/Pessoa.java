package com.age.med.domain;

import com.age.med.domain.enumerated.SexoEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pessoa", schema = "public")
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_id_seq")
    @SequenceGenerator(name = "pessoa_id_seq",sequenceName = "PESSOA_ID_SEQ",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "data_nascimento")
    private Date dataNascimento;
    @Column(name = "sexo")
    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;
    @Column(name = "mae")
    private String mae;
 /*   @OneToOne
    @JoinColumn(name = "fk_endereco",referencedColumnName = "id")
    private Endereco endereco;
    @OneToOne
    @JoinColumn(name = "fk_documentos",referencedColumnName = "id")
    private Documentos documentos;
    @OneToOne
    @JoinColumn(name = "fk_contato",referencedColumnName = "id")
    private Contato contato;
*/
    /*@OneToOne
    private Medico medico;

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

 /*   public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Documentos getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Documentos documentos) {
        this.documentos = documentos;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }*/

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }
}
