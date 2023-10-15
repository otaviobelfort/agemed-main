import { defineStore } from "pinia";
import instance from "@/service/axiosConfigMED";
import { Toast } from "@/utils/services";

export const useListaStore = defineStore("lista", {
  state() {
    return {
      bairros: []
    };
  },
  actions: {

    async buscarBairrosPorIdCidade(id) {
      this.bairros = await instance.get(`/lista/bairros?cidade=${id}`);
    }

  },
});
