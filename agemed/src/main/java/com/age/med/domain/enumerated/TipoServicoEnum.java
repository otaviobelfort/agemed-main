package com.age.med.domain.enumerated;
public enum TipoServicoEnum {
    PRESENCIAL("PARTICULAR"),
    ONLINE("CONVENIO");

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    TipoServicoEnum(String descricao) {

    }
}