<script setup>
import { ref } from "vue";

const props = defineProps({
  data: {
    type: Object,
    required: true,
  },
});

const ultimaData = ref(props.data.dataUltimaAtual.split("T")[0]);
const ultimaHora = ref(props.data.dataUltimaAtual.split("T")[1]);

const dataFormatada = (data) => {
  let date = data.split("T")[0];
  let datef = date.split("-");
  return `${datef[2]}/${datef[1]}/${datef[0]}`;
};

/* const items = ref([
    { name: "Fulano", id: 1 },
    { name: "Fulano2", id: 2 },
]) */
</script>
<template>
  <v-row class="form-label-resize">
    <v-col cols="6">
      <v-text-field
        v-model="ultimaData"
        variant="underlined"
        type="date"
        label="Data última atualização:"
      ></v-text-field>
    </v-col>
    <v-col cols="6">
      <v-text-field
        v-model="ultimaHora"
        variant="underlined"
        type="time"
        label="Hora última atualização:"
      ></v-text-field>
    </v-col>
  </v-row>
  <!-- <v-autocomplete :items="items" item-title="name" label="Select">
        <template #item="{ props, item }">
            <v-list-item v-bind="props" :title="item?.raw?.name"></v-list-item>
        </template>
    </v-autocomplete> -->
</template>