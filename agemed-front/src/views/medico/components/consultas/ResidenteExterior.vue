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
    <v-col cols="6">
      <v-text-field
        v-model="data.codPaisResidExt"
        variant="underlined"
        label="Código do país:"
      ></v-text-field>
    </v-col>
    <v-col cols="6">
      <v-text-field
        v-model="data.nomePaisResidExt"
        variant="underlined"
        label="Nome do país:"
      ></v-text-field>
    </v-col>
  </v-row>
</template>