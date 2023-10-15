<template>
  <v-card class="mb-2">
    <v-form ref="form">
      <v-container>
        <v-col cols="12">
          <v-row>
            <v-col class="pb-0" cols="3">
              <v-autocomplete
                :items="listEstados"
                :custom-filter="customFilter"
                item-title="descricao"
                item-value="id"
                label="Estado"
                variant="underlined"
                v-model="codUf"
                :rules="required"
              >
              </v-autocomplete>
            </v-col>
            <v-col class="pb-0" cols="3">
              <v-autocomplete
                :items="listMunicipio"
                :custom-filter="customFilter"
                v-model="codMunicipio"
                item-title="descricao"
                item-value="id"
                label="Município"
                variant="underlined"
                :rules="required"
              >
              </v-autocomplete>
            </v-col>
            <v-col class="pb-0" cols="3">
              <v-autocomplete
                :items="listBairros"
                :custom-filter="customFilter"
                v-model="endereco.codBairro"
                item-title="descricao"
                item-value="id"
                label="Bairro"
                variant="underlined"
                :rules="required"
              >
              </v-autocomplete>
            </v-col>
            <v-col class="pb-0" cols="3">
              <v-text-field
                label="CEP"
                variant="underlined"
                v-model="endereco.cep"
                v-mask="'##.###-###'"
                :rules="required"
              ></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="3">
              <v-text-field
                label="Logradouro"
                variant="underlined"
                v-model="endereco.logradouro"
                :rules="required"
              ></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="3">
              <v-text-field
                label="Número"
                variant="underlined"
                v-model="endereco.numero"
                :rules="required"
              ></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="6">
              <v-text-field
                label="Complemento Logradouro"
                variant="underlined"
                v-model="endereco.complemento"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-col>
      </v-container>
    </v-form>
  </v-card>
</template>
<script setup>
import { onMounted, ref, reactive, watch, onBeforeUnmount } from "vue";
import { rules } from "@/utils/validation";

const { email, vCpf, required } = rules;

let codMunicipio = ref();
let codUf = ref();
let endereco = ref({});
let listEstados = ref([{id: 1,
descricao:"MA",}]);
let listMunicipio = ref([{id: 1,
descricao:"MA",}]);
let listBairros = ref([{id: 1,
descricao:"MA",}]);

onMounted(() => {
});


let form=ref(null)

const props = defineProps({
  validarStepEdicaoEndereco: {
    type: Boolean,
    required: true,
  },
});


const validar = async () => {
  const { valid } = await form?.value.validate();
  return valid
};
</script>
