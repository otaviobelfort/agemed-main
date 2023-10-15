package com.age.med.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
/*

@Entity
@Table(name = "medico", schema = "public")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medico_id_seq")
    @SequenceGenerator(name = "medico_id_seq",sequenceName = "MEDICO_ID_SEQ",allocationSize = 1)
    @Column(name = "id")
    private Long id;
    */
/*@Column(name = "especialidade")
    private Especialidade especialidade;*//*

    @Column(name = "crm")
    private String crm;
    @Column(name = "ativo")
    private boolean ativo;
    //@JdbcTypeCode(SqlTypes.JSON)
    */
/*@OneToOne
    @JoinColumn(name = "fk_pessoa",referencedColumnName = "id")
    private Pessoa pessoa;*//*


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

*/
/*    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }*//*


}
*/
