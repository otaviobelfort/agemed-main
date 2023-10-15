<template>
  <v-card>
    <v-data-table
      :headers="headers"
      :items="items"
      :search="search"
      :items-per-page="pages.itemsPerPage"
      :page="pages.page"
    >
      <template #item.check="{ item }">
        <v-checkbox
          :id="'checkBoxPosto' + item.raw.id"
          class="mt-5"
          @click="preencherFormEdicaoPosto(item.raw)"
        ></v-checkbox>
      </template>
      <template #item.descricao="{ item }">
        {{ item.raw.descricao || "Sem dados" }}
      </template>
      <template #item.codigo="{ item }">
        {{ item.raw.codigo || "Sem dados" }}
      </template>
      <template #item.ativo="{ item }">
        {{ item.raw.ativo ? "Verdadeiro" : "Falso" }}
      </template>
      <template #item.actions="{ item }">
        <v-btn
          dark
          size="30px"
          title="Deletar posto"
          icon="mdi-delete"
          class="mx-1 text-info rounded"
          @click="confirmaRemovePosto(item.raw)"
        />
      </template>
      <template #bottom> </template>
    </v-data-table>
  </v-card>
</template>

<script setup>
import { ref, onBeforeMount, watch, defineEmits } from "vue";
import { fieldsTabelaConsultaPosto } from "./fieldsTabelaConsultaPosto";
import PostoService from "@/service/PostoService";
import { Toast } from "@/utils/services";
let showDialogConfirmacao = ref(false);
const headers = ref(fieldsTabelaConsultaPosto);
const emit = defineEmits(["preencherFormEdicaoPosto"]);
const props = defineProps({
  resultadoConsultaPosto: {
    type: Array,
    required: false,
  },
});
let postoEditar = "";
let postoRemover = "";
watch(
  () => props.resultadoConsultaPosto,
  (first, second) => {
    postoEditar = "";
    items.value = first;
  }
);
let items = ref([]);
const pages = ref({
  itemsPerPage: 5,
  page: 1,
  pageCount: 1,
  items: [10, 20, 50, 100, "todos"],
});

const removePosto = () => {
  for (let i = 0; i < items.value.length; i++) {
    if (items.value[i].id == postoRemover.id) {
      PostoService.deletarPosto(items.value[i])
        .then(() => {
          items.value.splice(i, 1);
          postoEditar = "";
          emit("preencherFormEdicaoPosto", {});
          Toast.fire("Posto removido com sucesso!!", "success");
        })
        .catch(() => {
          Toast.fire("Falha ao remover posto!!", "error");
        });

    }
  }
};

const confirmaRemovePosto = (posto) => {
  postoRemover = posto;
  showDialogConfirmacao.value = !showDialogConfirmacao.value;
};
const preencherFormEdicaoPosto = (posto) => {
  if (document.getElementById("checkBoxPosto" + posto.id).checked) {
    if (postoEditar != "") {
      document.getElementById("checkBoxPosto" + posto.id).checked = false;
    } else {
      postoEditar = { ...posto };
      emit("preencherFormEdicaoPosto", { ...posto });
    }
  } else {
    postoEditar = "";
    emit("preencherFormEdicaoPosto", {});
  }
};
const confirmacaoDialog = (flag) => {
  if (flag) {
    removePosto();
  } else {
    console.log(false);
  }
};

onBeforeMount(() => {});
</script>

<style>
</style>
