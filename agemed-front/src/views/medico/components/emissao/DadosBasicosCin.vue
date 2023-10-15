<script setup>
import { onMounted, ref, reactive, watch } from "vue";
import { listSexo } from "@/assets/mock/estadual/listSexo";
import { listEstadoCivil } from "@/assets/mock/estadual/listEstadoCivil";
import { listCodigoNacionalidade } from "@/assets/mock/estadual/listCodigoNacionalidade";
import { listTiposCabelos } from "@/assets/mock/estadual/listTiposCabelos";
import { listTipoNacionalidade } from "@/assets/mock/estadual/listTipoNacionalidade";
import ConsultaListasService from "@/service/ConsultaListasService";
import ModalCropperImage from "./components/ModalCropperImage.vue";

let listEstados = ref([]);
let listPaises = ref([]);
let listProfissao = ref([]);
let listMunicipio = ref([]);
let estado = ref();
let municipio = ref();
const dialog = ref(false);
const dadosBasicos = ref(null);

const props = defineProps({
  data: {
    type: Object,
    required: true,
  },
});

const config = {
  dateFormat: "d/m/Y",
}

let dataNascimento = new Date(props?.data?.dataNascimento).toLocaleDateString("pt-br");
dadosBasicos.value = {
  nome: props?.data?.nomeContribuinte,
  nomeSocial: props?.data?.nomeSocial,
  dataNascimento: dataNascimento,
  cpf: props?.data?.cpfContribuinte,
  sexo: null,
  indMaeRegistro: !props?.data?.nomeMae ? 0 : 1,
}

console.log(props.data)
console.log(dadosBasicos.value)

// watch(estado, (newValue, oldValue) => {
//   ConsultaListasService.buscarCidadesPorIdEstado(newValue)
//     .then((response) => {
//       listMunicipio.value = response.data;
//     })
//     .catch((e) => alert("ERRO: " + e.message));
// });
/* const listEstadoCivil = ref([
  {
    id: 1,
    label: "SOLTEIRO",
  },
  {
    id: 2,
    label: "CASADO",
  },
  {
    id: 3,
    label: "DIVORCIADO",
  },
  {
    id: 4,
    label: "VIUVO",
  },
  {
    id: 5,
    label: "SEPARADO JUDICIALMENTE",
  },
  {
    id: 6,
    label: "DESQUITADO",
  },
  {
    id: 7,
    label: "CUNCUBINADO",
  },
]);

let estadoCivil = ref({}); */

onMounted(() => {
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

const filterData = (item, queryText) => {
  return (
    item.id.toLowerCase().includes(queryText.toLowerCase()) ||
    item.label.toLowerCase().includes(queryText.toLowerCase())
  );
};

const getImageCin = (image) => {
  let imageFormated = image.split(",");
  if (imageFormated[0] === 'data:image/png;base64') return image
  else return `data:image/png;base64,${image}`
}
</script>

<template>
  <v-card class="mb-2">
    <v-dialog
      v-model="dialog"
      persistent
      max-width="540"
      transition="dialog-top-transition"
    >
      <!-- <template #activator>
        <v-btn size="38px" @click="dialog = true" icon="mdi-cloud-upload" class="text-info rounded">
        </v-btn>
      </template> -->
      <v-card>
        <v-card-title class="headline text-center">Inserir Imagem</v-card-title>
        <v-divider />
        <modal-cropper-image
          :data="data?.listaCin[0]?.imagemCin"
          @dialog="() => (dialog = false)"
        />
      </v-card>
    </v-dialog>
    <v-form>
      <v-container>
        <v-row>
          <v-col cols="3" class="d-flex align-center justify-center">
            <v-row>
              <v-col cols="12">
                <v-col cols="12" class="d-flex justify-center">
                  <v-avatar rounded class="avatar-emissao">
                    <v-img
                      v-if="data?.listaCin[0]?.imagemCin"
                      :src="getImageCin(data.listaCin[0].imagemCin.foto)"
                      alt="Imagem"
                    ></v-img>
                    <v-icon v-else icon="mdi-account" size="150"></v-icon>
                    <!-- <v-icon icon="mdi-account" size="150"></v-icon> -->
                  </v-avatar>
                </v-col>
                <v-col cols="12" class="d-flex justify-center pa-0">
                  <v-btn
                    variant="elevated"
                    color="indigo"
                    size="38"
                    @click="dialog = true"
                  >
                    <v-icon icon="mdi-cloud-upload"></v-icon>
                  </v-btn>
                  <v-btn
                    variant="elevated"
                    color="blue-darken-3"
                    size="38"
                    class="mx-1"
                  >
                    <v-icon icon="mdi-camera-front-variant"></v-icon>
                  </v-btn>
                  <v-btn variant="elevated" color="red-darken-3" size="38">
                    <v-icon icon="mdi-cancel"></v-icon>
                  </v-btn>
                </v-col>
              </v-col>
            </v-row>
          </v-col>
          <v-col cols="9">
            <v-row>
              <v-col class="pb-0" cols="6">
                <v-text-field v-model="dadosBasicos.nome" label="Nome" variant="underlined"></v-text-field>
              </v-col>
              <v-col class="pb-0" cols="3">
                <v-text-field
                  v-model="dadosBasicos.nomeSocial"
                  label="Nome social"
                  variant="underlined"
                ></v-text-field>
              </v-col>
              <v-col class="pb-0 form-pickr" cols="3">
                <div
                  class="v-input v-input--horizontal v-input--density-default v-input--dirty v-text-field v-text-field--flush-details">

                  <div class="v-input__control">
                    <div class="v-field v-field--active v-field--dirty v-field--variant-underlined v-theme--customLight"
                      role="textbox">
                      <div class="v-field__overlay"></div>
                      <div class="v-field__loader">
                        <div class="v-progress-linear v-theme--customLight" role="progressbar" aria-hidden="true"
                          aria-valuemin="0" aria-valuemax="100"
                          style="top: 0px; height: 0px; --v-progress-linear-height:2px; left: 50%; transform: translateX(-50%);">

                          <div class="v-progress-linear__background" style="width: 100%;"></div>
                          <div class="v-progress-linear__indeterminate">
                            <div class="v-progress-linear__indeterminate long"></div>
                            <div class="v-progress-linear__indeterminate short"></div>
                          </div>
                        </div>
                      </div>
                      <!-- FLATPICKR AQUI -->
                      <div class="v-field__field" data-no-activator="">
                        <label class="v-label v-field-label" for="nascimento">Data de Nascimento</label>
                        <flat-pickr id="nascimento" v-model="dadosBasicos.dataNascimento" :config="config"
                          class="v-field__input" aria-describedby="nascimento-messages"></flat-pickr>

                      </div>
                      <div class="v-field__outline">
                        <label class="v-label v-field-label v-field-label--floating" aria-hidden="true"
                          for="nascimento">Data de Nascimento</label>
                      </div>
                    </div>
                  </div>
                  <div class="v-input__details">
                    <div class="v-messages" role="alert" aria-live="polite" id="nascimento-messages"></div>
                  </div>
                </div>
              </v-col>
              <v-col class="pb-0" cols="4">
                <v-text-field v-model="dadosBasicos.cpf" label="CPF" variant="underlined"
                  v-mask="'###.###.###-##'"></v-text-field>
              </v-col>
              <v-col class="pb-0" cols="4">
                <v-autocomplete v-model="dadosBasicos.sexo" :items="listSexo" item-title="label" item-value="id"
                  label="Sexo" variant="underlined">
                </v-autocomplete>
              </v-col>
              <v-col class="pb-0" cols="4">
                <!-- Ajustar alinhamento -->
                <label>Mãe no registro?</label>
                <v-radio-group v-model="dadosBasicos.indMaeRegistro" inline>
                  <v-radio label="Sim" color="red" :value="1"></v-radio>
                  <v-radio label="Não" color="red" :value="0"></v-radio>
                </v-radio-group>
              </v-col>
              <v-col class="pb-0" cols="12">
                <v-textarea v-model="dadosBasicos.obsEmissaoIdentidade" label="Observações" variant="underlined"
                  rows="2"></v-textarea>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
  </v-card>
</template>

<style>
:root {
  --tam-avatar: 180px;
}

.avatar-emissao {
  border: 1px solid #c5c5c5;
  height: var(--tam-avatar) !important;
  width: calc(var(--tam-avatar) * 0.75) !important;
}
</style>