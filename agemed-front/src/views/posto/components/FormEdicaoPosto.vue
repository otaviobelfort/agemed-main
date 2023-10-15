<template>
  <div>
    <v-form ref="formCadastroPosto">
      <Loading v-show="store.loader"/>
      <v-col cols="12">
        <v-row>
          <v-col class="pb-0" cols="6">
            <v-text-field
              variant="underlined"
              label="Descrição"
              v-model="postoEdicao.descricao"
            ></v-text-field>
          </v-col>
          <v-col class="pb-0" cols="6">
            <v-autocomplete
              :items="listTipoPosto"
              item-title="label"
              item-value="id"
              label="Tipo de posto"
              variant="underlined"
              v-model="postoEdicao.tipoPosto"
            >
            </v-autocomplete>
          </v-col>
          <v-col class="pb-0" cols="6">
            <v-text-field
              variant="underlined"
              label="Código"
              type="number"
              v-model="postoEdicao.codigo"
            ></v-text-field>
          </v-col>

          <v-col class="pb-0" cols="6">
            <h4>Ativo:</h4>
            <v-radio-group v-model="postoEdicao.ativo" inline>
              <v-radio
                class="mr-3 ml-5"
                label="Sim"
                value="true"
                color="green-darken-3"
              ></v-radio>
              <v-radio label="Não" value="false" color="red-darken-3"></v-radio>
            </v-radio-group>
          </v-col>
        </v-row>
      </v-col>
      <div class="d-flex justify-end">
        <v-btn class="mx-2" color="blue-darken-3" @click="bucarPostoCustom"
          >Buscar</v-btn
        >
        <v-btn
          v-show="showBotaoEditar"
          @click="editarPosto"
          class="mx-2"
          color="blue-darken-3"
          >Editar</v-btn
        >
      </div>
    </v-form>
    <TabelaConsultaPosto
      @preencherFormEdicaoPosto="preencherFormEdicaoPosto"
      :resultadoConsultaPosto="store.resultadoConsultaPosto"
      class="mt-3"
    />
  </div>
</template>

<script setup>
import { ref } from "vue";
import PostoService from "@/service/PostoService";
import TabelaConsultaPosto from "./TabelaConsultaPosto.vue";
import Loading from "@/components/Loading.vue";
import { rules } from "@/utils/validation";
const { required } = rules;
import { usePostoStore } from "@/store/posto";
const store = usePostoStore();
let overlay = ref(false);
let showBotaoEditar = ref(false);
let resultadoConsultaPosto = ref([]);
let postoEdicao = ref({
  ativo: "false",
  descricao: null,
  tipoPosto: null,
  codigo: null,
});
let listTipoPosto = ref([
  { label: "ONLINE", id: "POSTO_ONLINE" },
  { label: "OFFLINE", id: "POSTO_OFFLINE" },
  { label: "MOVEL", id: "POSTO_MOVEL" },
]);
let formCadastroPosto = ref(null);
import { Toast } from "@/utils/services";
let editarPosto = async () => {
  let posto = { ...postoEdicao.value };
  posto.codigo = parseInt(posto.codigo);
  posto.ativo = posto.ativo == "true" ? true : false;
  overlay.value=true
  PostoService.editarPosto(posto)
    .then((response) => {
      Toast.fire("Edição realizada com sucesso!!", "success");
      overlay.value=false
    })
    .catch((e) => {
      overlay.value=false
      Toast.fire(e, "error");
    });
};
let bucarPostoCustom = () => {
  let posto = { ...postoEdicao.value };
  posto.codigo = parseInt(posto.codigo);
  posto.ativo = posto.ativo == "true" ? true : false;
  posto.descricao = posto.descricao == "" ? null : posto.descricao;
  
  store.customQueryPosto(posto)
  // PostoService.customQueryPosto(posto)
  //   .then((response) => {
  //     Toast.fire("Busca realizada com sucesso!!", "success");
  //     store.resultadoConsultaPosto.value = {};
  //     store.resultadoConsultaPosto.value = response.data;
  //     overlay.value=false
  //     if (response.data.length == 0) {
  //       Toast.fire("Nenhum posto encontrado", "error");
  //     }
  //   })
  //   .catch((e) => {
  //     overlay.value=false
  //     Toast.fire(e, "error");
  //   });
};
const preencherFormEdicaoPosto = (posto) => {
  if (isObjectEmpty(posto)) {
    showBotaoEditar.value = false;
  } else {
    showBotaoEditar.value = true;
  }

  postoEdicao.value = posto;
  postoEdicao.value.ativo = posto.ativo ? "true" : "false";
};
const isObjectEmpty = (objectName) => {
  return Object.keys(objectName).length === 0;
};
</script>

<style>
</style>