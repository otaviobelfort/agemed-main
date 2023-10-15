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
      <v-text-field
        v-model="data.contato.ddd"
        variant="underlined"
        label="Número do DDD:"
      ></v-text-field>
    </v-col>
    <v-col cols="4">
      <v-text-field
        v-model="data.contato.nroTel"
        variant="underlined"
        label="Número telefone fixo:"
      ></v-text-field>
    </v-col>
    <v-col cols="4">
      <v-text-field
        v-model="data.contato.nroCel"
        variant="underlined"
        label="Número do celular:"
      ></v-text-field>
    </v-col>
  </v-row>
</template>