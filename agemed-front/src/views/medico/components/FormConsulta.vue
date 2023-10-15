<script setup>
import { ref } from "vue";
import DadosBasicos from "./consultas/DadosBasicos.vue";
import DomicilioFiscal from "./consultas/DomicilioFiscal.vue";
import PessoaEstrangeira from "./consultas/PessoaEstrangeira.vue";
import ResidenteExterior from "./consultas/ResidenteExterior.vue";
import Telefone from "./consultas/Telefone.vue";
import Atualizacao from "./consultas/Atualizacao.vue";
import DadosNacional from "./consultas/DadosNacional.vue";
import ListaEmail from "./consultas/ListaEmail.vue";
import ListaTelefone from "./consultas/ListaTelefone.vue";
import ListaFiliacao from "./consultas/ListaFiliacao.vue";
import OutrasCarteiras from "./consultas/OutrasCarteiras.vue";

const props = defineProps({
  data: {
    type: Object,
    required: true,
  },
});

const dados = ref({
  basicos: true,
  domicilioFiscal: true,
  pessoaEstrangeira: true,
  residenteExterior: true,
  telefone: true,
  atualizacao: true,
  nacional: true,
  listaEmail: true,
  listaTelefone: true,
  listaFiliacao: true,
  outrasCarteiras: true,
});

const getDadosBasicos = (data) => {
  let not = "SEM REGISTRO";
  let dadosBasicos = {
    nome: data?.nomeContribuinte ?? not,
    nasc: data?.dataNascimento ?? not,
    cpf: data?.cpfContribuinte ?? not,
    inscricao: data?.dataInscricao ?? not,
    sitCad: data?.situacaoCadastro?.descSitCad ?? not,
    dataSitCad: data?.situacaoCadastro?.dataSitCad ?? not,
    nomeSocial: data?.nomeSocial ?? not,
    mae: data?.nomeMae ?? not,
    obito: data?.anoObito ?? not,
    avatarImg: data?.listaCin[0]?.imagemCin
      ? `data:image/png;base64,${data?.listaCin[0]?.imagemCin?.foto}`
      : not,
  };
  return dadosBasicos;
};
const getDomicilioFiscal = (data) => {
  let not = "SEM REGISTRO";
  let domicilio = {
    cepDomicilio: data?.enderecoCin?.cepDomicilio ?? not,
    tipoLogradouro:
      data?.enderecoCin?.logradouroDomicilio?.split(" ")[0] ?? not,
    logradouro:
      data?.enderecoCin?.logradouroDomicilio?.split(" ").splice(1).join(" ") ??
      not,
    numeroDomicilio: data?.enderecoCin?.numeroDomicilio ?? not,
    compDomicilio: data?.enderecoCin?.compDomicilio ?? not,
    bairroDomicilio: data?.enderecoCin?.bairroDomicilio ?? not,
  };
  return domicilio;
};
const getPessoaEstrangeira = (data) => {
  let not = "SEM REGISTRO";
  let pessoaEstrangeira = {
    indEstrangeiro: data?.residenciaExterior?.indEstrangeiro ?? not,
    codPaisResidExt: data?.residenciaExterior?.codPaisResidExt ?? not,
    nomePaisResidExt: data?.residenciaExterior?.nomePaisResidExt ?? not,
  };
  return pessoaEstrangeira;
};
const getData = (data) => {
  let not = "SEM REGISTRO";
  let dados = data ?? not;
  return dados;
};
const position = ref(0);
const ItemListaCinPosition = (data) => {
  position.value = data;
};
</script>
<template>
  <!-- receber o evento "get-item-listaCin"  -->
  <v-row>
    <v-col cols="6">
      <!-- Dados Básicos -->
      <v-col cols="12">
        <v-expansion-panels>
          <v-expansion-panel title="Dados básicos">
            <v-expansion-panel-text>
              <dados-basicos :dados-basicos="getDadosBasicos(data)" />
            </v-expansion-panel-text>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-col>
      <!-- Endereço Domicílio Fiscal -->
      <v-col cols="12">
        <v-expansion-panels>
          <v-expansion-panel title="Endereço domicílio fiscal">
            <v-expansion-panel-text>
              <domicilio-fiscal
                :data="getDomicilioFiscal(data.listaCin[position])"
              />
            </v-expansion-panel-text>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-col>
      <!-- Pessoa Estrangeira -->
      <v-col cols="12">
        <v-expansion-panels>
          <v-expansion-panel title="Pessoa estrangeira">
            <v-expansion-panel-text>
              <pessoa-estrangeira :data="getPessoaEstrangeira(data)" />
            </v-expansion-panel-text>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-col>
      <!-- Residente no Exterior -->
      <v-col cols="12">
        <v-expansion-panels>
          <v-expansion-panel title="Residente no exterior">
            <v-expansion-panel-text>
              <residente-exterior :data="getPessoaEstrangeira(data)" />
            </v-expansion-panel-text>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-col>
      <!-- Telefone -->
      <v-col cols="12">
        <v-expansion-panels>
          <v-expansion-panel title="Telefone">
            <v-expansion-panel-text>
              <telefone :data="getData(data)" />
            </v-expansion-panel-text>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-col>
    </v-col>
    <v-col cols="6">
      <!-- Dados da carteira de identidade nacional -->
      <v-col cols="12">
        <v-expansion-panels>
          <v-expansion-panel title="Dados da carteira de identidade nacional">
            <v-expansion-panel-text>
              <dados-nacional :data="data.listaCin[position]" />
            </v-expansion-panel-text>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-col>
      <!-- Lista de e-mails -->
      <v-col cols="12">
        <v-expansion-panels>
          <v-expansion-panel title="Lista de e-mails">
            <v-expansion-panel-text>
              <lista-email :data="data.listaCin[position]" />
            </v-expansion-panel-text>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-col>

      <!-- Lista de Telefones -->
      <v-col cols="12">
        <v-expansion-panels>
          <v-expansion-panel title="Lista de telefones">
            <v-expansion-panel-text>
              <lista-telefone :data="data.listaCin[position]" />
            </v-expansion-panel-text>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-col>

      <!-- Lista de Filiação -->
      <v-col cols="12">
        <v-expansion-panels>
          <v-expansion-panel title="Lista de filiação">
            <v-expansion-panel-text>
              <lista-filiacao :data="data.listaCin[position]" />
            </v-expansion-panel-text>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-col>

      <!-- Atualização -->
      <v-col cols="12">
        <v-expansion-panels>
          <v-expansion-panel title="Atualização">
            <v-expansion-panel-text>
              <atualizacao :data="getData(data)" />
            </v-expansion-panel-text>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-col>
    </v-col>
    <!-- Outras carteiras cadastradas -->
    <v-col cols="12" class="px-6">
      <v-expansion-panels>
        <v-expansion-panel title="Outras carteiras cadastradas">
          <v-expansion-panel-text>
            <outras-carteiras
              :data="data.listaCin"
              @get-item-lista-cin="ItemListaCinPosition"
            />
          </v-expansion-panel-text>
        </v-expansion-panel>
      </v-expansion-panels>
    </v-col>
  </v-row>
</template>