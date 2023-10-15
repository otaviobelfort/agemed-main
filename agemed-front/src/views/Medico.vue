<script setup>
import { ref, watch } from "vue";
import { Toast } from "@/utils/services";
 import router from "@/router/index1";
 import { solicitacao } from "./fields";
 import BreadCrumbComponets from "@/components/BreadCrumbComponets.vue";

/* import PessoaService from "@/service/PessoaService";
import SolicitacaoService from "@/service/SolicitacaoService";
import TabelaResultadoConsultaPessoa from "@TabelaResultadoConsultaPessoa.vue";
*/
import SnackBar from "@/components/SnackBar.vue";



// ---------------------
import DadosBasicosEmissao from "@/views/solicitacao/vinculacao/emissao/DadosBasicosEmissao.vue";
import DocumentosEmissao from "@/views/solicitacao/vinculacao/emissao/DocumentosEmissao.vue";
import EnderecoEmissao from "@/views/solicitacao/vinculacao/emissao/EnderecoEmissao.vue";
import DadosAdicionaisEmissao from "@/views/solicitacao/vinculacao/emissao/DadosAdicionaisEmissao.vue";
import StepperComponent from "@/components/StepperComponent.vue";





let validarStepEdicaoDadosBasicos = ref(false);
let validarStepEdicaoEndereco = ref(false);
const validarFormulario = () => {
  console.log("stepp : "+ step.value);
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
      step.value++;
      break;

    default:
      console.log("nada");
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
const findIndex = (vetor, elemento) => {
  for (let index = 0; index < vetor.length; index++) {
    if (vetor[index].id == elemento.id) {
      return index;
    }
  }
  return null;
};
/* const props = defineProps({
  finished: {
    type: Array,
    required: true,
  },
}); */


// ----------------------


let ativarSnackbar = ref(false);
const dialog = ref(false);
let cpf = ref("");
let rg = ref("");
let cidadaoVincular = ref([]);
let retornouPessoa = ref(true);
/* const items = ref([]);
 */let tipoSnackBar = ref("");
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

let tipoConsultaPessoa = ref();
const cadastrarMedico2 = () => {
  switch (tipoConsultaPessoa.value) {
    case "Nome completo":
      cadastrarMedicoNomeCompleto();
      break;
    case "CPF":
      cadastrarMedicoCPF();
      break;
    case "RG":
      cadastrarMedicoRG();
      break;
    default:
      console.log("nada");
  }
};

let dataNascimento = ref("1999-02-21");
let nomeCompleto = ref("");
const cadastrarMedicoNomeCompleto = () => {
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

const formatarDataHora = (dataHora) => {
  const [data, hora] = dataHora.split(" ");
  const [horas, minutos, segundos] = hora.split(":");
  const [ano, mes, dia] = data.split("-");
  const dataFormatada = `${dia}/${mes}/${ano} ${horas}:${minutos}`;
  return dataFormatada;
};
const cadastrarMedicoCPF = () => {
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
const cadastrarMedicoRG = () => {
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
// novos

const labels_estadual = [
  "Dados básicos",
  "Documentos",
  "Endereço",
  "Contato",
];
let step = ref(1);

const steps = labels_estadual?.length;
const label_steps = labels_estadual;
const items = ref([
    {
        crm: 1234,
        nome: "Fulano da Silva Sauro",
        status: "Ativo",
        especialidade: "Cardiologista",
        dataCadastro: "2023-01-02T08:43:02",
    },
    {
      crm: 1234,
        nome: "Olívia da Silva Sauro",
        status: "Ativo",
        especialidade: "Alérgista",
        dataCadastro: "2023-01-02T08:43:02",
    },
    {
        crm: 1234,
        nome: "Júlia da Silva Sauro",
        status: "Ativo",
        especialidade: "Clínico geral",
        dataCadastro: "2023-01-02T08:43:02",
    }
  ])
const headers = ref([{
        align: 'start',
        key: 'crm',
        sortable: false,
        title: 'CRM',
    },
    { key: 'nome', title: 'Nome' },
    { key: 'especialidade', title: 'Especialidade' },
    { key: 'dataCadastro', title: "Data cadastro" },
    { key: 'status', title: 'Status' },
    { key: 'actions', title: 'Ações' },]);
const emit = defineEmits(["resultadoConsulta"]);
const pages = ref({
  itemsPerPage: 5,
  page: 1,
  pageCount: 1,
  items: [10, 20, 50, 100, "todos"],
});
let dialog2 = ref(false);
const cadastrarMedico = () =>{
  dialog2.value = !dialog2.value;
}

</script>
<template>
  <bread-crumb-componets :data="$route.name" />
  <!-- <v-dialog
    v-model="dialog"
    persistent
    max-width="840"
    transition="dialog-top-transition">

    <template #activator>
      <v-btn
        size="30px"
        title="Vincular pessoa"
        @click="dialog = true"
        icon="mdi-account-plus"
        class="mx-1 text-info rounded"
      >
      </v-btn>
    </template> -->
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
                <DadosBasicosEmissao finished="finalizar" />
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
                <v-btn @click="validarFormulario"
                :disabled="step == steps">
                  próximo
                </v-btn>
              </template>
            </stepper-component>
          </v-card-text>
          <!-- <v-card-actions> </v-card-actions> -->
        </v-card>
      </v-dialog>


      <SnackBar
        :tipoSnackBar="tipoSnackBar"
        :mensagem="mensagemSnackBar"
        :ativarSnackbar="ativarSnackbar"
      />
      <!-- <v-card-title class="headline text-center">Médico</v-card-title>
      <v-divider /> -->
      <v-card-text>
        <v-col cols="12">
          <v-form ref="form">
            <v-row>
              <v-col cols="6">
                <v-select
                  v-model="tipoConsultaPessoa"
                  :items="['Nome completo', 'CPF', 'RG']"
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

              <div v-if="tipoConsultaPessoa == 'Nome completo'">
                <v-text-field
                  variant="underlined"
                  label="Nome completo"
                  v-model="nomeCompleto"
                ></v-text-field>
              </div>
            </v-col>
            </v-row>
            <v-row class="d-flex flex-row-reverse">

              <v-btn
                v-if="retornouPessoa"
                class="mr-2"
                color="light-blue-darken-4"
                @click="cadastrarMedico"
              >
                <v-icon class="mr-1" icon="mdi-card-account-details" />
                Cadastrar médico
              </v-btn>

              <v-btn
              class="mr-2"
                  variant="elevated"
                  color="light-blue-darken-4"
                  text
                  @click="cadastrarMedico"

                  >
                  <v-icon class="mr-1" icon="mdi-magnify"></v-icon>
                    Consultar
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
        <!-- <TabelaResultadoConsultaPessoa
          v-show="!loading"
          @resultado-consulta="mostraResultado"
          :finished="resultadoBuscaPessoa"
        /> -->
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
      <v-divider />
      <v-card-actions>
        <v-spacer></v-spacer>
    <!--         <v-btn
          variant="elevated"
          color="grey darken-2"
          text
          @click="closeDialog()"
          >Sair</v-btn
        > -->

      </v-card-actions>
    </v-card>
<!--   </v-dialog> -->
</template>
<style>
</style>
