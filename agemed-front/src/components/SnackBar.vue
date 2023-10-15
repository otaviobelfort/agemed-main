<template>
  <div class="text-center ma-2">
    <v-snackbar top v-model="snackbar" timeout="5000" location="top right" :color="tipoSnackBar" :auto-height="true">
      {{ text }}

      <template v-slot:actions>
        <v-btn color="pink" variant="text" @click="snackbar = false">
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </div>
</template>

<script setup>
import { ref, watch } from "vue";
let snackbar = ref(false);
let text = ref("");
let tipoSnackBar = ref("");

const props = defineProps({
  ativarSnackbar: {
    type: Boolean,
    required: true,
  },
  mensagem: {
    type: String,
    required: true,
  },
  tipoSnackBar: {
    type: String,
    required: true,
  },
});



watch(
  () => props.ativarSnackbar,
  (first, second) => {
    console.log(first);
    text.value = props.mensagem;
    tipoSnackBar.value = props.tipoSnackBar;
    snackbar.value = true;
  }
);




</script>

<style>
</style>