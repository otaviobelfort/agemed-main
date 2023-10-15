<!-- <script setup>
import { ref, watch } from "vue";
import { Toast } from "@/utils/services";
import router from "@/router/index1";
import PessoaService from "@/service/PessoaService";
import SolicitacaoService from "@/service/SolicitacaoService";
import TabelaResultadoConsultaPessoa from "./TabelaResultadoConsultaPessoa.vue";
import SnackBar from "@/components/SnackBar.vue";

let ativarSnackbar = ref(false);
const dialog = ref(false);
let cpf = ref("22222222222");
let rg = ref("1233334");
let cidadaoVincular = ref([]);
let retornouPessoa = ref(true);

let tipoSnackBar = ref("");
let mensagemSnackBar = ref("");

const props = defineProps({
  data: {
    type: Object,
    required: true,
  },
  solicitacao: {
    type: Object,
    required: true,
  },
});

watch(props, () => {
  if (props.visible) dialog.value = true;
});

const resultadoBuscaPessoa = ref([]);

let tipoConsultaPessoa = ref("Nome completo e data nascimento");
const buscarPessoa = () => {
  switch (tipoConsultaPessoa.value) {
    case "Nome completo e data nascimento":
      buscarPessoaNomeDataNascimento();
      break;
    case "CPF":
      buscarPessoaCPF();
      break;
    case "RG":
      buscarPessoaRG();
      break;
    default:
      console.log("nada");
  }
};

let dataNascimento = ref("1999-02-21");
let nomeCompleto = ref("Anna Olivia Belfort");
const buscarPessoaNomeDataNascimento = () => {
  showLoading();
  PessoaService.consultarPessoaDataNascimentoNomeCompleto(
    dataNascimento.value,
    nomeCompleto.value
  )
    .then((response) => {
      if (response.data.length == 0) {
        resultadoBuscaPessoa.value = {};
        resultadoBuscaPessoa.value = [];
        retornouPessoa.value = false;
        Toast.fire("Pessoa não encontrada!!", "error");
        showSnackBar("Falha ao consultar!!", "error");
      } else {
        resultadoBuscaPessoa.value = response.data;
      }

      hideLoading();
    })
    .catch((e) => {
      resultadoBuscaPessoa.value = {};
      resultadoBuscaPessoa.value = [];
      retornouPessoa.value = false;
      console.log(e);
      showSnackBar("Falha ao consultar!!", "error");
      Toast.fire("Falha ao consultar!!", "error");
      hideLoading();
    });
};
const buscarPessoaCPF = () => {
  showLoading();

  let localCpf = formatarCpfRg(cpf.value);

  PessoaService.consultarPessoaCpf(localCpf)
    .then((response) => {
      resultadoBuscaPessoa.value = response.data;
      console.log(resultadoBuscaPessoa.value);
      hideLoading();
    })
    .catch((e) => {
      retornouPessoa.value = false;
      resultadoBuscaPessoa.value = {};
      resultadoBuscaPessoa.value = [];
      showSnackBar("Falha ao consultar!!", "error");
      Toast.fire("Falha ao consultar!!", "info");
      hideLoading();
    });
};
const buscarPessoaRG = () => {
  showLoading();

  let localRg = formatarCpfRg(rg.value);

  PessoaService.consultarPessoaRg(localRg)
    .then((response) => {
      resultadoBuscaPessoa.value = response.data;
      hideLoading();
    })
    .catch((e) => {
      resultadoBuscaPessoa.value = {};
      resultadoBuscaPessoa.value = [];
      retornouPessoa.value = false;
      showSnackBar("Falha ao consultar!!", "error");
      Toast.fire("Falha ao consultar!!", "error");
      hideLoading();
    });
};

const vincularPessoa = () => {
  let solicitacaoIdPessoaDTO = {
    solicitacao: props.data.solicitacao,
    pessoa: resultadoBuscaPessoa.value.id,
  };
  showLoading();
  SolicitacaoService.vincularPessoa(solicitacaoIdPessoaDTO)
    .then((response) => {
      showSnackBar("Sucesso ao vincular!!", "success");
      Toast.fire("Sucesso ao vincular!!", "success");
      loading.value = false;
    })
    .catch((e) => {
      console.log(e);
      showSnackBar("Falha ao vincular!!", "error");
      Toast.fire("Falha ao vincular!!", "error");
      loading.value = false;
    });
};

let loading = ref(false);
const showLoading = () => {
  loading.value = true;
};
const hideLoading = () => {
  loading.value = false;
};

const formatarCpfRg = (cpfRg) => {
  return cpfRg.replace(/\D/g, "");
};

const cadastrarPessoa = () => {
  router.push({ path: "/rg/cadastro" });
};

const showSnackBar = (mensagemSnackBarParam, tipoSnackBarParam) => {
  tipoSnackBar.value = tipoSnackBarParam;
  mensagemSnackBar.value = mensagemSnackBarParam;
  ativarSnackbar.value = !ativarSnackbar.value;
};

const mostraResultado = (resultado) => {
  cidadaoVincular.value = resultado;
};

const closeDialog = () => {
  dialog.value = false;
  resultadoBuscaPessoa.value = [];
  retornouPessoa.value = true;
};
</script>
<template>
  <v-dialog
    v-model="dialog"
    persistent
    max-width="840"
    transition="dialog-top-transition"
  >
    <template #activator>
      <v-btn
        size="30px"
        title="Vincular pessoa"
        @click="dialog = true"
        icon="mdi-account-plus"
        class="mx-1 text-info rounded"
      >
      </v-btn>
    </template>
    <v-card>
      <SnackBar
        :tipoSnackBar="tipoSnackBar"
        :mensagem="mensagemSnackBar"
        :ativarSnackbar="ativarSnackbar"
      />
      <v-card-title class="headline text-center">Vincular pessoa</v-card-title>
      <v-divider />
      <v-card-text>
        <v-col cols="12">
          <v-form ref="form">
            <v-row>
              <v-col cols="6">
                <v-select
                  v-model="tipoConsultaPessoa"
                  :items="['Nome completo e data nascimento', 'CPF', 'RG']"
                  label="Tipo consulta"
                  variant="underlined"
                >
                </v-select>
              </v-col>
              <v-col cols="6">
                <v-text-field
                  v-if="tipoConsultaPessoa == 'CPF'"
                  v-model="cpf"
                  variant="underlined"
                  label="CPF"
                  v-mask="'###.###.###-##'"
                ></v-text-field>

                <div v-if="tipoConsultaPessoa == 'RG'">
                  <v-text-field
                    variant="underlined"
                    label="RG"
                    v-model="rg"
                    v-mask="'##.###.###-#'"
                  ></v-text-field>
                </div>
              </v-col>
            </v-row>

            <v-row
              v-if="tipoConsultaPessoa == 'Nome completo e data nascimento'"
            >
              <v-col cols="6">
                <v-text-field
                  variant="underlined"
                  label="Nome completo"
                  v-model="nomeCompleto"
                ></v-text-field>
              </v-col>
              <v-col cols="6">
                <v-text-field
                  type="date"
                  variant="underlined"
                  label="Data de nascimento"
                  v-model="dataNascimento"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row class="d-flex flex-row-reverse">
              <v-btn
                v-if="cidadaoVincular.length != 0"
                class="mr-2"
                color="light-blue-darken-4"
                @click="vincularPessoa"
              >
                <v-icon class="mr-1" icon="mdi-account-plus" /> Vincular
              </v-btn>

              <v-btn
                v-if="!retornouPessoa"
                class="mr-2"
                color="light-blue-darken-4"
                @click="cadastrarPessoa"
              >
                <v-icon class="mr-1" icon="mdi-card-account-details" />
                Cadastrar
              </v-btn>
            </v-row>
          </v-form>
        </v-col>
        <div class="text-center" v-show="loading">
          <v-progress-circular
            indeterminate
            color="primary"
          ></v-progress-circular>
        </div>
        <TabelaResultadoConsultaPessoa
          v-show="!loading"
          @resultado-consulta="mostraResultado"
          :finished="resultadoBuscaPessoa"
        />
      </v-card-text>
      <v-divider />
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          variant="elevated"
          color="grey darken-2"
          text
          @click="closeDialog()"
          >Sair</v-btn
        >
        <v-btn
          variant="elevated"
          color="light-blue-darken-4"
          text
          @click="buscarPessoa"
          >Consultar</v-btn
        >
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>
<style>
</style> -->
