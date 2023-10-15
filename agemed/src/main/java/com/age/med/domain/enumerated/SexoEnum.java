package com.age.med.domain.enumerated;
public enum SexoEnum {
    M("MASCULINO"),
    F("FEMINIMO");

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    SexoEnum(String descricao) {

    }
}