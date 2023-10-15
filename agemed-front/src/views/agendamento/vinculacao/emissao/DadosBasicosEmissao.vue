<template>
  <v-card class="mb-2">
    <v-form ref="form">
      <v-container>
        <v-col cols="12">
          <v-row>
            <v-col class="pb-0" cols="6as">
              <v-text-field
                label="Nome"
                variant="underlined"
                v-model="dadosPessoais.nome"
                :rules="required"
              ></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="6">
              <v-text-field
                type="date"
                label="Data de nascimento"
                variant="underlined"
                v-model="dadosPessoais.dataNascimento"
                :rules="required"
              ></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="6">
              <v-autocomplete
                :items="listEstadoCivil"
                :custom-filter="customFilter"
                v-model="dadosPessoais.estadoCivil"
                item-title="label"
                item-value="id"
                label="Especialidade"
                variant="underlined"
                :rules="required"
              >
              </v-autocomplete>
            </v-col>
            <v-col class="pb-0" cols="6">
              <v-autocomplete
                :items="listEstadoCivil"
                :custom-filter="customFilter"
                v-model="dadosPessoais.estadoCivil"
                item-title="label"
                item-value="id"
                label="Sexo"
                variant="underlined"
                :rules="required"
              >
              </v-autocomplete>
            </v-col>
            <v-col class="pb-0" cols="6">
              <v-autocomplete
              :items="listEstadoCivil"
                :custom-filter="customFilter"
                v-model="dadosPessoais.estadoCivil"
                item-title="label"
                item-value="id"
                label="Tipo sanguíneo"
                variant="underlined"
                :rules="required"
              >
              </v-autocomplete>
            </v-col>
          </v-row>
        </v-col>
      </v-container>
    </v-form>
  </v-card>
</template>

<script setup>
import { onMounted, ref, reactive, watch, onBeforeUnmount } from "vue";
import { listEstadoCivil } from "@/assets/mock/estadual/listEstadoCivil";
import ConsultaListasService from "@/service/ConsultaListasService";
import { Toast } from "@/utils/services";

import { rules } from "@/utils/validation";
const { email, vCpf, required } = rules;

const emit = defineEmits(["avancarStepEdicaoDadosBasicos"]);
let form = ref(null);
let listProfissao = ref([]);
let ufNascimento = ref();
let dadosPessoais = ref({
  filiacao: [
    {
      nome: null,
      tipo: 0,
    },
  ],
  doadorOrgaos: "false",
});

const props = defineProps({
  validarStepEdicaoDadosBasicos: {
    type: Boolean,
    required: true,
  },
});

watch(
  () => props.validarStepEdicaoDadosBasicos,
  (first, second) => {
    (async () => {
      if (await validar()) {
        emit("avancarStepEdicaoDadosBasicos");
      }
    })();
  }
);

const validar = async () => {
  const { valid } = await form?.value.validate();
  return valid;
};




onMounted(() => {
  if (JSON.parse(localStorage.getItem("armazenarDadosEdicao")) != null) {
    dadosPessoais.value = JSON.parse(
      localStorage.getItem("armazenarDadosEdicao")
    );
    dadosPessoais.value.doadorOrgaos =
      dadosPessoais.value.doadorOrgaos == true ? "true" : "false";

    ufNascimento.value = dadosPessoais.value.ufNascimento;
  }

  ConsultaListasService.buscarListaPorNomeTabela("estado")
    .then((response) => {
      listEstados.value = response.data;
    })
    .catch((e) => alert("ERRO: " + e.message));

  ConsultaListasService.buscarListaPorNomeTabela("pais")
    .then((response) => {
      listPaises.value = response.data;
    })
    .catch((e) => alert("ERRO: " + e.message));

  ConsultaListasService.buscarListaPorNomeTabela("profissao")
    .then((response) => {
      listProfissao.value = response.data;
    })
    .catch((e) => alert("ERRO: " + e.message));
});

onBeforeUnmount(() => {
  dadosPessoais.value.doadorOrgaos =
    dadosPessoais.value.doadorOrgaos == "true" ? true : false;

  dadosPessoais.value.ufNascimento = ufNascimento.value;

  localStorage.setItem(
    "armazenarDadosEdicao",
    JSON.stringify(dadosPessoais.value)
  );
});

const isSelectEstadoNull = () => {
  console.log("Erro ao cadastrar!!");
  Toast.fire("Erro ao cadastrar!!", "error");
};

const filterData = (item, queryText) => {
  return (
    item.id.toLowerCase().includes(queryText.toLowerCase()) ||
    item.label.toLowerCase().includes(queryText.toLowerCase())
  );
};
</script>

<style>
.v-enter-active,
.v-leave-active {
  transition: all 0.3s;
}

.v-enter,
.v-leave-to {
  opacity: 0;
  transform: translate3d(-20px, 0, 0);
}
</style>
