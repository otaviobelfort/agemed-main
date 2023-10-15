<template>
  <div>
    <stepper-component :step="step" :steps="steps" :label_steps="label_steps">
      <template #step-1>
        <dados-basicos-cin :data="data" />
      </template>
      <template #step-2>
        <endereco-cin :data="data" />
      </template>
      <template #step-3>
        <listas-cin :data="data" />
      </template>
      <template #btn-finish>
        <v-btn type="submit" variant="elevated" color="green-darken-2" @click="mostrarToast">
          finalizar
        </v-btn>
      </template>
      <template #btn-back>
        <v-btn @click="step--" :disabled="step == 1"> voltar </v-btn>
      </template>
      <template #btn-next>
        <v-btn @click="step++" :disabled="step == steps">
          próximo
        </v-btn>
      </template>
    </stepper-component>
  </div>
</template>

<script setup>
import StepperComponent from "@/components/StepperComponent.vue";
import DadosBasicosCin from "./emissao/DadosBasicosCin.vue";
import EnderecoCin from "./emissao/EnderecoCin.vue";
import ListasCin from "./emissao/ListasCin.vue";
import { Toast } from "@/utils/services";
import { ref } from "vue";
/* import DadosAdicionaisEmissao from "./emissao/DadosAdicionaisEmissao.vue";
 */

const props = defineProps({
  data: {
    type: Object,
    required: true
  }
})
const labels_estadual = [
  "Dados básicos", "Endereço", "Listas"
]
const step = ref(1);
const steps = labels_estadual?.length
const label_steps = labels_estadual

</script>

<style></style>