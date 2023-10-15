<template>
  <v-col cols="12">
    <v-card>
      <v-dialog v-model="dialog2" width="70%" persistent>
        <v-card>
          <v-card-title>
            <div class="d-flex flex-row-reverse">
              <v-btn icon @click="fecharSegundoDialog">
                <v-icon>mdi-close</v-icon>
              </v-btn>
            </div>
          </v-card-title>
          <v-card-text>
            <stepper-component
              :step="step"
              :steps="steps"
              :label_steps="label_steps"
            >
              <template #step-1>
                <dados-basicos-emissao
                  @avancarStepEdicaoDadosBasicos="avancarStepEdicaoDadosBasicos"
                  :validarStepEdicaoDadosBasicos="validarStepEdicaoDadosBasicos"
                />
              </template>
              <template #step-2>
                <documentos-emissao />
              </template>
              <template #step-3>
                <endereco-emissao
                  @avancarStepEdicaoDadosBasicos="avancarStepEdicaoDadosBasicos"
                  :validarStepEdicaoEndereco="validarStepEdicaoEndereco"
                />
              </template>
              <template #step-4>
                <dados-adicionais-emissao :finished="finalizar" />
              </template>
              <template #btn-finish>
                <v-btn @click="salvarDadosAdicionaisLocalStorage">
                  Editar
                </v-btn>
              </template>
              <template #btn-back>
                <v-btn @click="step--" :disabled="step == 1"> voltar </v-btn>
              </template>
              <template #btn-next>
                <v-btn @click="validarFormulario" :disabled="step == steps">
                  próximo
                </v-btn>
              </template>
            </stepper-component>
          </v-card-text>
          <v-card-actions> </v-card-actions>
        </v-card>
      </v-dialog>
      <v-card-text>
        <v-data-table
          :headers="headers"
          :items="items"
          :search="search"
          :items-per-page="pages.itemsPerPage"
          :page="pages.page"
        >
          <template #item.check="{ item }">
            <v-checkbox
              :id="'checkbox' + item.raw.id"
              @click="checkVincularPessoa(item.raw)"
              class="mt-5"
            ></v-checkbox>
          </template>
          <template #item.actions="{ item }">
            <v-btn
              dark
              size="30px"
              icon="mdi-account-edit"
              class="mx-1 text-info rounded"
              @click="editarConsultarPessoa(item.raw.id)"
            />
          </template>
          <template #bottom> </template>
        </v-data-table>
      </v-card-text>
    </v-card>
  </v-col>
</template>

<script setup>
import { solicitacao } from "./fields";
import { ref, onMounted, watch, defineEmits } from "vue";
import { Toast } from "@/utils/services";

import PessoaService from "@/service/PessoaService";
import DadosBasicosEmissao from "./emissao/DadosBasicosEmissao.vue";
import DocumentosEmissao from "./emissao/DocumentosEmissao.vue";
import EnderecoEmissao from "./emissao/EnderecoEmissao.vue";
import DadosAdicionaisEmissao from "./emissao/DadosAdicionaisEmissao.vue";
import StepperComponent from "@/components/StepperComponent.vue";
const labels_estadual = [
  "Dados básicos",
  "Documentos",
  "Endereço",
  "Deficiências",
];
let step = ref(1);
const steps = labels_estadual?.length;
const label_steps = labels_estadual;

const headers = ref(solicitacao);
const emit = defineEmits(["resultadoConsulta"]);
const pages = ref({
  itemsPerPage: 5,
  page: 1,
  pageCount: 1,
  items: [10, 20, 50, 100, "todos"],
});
let dialog2 = ref(false);

let validarStepEdicaoDadosBasicos = ref(false);
let validarStepEdicaoEndereco = ref(false);
const validarFormulario = () => {
  switch (step.value) {
    case 1:
      validarStepEdicaoDadosBasicos.value =
        !validarStepEdicaoDadosBasicos.value;
      break;
    case 2:
      step.value++;
      break;
    case 3:
      validarStepEdicaoEndereco.value = !validarStepEdicaoEndereco.value;
      break;

    default:
  }
};

const avancarStepEdicaoDadosBasicos = () => {
  step.value = step.value + 1;
};

const finalizar = ref(false);
const salvarDadosAdicionaisLocalStorage = () => {
  finalizar.value = !finalizar.value;
};
const fecharSegundoDialog = () => {
  dialog2.value = false;
  localStorage.removeItem("armazenarDadosPessoaisEdicao");
  localStorage.removeItem("armazenarDocumentosEdicao");
};
const props = defineProps({
  finished: {
    type: Array,
    required: true,
  },
});

let items = ref([]);

watch(
  () => props.finished,
  (first, second) => {
    if (first.length == 0) {
      items.value = first;
    } else if (Array.isArray(first)) {
      items.value = first;
      const [ano, mes, dia] = items.value[0].dataNascimento.split("-");
      const dataFormatada = `${dia}/${mes}/${ano}`;
      items.value[0].dataNascimento = dataFormatada;
    } else {
      items.value = [];
      items.value.push(first);
      const [ano, mes, dia] = items.value[0].dataNascimento.split("-");
      const dataFormatada = `${dia}/${mes}/${ano}`;
      items.value[0].dataNascimento = dataFormatada;
    }
  }
);
let pessoas = ref([]);
const findIndex = (vetor, elemento) => {
  for (let index = 0; index < vetor.length; index++) {
    if (vetor[index].id == elemento.id) {
      return index;
    }
  }
  return null;
};

const checkVincularPessoa = (item) => {
  emit("resultadoConsulta", pessoas.value);
  if (document.getElementById("checkbox" + item.id).checked) {
    if (pessoas.value.find((o) => o.id == item.id) == undefined) {
      if (pessoas.value.length > 0) {
        Toast.fire("Selecione apenas uma pessoa!!", "error");
        document.getElementById("checkbox" + item.id).checked = false;
      } else {
        pessoas.value.push(item);
      }
    }
  } else {
    if (pessoas.value.find((o) => o.id == item.id) != undefined) {
      let index = findIndex(pessoas.value, item);
      if (index != null) {
        pessoas.value.splice(index, 1);
      }
    }
  }
};
const editarConsultarPessoa = (idPessoa) => {
  localStorage.setItem("idPessoaEdicao", JSON.stringify(idPessoa));
  PessoaService.consultarPessoaId(idPessoa)
    .then((response) => {
      let dadosCadastro = response.data;

      if (dadosCadastro.sexo == "MASCULINO") {
        dadosCadastro.sexo = 1;
      }

      if (dadosCadastro.tipoCabelos == "LISOS") {
        dadosCadastro.tipoCabelos = 1;
      }

      if (dadosCadastro.corOlhos == "PRETOS") {
        dadosCadastro.corOlhos = 1;
      }

      if (dadosCadastro.cutis == "BRANCA") {
        dadosCadastro.cutis = 1;
      }

      for (let i = 0; i < dadosCadastro.filiacao.length; i++) {
        if (dadosCadastro.filiacao[i].tipo == "MAE") {
          dadosCadastro.filiacao[i].tipo = 0;
        } else if (dadosCadastro.filiacao[i].tipo == "PAI") {
          dadosCadastro.filiacao[i].tipo = 1;
        }
      }

      if (dadosCadastro.estadoCivil == "SOLTEIRO") {
        dadosCadastro.estadoCivil = 1;
      } else if (dadosCadastro.estadoCivil == "CASADO") {
        dadosCadastro.estadoCivil = 2;


      dadosCadastro.doadorOrgaos =
        dadosCadastro.doadorOrgaos == "true" ? true : false;

      localStorage.setItem(
        "armazenarDadosEdicao",
        JSON.stringify(dadosCadastro)
      );

      dialog2.value = !dialog2.value;
    })
    .catch((e) => {
      console.log(e);
    });
};
</script>

<style>
</style>
