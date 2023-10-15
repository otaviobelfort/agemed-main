// Utilities
import { defineStore } from "pinia";
import axiosInstance from "@/service/axiosConfigMED";
import { Toast } from "@/utils/services";

export const useSolicitacaoStore = defineStore('solicitacao', {
    state() {
        return {
            solicitacoesEnviadas: [],
            solicitacoesNaoEnviadas: [],
            loader: false
        };
    },
    actions: {
                async listaTodasSolicitacoesNaoEnviadasPorUsuarioPaginado(page, size) {
            this.loader = true
            try {
                let response = await axiosInstance.get(`/v1/solicitacao`);
                this.solicitacoesNaoEnviadas = response.data;

            } catch (e) {
                console.log(e)
                Toast.fire("Erro ao buscar solicitações", "error");
            } finally {
                this.loader = false
            }

        }
    },
    getters: {}
})
