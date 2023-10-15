<script setup>
import { ref } from "vue";

const props = defineProps({
  data: {
    type: Object,
    required: true,
  },
});
// const disabled = ref(true)
</script>
<template>
  <v-row class="form-label-resize">
    <v-col cols="4">
      <v-text-field
        v-model="data.cepDomicilio"
        variant="underlined"
        label="CEP:"
      ></v-text-field>
    </v-col>
    <v-col cols="4">
      <v-text-field
        v-model="data.tipoLogradouro"
        variant="underlined"
        label="Tipo logradouro:"
      ></v-text-field>
    </v-col>
    <v-col cols="4">
      <v-text-field
        v-model="data.logradouro"
        variant="underlined"
        label="Logradouro:"
      ></v-text-field>
    </v-col>
    <v-col cols="4">
      <v-text-field
        v-model="data.numeroDomicilio"
        variant="underlined"
        label="Número:"
      ></v-text-field>
    </v-col>
    <v-col cols="4">
      <v-text-field
        v-model="data.compDomicilio"
        variant="underlined"
        label="Complemento:"
      ></v-text-field>
    </v-col>
    <v-col cols="4">
      <v-text-field
        v-model="data.bairroDomicilio"
        variant="underlined"
        label="Bairro:"
      ></v-text-field>
    </v-col>
  </v-row>
</template>