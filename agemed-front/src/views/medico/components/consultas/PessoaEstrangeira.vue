<script setup>
const props = defineProps({
  data: {
    type: Object,
    required: true,
  },
});
</script>
<template>
  <v-row class="form-label-resize">
    <v-col cols="4">
      <v-radio-group
        v-model="data.indEstrangeiro"
        label="Estrangeiro"
        class="d-flex"
      >
        <v-radio color="primary" label="Sim" value="Sim"></v-radio>
        <v-radio color="red" label="Não" value="Não"></v-radio>
      </v-radio-group>
    </v-col>
    <v-col cols="4">
      <v-text-field
        v-model="data.codPaisResidExt"
        variant="underlined"
        label="Código do país:"
      ></v-text-field>
    </v-col>
    <v-col cols="4">
      <v-text-field
        v-model="data.nomePaisResidExt"
        variant="underlined"
        label="Nome do país:"
      ></v-text-field>
    </v-col>
  </v-row>
</template>