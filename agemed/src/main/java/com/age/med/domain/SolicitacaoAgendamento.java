package com.age.med.domain;

import com.age.med.domain.enumerated.TipoAgendamentoEnum;
import com.age.med.domain.enumerated.TipoServicoEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "solicitacao_agendamento", schema = "public")
public class SolicitacaoAgendamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solicitacao_agendamento_id_seq")
    @SequenceGenerator(name = "solicitacao_agendamento_id_seq",sequenceName = "SOLICITACAO_AGENDAMENTO_ID_SEQ",allocationSize = 1)
    @Column(name = "id")
    private Long id;
/*    @Column(name = "medico")
    private Medico medico;*/
    @Column(name = "data_atendimento")
    private Date dataAtendimento;
    @Column(name = "usuario")
    private Usuario usuario;
    @Column(name = "data_inclusao")
    private LocalDateTime dataInclusao;
    @Column(name = "data_finalizacao")
    private LocalDateTime dataFinalizacao;
    @Column(name = "descricao_alergico")
    private String descricaoAlergico;
    @Column(name = "tipo_agendamento")
    @Enumerated(EnumType.STRING)
    private TipoAgendamentoEnum tipoAgendamento;
    @Column(name = "hora_atendimento")
    private String horaAtendimento;
    @Column(name = "especialidade")
    private Especialidade especialidade;
    @Column(name = "servico")
    private Servico servico;
    @Column(name = "tipo_servico")
    @Enumerated(EnumType.STRING)
    private TipoServicoEnum tipoServico;
    @Column(name = "valor_servico")
    private String valorServico;
    @Column(name = "desconto_servico")
    private String descontoServico;
    @ManyToOne
    @JoinColumn(name = "fk_pessoa",referencedColumnName = "id")
    private Pessoa pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   /* public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }*/

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public LocalDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public String getDescricaoAlergico() {
        return descricaoAlergico;
    }

    public void setDescricaoAlergico(String descricaoAlergico) {
        this.descricaoAlergico = descricaoAlergico;
    }

    public TipoAgendamentoEnum getTipoAgendamento() {
        return tipoAgendamento;
    }

    public void setTipoAgendamento(TipoAgendamentoEnum tipoAgendamento) {
        this.tipoAgendamento = tipoAgendamento;
    }

    public String getHoraAtendimento() {
        return horaAtendimento;
    }

    public void setHoraAtendimento(String horaAtendimento) {
        this.horaAtendimento = horaAtendimento;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public TipoServicoEnum getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServicoEnum tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getValorServico() {
        return valorServico;
    }

    public void setValorServico(String valorServico) {
        this.valorServico = valorServico;
    }

    public String getDescontoServico() {
        return descontoServico;
    }

    public void setDescontoServico(String descontoServico) {
        this.descontoServico = descontoServico;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
