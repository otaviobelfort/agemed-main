package com.age.med.domain.enumerated;
public enum TipoAgendamentoEnum {
    PRESENCIAL("PRESENCIAL"),
    ONLINE("ONLINE");

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    TipoAgendamentoEnum(String descricao) {

    }
}