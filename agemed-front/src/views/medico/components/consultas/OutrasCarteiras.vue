<script setup>
import { ref } from "vue";
import { mascaraCPF, Toast } from "@/utils/services";

const props = defineProps({
  data: {
    type: Array,
    required: true,
  },
});

const fields = ref([
  { key: "ord", title: "#", align: "text-center" },
  { key: "cpf", title: "CPF", align: "text-center" },
  { key: "protocoloRFB", title: "Protocolo da RFB", align: "center" },
  {
    key: "dataHora",
    title: "Data/hora do Protocolo da RBF",
    align: "center",
  },
  { key: "protocoloOIC", title: "Protocolo OIC", align: "center" },
  { key: "situacao", title: "Situação", align: "center" },
  { key: "biografia", title: "Informação biográfica válida", align: "center" },
  { key: "biometria", title: "Biometria válida", align: "center" },
]);
const itemsPerPage = ref(10);
const page = ref(1);
const search = ref("");
const length = ref(3);

const selectItems = ref([5, 10, 20, 50, 100]);

const getBiometria = (data) => {
  if (!data || data === "") return "SEM REGISTRO";
  else return data;
};

const emit = defineEmits("get-item-listaCin");

const getNewDatas = (event, row) => {
  let position = props.data.indexOf(row.item.raw);
  emit("get-item-lista-cin", position);
  Toast.fire("Dados atualizados na tela de consulta!", "info");
};
</script>
<template>
  <v-row class="form-label-resize">
    <v-row class="d-flex justify-space-between pt-5">
      <v-col cols="6">
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Buscar"
          hide-details
          variant="underlined"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-col cols="12">
      <v-data-table
        :headers="fields"
        :items="data"
        :search="search"
        :items-per-page="itemsPerPage"
        hover
        @click:row="getNewDatas"
      >
        <template #item.ord="{ item, index }">
          {{ index + 1 }}
        </template>

        <template #item.biografia="{ item }">
          {{ item.raw.informacoesBiometricaBiografica.indicadorConsBiografica }}
        </template>

        <template #item.biometria="{ item }">
          {{
            getBiometria(
              item.raw.informacoesBiometricaBiografica.biometriaValida
            )
          }}
        </template>

        <template #item.dataHora="{ item }">
          {{ item.raw.dataHoraProtocoloRFB }}
        </template>

        <template #item.cpf="{ item }">
          {{ mascaraCPF(item.raw.cpf) }}
        </template>
      </v-data-table>
    </v-col>
  </v-row>
</template>