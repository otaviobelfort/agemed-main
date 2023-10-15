<script setup>
import BreadCrumbComponets from "@/components/BreadCrumbComponets.vue";
import ValidaBiometria from "@/views/agendamento/ValidaBiometria.vue";
//import DialogVinculacao from "./solicitacao/vinculacao/DialogVinculacao.vue";
import { solicitacao } from "./fields";
import { ref, onBeforeMount,onMounted } from "vue";
import { Toast } from "@/utils/services";

import PessoaService from "@/service/PessoaService";

let loading = ref(false);
const search = ref(null);
const headers = ref(solicitacao);
//const items = ref([]);
const itemsEnviados = ref([]);
const itemsNaoEnviados = ref([]);
const tab = ref(null);

/*
    { key: 'tipoVia', title: 'Paciente' },
    { key: 'posto', title: "Médico" },
    { key: 'tipo', title: 'Consulta' },
    { key: 'dataInclusao', title: 'Data da consulta' },
    { key: 'cidadao', title: 'Tipo de serviço' },
    { key: 'movimentacao', title: 'Status' },
    { key: 'actions', title: 'Ações' },
    */
const items2 = ref([
    {
        solicitacao: 1,
        cidadao: "Plano individual",
        tipo: "Clínico geral",
        posto: "Ricardo Gomez",
        tipoVia: "Rodrigo Silva",
        dataInclusao: "20/08/2023 8:00",
        movimentacao: "AGENDADO",
    },
    {
      solicitacao: 2,
        cidadao: "Particular",
        tipo: "Cardiologista",
        posto: "Júly Mary",
        tipoVia: "Maria Joana Rizzo",
        dataInclusao: "25/08/2023 11:00",
        movimentacao: "AGUARDANDO CONFIRMAÇÃO",
    },
  ])
const items = ref([{}]);

/* onBeforeMount(() => { listaAgendamentos
  SolicitacaoService.listaIdPostIdUsuario(1, 1410).then((response) => {
    //items.value = response.data;
    itemsEnviados.value = response.data.filter(
      (item) => item.movimentacao === "ENVIADO"
    );
    itemsNaoEnviados.value = response.data.filter(
      (item) => item.movimentacao != "ENVIADO"
    );

  });
}); */
const formatarDataHora = (dataHora) => {
  const [data, hora] = dataHora.split(" ");
  const [horas, minutos, segundos] = hora.split(":");
  const [ano, mes, dia] = data.split("-");
  const dataFormatada = `${dia}/${mes}/${ano} ${horas}:${minutos}`;
  return dataFormatada;
};

const formatarData = (data) => {
      const dataObj = new Date(data);
      const dia = String(dataObj.getDate()).padStart(2, '0');
      const mes = String(dataObj.getMonth() + 1).padStart(2, '0');
      const ano = dataObj.getFullYear();
      const horas = String(dataObj.getHours()).padStart(2, '0');
      const minutos = String(dataObj.getMinutes()).padStart(2, '0');

      return `${dia}/${mes}/${ano} ${horas}h${minutos}`;
    }

const pages = ref({
  itemsPerPage: 5,
  page: 1,
  pageCount: 1,
  items: [10, 20, 50, 100, "todos"],
});

const lengthPage = (length) => {
  let count = Math.ceil(length / pages.value.itemsPerPage);
  return count;
};
const cancelarAgendamento = (id) => {


  PessoaService.cancelarAgendamento(id).then((response) => {
    //showSnackBar("Agendamento cancelado com sucesso!", "success");


  });
  Toast.fire("Agendamento cancelado com sucesso!", "success");
};

const cancelarAgendamentoAguardar = (id) => {

Toast.fire("Aguarde a confirmação!", "error");
};

onBeforeMount(() => {
  PessoaService.listaAgendamentos().then((response) => {
    items.value = response.data
  });

});

const allItems = (item) => {
  if (item === "todos") {
    pages.value.itemsPerPage = items.value.length;
  }
};



</script>
<template>
  <bread-crumb-componets :data="$route.name" />

  <v-col cols="12">
    <v-card>
      <v-card-title>
        <v-tabs v-model="tab" bg-color="transparent" color="basil" grow>
          <v-tab value="one">Novas</v-tab>
          <v-tab value="two">Geral</v-tab>
        </v-tabs>
      </v-card-title>

      <v-card-text>
        <v-window v-model="tab">
          <v-window-item value="one">

            <!-- <v-btn class="ma-2"
                size="30px"
                title="Vincular pessoa"

                icon="mdi-account-plus"

              ></v-btn>
              <v-btn class="ma-2"
                        @click="createPost()"
                        color="blue-darken-3"
                        type="submit"
                        >  Agendar consulta
                        <v-icon
                              icon="mdi-account-plus"
                            ></v-icon>
                        </v-btn
-->
            <div>
            <ValidaBiometria

                  title="Agendar consulta">
              </ValidaBiometria>
            </div>
            <v-data-table
              :headers="headers"
              :items="items"
              :search="search"
              :items-per-page="pages.itemsPerPage"
              :page="pages.page"
              :sort-by="item?.raw?.id"
              :sortDesc="true"
            >
              <template #item.item-disabled="{ item }">
                <span class=" rounded pa-1">
                  {{ item?.raw?.id }}
                </span>
              </template>
              <template #item.dataInclusao="{ item }">
                <span class="pa-1">
                  {{ item?.raw?.dataInclusao}}
                  <!-- {{ item?.raw?.dataInclusao }} -->
                </span>
              </template>
              <template #item.status="{ item }">
                <span
                  :class="
                    item.raw.status == 'AGENDADO'
                      ? 'bg-green-darken-3 rounded pa-1'
                      : item.raw.status == 'CANCELADO'
                      ? 'bg-red-darken-3 rounded pa-1'
                      : 'bg-orange-darken-3 rounded pa-1'

                  "
                >
                  {{ item.raw.status }}


                </span>
              </template>
              <template #item.actions="{ item }">
                <!-- <dialog-vinculacao
                  :data="item.raw"
                  v-if="item?.raw?.movimentacao != 'CRIADA'"
                /> -->
                <!-- <v-btn dark size="30px" icon="mdi-plus-box-multiple-outline" class="mx-1 text-info rounded" /> -->
                <v-btn
                  dark
                  size="30px"
                  title="Detalhes"
                  icon="mdi-information"
                  class="mx-1 text-info rounded"
                />
                <v-btn
                  v-if="item?.raw?.status == 'AGENDADO'"
                  dark
                  title="Cancelar agendamento"
                  size="30px"
                  icon="mdi-delete"
                  class="mx-1 text-info rounded"
                  @click="cancelarAgendamento(item?.raw?.id)"
                />
                <v-btn
                  v-if="item?.raw?.status == 'AGUARDANDO CONFIRMACAO'"
                  dark
                  title="Cancelar agendamento"
                  size="30px"
                  icon="mdi-delete"
                  class="mx-1 text-info rounded"
                  @click="cancelarAgendamentoAguardar(item?.raw?.id)"
                />
                <!-- <valida-biometria
                  v-if="item?.raw?.tipo === 'RG NACIONAL'"
                  title="Validação biométrica"
                  :data="item.raw"
                /> -->
                <!-- <v-btn dark size="30px" icon="mdi-paperclip" class="mx-1 text-info rounded" /> -->
              </template>
              <template #bottom>
                <div class="d-flex justify-end align-center">
                  <div class="d-flex">
                    <label for="itemsPerPage" class="d-flex align-center mx-2"
                      >Linhas por página</label
                    >
                    <v-select
                      id="itemsPerPage"
                      v-model="pages.itemsPerPage"
                      :items="pages.items"
                      label="Itens por página"
                      persistent-hint
                      return-object
                      single-line
                      @update:model-value="allItems(pages.itemsPerPage)"
                      variant="underlined"
                      class="mx-2"
                    ></v-select>
                    <v-pagination
                      class="d-flex align-center"
                      v-model="pages.page"
                      :length="lengthPage(items.length)"
                      :size="30"
                      :total-visible="3"
                    ></v-pagination>
                  </div>
                </div>
              </template>
            </v-data-table>
          </v-window-item>
          <v-window-item value="two">
            <v-text-field
              v-model="search"
              variant="underlined"
              append-icon="mdi-magnify"
              label="Buscar"
              single-line
              hide-details
              hover
              class="w-450"
            ></v-text-field>
            <v-data-table
              :headers="headers"
              :items="itemsEnviados"
              :search="search"
              :items-per-page="pages.itemsPerPage"
              :page="pages.page"
            >
              <template #item.emissao="{ item }">
                <span class="bg-green-darken-3 rounded pa-1">
                  {{ item.raw.emissao }}
                </span>
              </template>
              <template #item.dataInclusao="{ item }">
                <span class="pa-1">
                  {{ formatarDataHora(item?.raw?.dataInclusao) }}
                </span>
              </template>
              <template #item.actions="{ item }">
                <!-- <dialog-vinculacao :data="item.raw" :solicitacao="item?.raw?.solicitacao" /> -->
                <v-btn
                  dark
                  size="30px"
                  title="Detalhes"
                  icon="mdi-information"
                  class="text-info rounded"
                />
                <!-- <v-btn dark size="30px" icon="mdi-send" class="mx-1 text-info rounded" /> -->
                <!-- <valida-biometria v-if="item?.raw?.tipo === 'RG Nacional'" :data="item.raw" /> -->
              </template>
              <template #bottom>
                <div class="d-flex justify-end align-center">
                  <div class="d-flex">
                    <label for="itemsPerPage" class="d-flex align-center mx-2"
                      >Linhas por página</label
                    >
                    <v-select
                      id="itemsPerPage"
                      v-model="pages.itemsPerPage"
                      :items="pages.items"
                      label="Itens por página"
                      persistent-hint
                      return-object
                      single-line
                      @update:model-value="allItems(pages.itemsPerPage)"
                      variant="underlined"
                      class="mx-2"
                    ></v-select>
                    <v-pagination
                      class="d-flex align-center"
                      v-model="pages.page"
                      :length="lengthPage(itemsEnviados.length)"
                      :size="30"
                      :total-visible="3"
                    ></v-pagination>
                  </div>
                </div>
              </template>
            </v-data-table>
          </v-window-item>
        </v-window>
      </v-card-text>
    </v-card>
  </v-col>
</template>
