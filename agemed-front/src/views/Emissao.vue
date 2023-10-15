<script setup>
import { onMounted, ref } from "vue";
import router from "@/router/index1";
import BreadCrumbComponets from "@/components/BreadCrumbComponets.vue";
import { rules } from "@/utils/validation";
import { apiCin, appLoading, Toast } from "@/utils/services";
import ConsultaListasService from "@/service/ConsultaListasService";

const via = ref([
  { id: 1, label: "PRIMEIRA VIA" },
  { id: 2, label: "SEGUNDA VIA" },
]);
const tipoDocumento = ref([
  { id: 2, descricao: "CASAMENTO" },
  { id: 7, descricao: "CERTIDAO DE DESQUITE" },
  { id: 4, descricao: "CERTIFICADO DE IGUALDADE DE   DIREITOS E DEVERES" },
  { id: 3, descricao: "CERTIFICADO DE NATURALIZACAO" },
  { id: 6, descricao: "DIVORCIADO" },
  { id: 1, descricao: "NASCIMENTO" },
  { id: 5, descricao: "SEPARACAO" },
  { id: 9, descricao: "CERTIDAO DE OBITO" },
]);
const estados = ref([]);

const tipoGratuidade = ref([
  {
    id: 3,
    label: "BO",
  },
  {
    id: 5,
    label: "ACAO SOCIAL",
  },
  {
    id: 6,
    label: "IDH",
  },
  {
    id: 10,
    label: "LEI 9.009 DE 10/08/2009",
  },
  {
    id: 11,
    label: "HIPOSSUFICIENTES",
  },
  {
    id: 16,
    label: "INTERNOS/COMECAR DE NOVO ",
  },
  {
    id: 17,
    label: "TEA",
  },
  {
    id: 18,
    label: "SINDROME DE DOWN  ",
  },
  {
    id: 20,
    label: "NÃO POSSUI",
  },
]);
const formNacional = ref(null);
const formEstadual = ref(null);
const cpf = ref(null);
const { email, vCpf, required } = rules;

onMounted(() => {
  ConsultaListasService.buscarListaPorNomeTabela("estado")
    .then((response) => {
      estados.value = response.data;
    })
    .catch((e) => console.log(e));
});

const validateNacional = async () => {
  const { valid } = await formNacional?.value.validate();
  getConsultaNacionalCPF();
};
const validateEstadual = async () => {
  const { valid } = await formEstadual?.value.validate();
};

const getConsultaNacionalCPF = () => {
  // cpf = "21358526869";
  appLoading.style.display = "block";
  let body = {
    listadecpf: cpf.value.replace(/[^\d]+/g, ""),
    cpfusuario: "67364519300",
  };
  apiCin("post", "/v1/cin/consulta-cpf", body)
    .then((response) => {
      localStorage.setItem("consultaNacional", JSON.stringify(response.data));
      appLoading.style.display = "none";
      router.replace({ name: "rg-nacional" });
    })
    .catch(() => {
      appLoading.style.display = "none";
      Toast.fire("Houve um erro ao buscar sua agendamento", "error");
    });
};
</script>

<template>
  <bread-crumb-componets :data="$route.name" />
  <v-row>
    <v-col class="d-flex flex-column" cols="12" md="6">
      <v-expansion-panels>
        <v-expansion-panel>
          <v-expansion-panel-title>
            <v-avatar>
              <v-img height="20" src="@/assets/image/bra.svg"> </v-img>
            </v-avatar>
            <h4>RG Nacional</h4>
          </v-expansion-panel-title>
          <v-expansion-panel-text>
            <v-form @submit.prevent="validateNacional" ref="formNacional">
              <div class="d-flex align-center">
                <v-text-field
                  variant="underlined"
                  v-model="cpf"
                  label="CPF"
                  :rules="[...required, ...vCpf]"
                  v-mask="'###.###.###-##'"
                ></v-text-field>
                <v-btn class="mx-2" color="blue-darken-3" type="submit"
                  >Pesquisar</v-btn
                >
              </div>
            </v-form>
          </v-expansion-panel-text>
        </v-expansion-panel>
      </v-expansion-panels>
    </v-col>
    <v-col class="d-flex flex-column" cols="12" md="6">
      <v-expansion-panels>
        <v-expansion-panel>
          <v-expansion-panel-title>
            <v-avatar>
              <v-img height="20" src="@/assets/image/ma.svg"> </v-img>
            </v-avatar>
            <h4>RG Estadual</h4>
          </v-expansion-panel-title>
          <v-expansion-panel-text>
            <v-form @submit.prevent="criarSolicitacao" ref="formEstadual">
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6">
                    <v-autocomplete
                      v-model="solicitacao.tipoVia"
                      :items="via"
                      item-title="label"
                      item-value="id"
                      label="Via"
                      variant="underlined"
                    >
                    </v-autocomplete>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-autocomplete
                      v-if="solicitacao.tipoVia === 2"
                      v-model="solicitacao.fatorGratuidade"
                      :items="tipoGratuidade"
                      item-title="label"
                      item-value="id"
                      label="Tipo gratuidade"
                      variant="underlined"
                    >
                    </v-autocomplete>
                  </v-col>
                  <v-col cols="12" sm="12">
                    <h4> Documento apresentado </h4>
                    <v-divider></v-divider>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-autocomplete
                      v-model="solicitacao.documentoApresentado.tipoCertidao"
                      :items="tipoDocumento"
                      item-title="descricao"
                      item-value="id"
                      label="Tipo de documento"
                      variant="underlined"
                    >
                    </v-autocomplete>
                    <v-text-field
                      v-model="solicitacao.documentoApresentado.numeroCertidao"
                      variant="underlined"
                      label="Número da certidão"
                      :rules="required"
                    ></v-text-field>
                    <v-text-field
                      v-model="solicitacao.documentoApresentado.numeroLivro"
                      variant="underlined"
                      label="Número do livro"
                      :rules="required"
                    ></v-text-field>
                    <v-text-field
                      v-model="
                        solicitacao.documentoApresentado.numeroFolhaLivro
                      "
                      variant="underlined"
                      label="Número da folha do livro"
                      :rules="required"
                    ></v-text-field>
                    <v-text-field
                      v-model="solicitacao.documentoApresentado.varaCivil"
                      variant="underlined"
                      label="Vara civil"
                      :rules="required"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-text-field
                      v-model="solicitacao.documentoApresentado.nomeCidadao"
                      variant="underlined"
                      label="Nome do cidadão"
                      :rules="required"
                    ></v-text-field>
                    <v-text-field
                      v-model="solicitacao.documentoApresentado.nomeCartorio"
                      variant="underlined"
                      label="Nome do cartório"
                      :rules="required"
                    ></v-text-field>
                    <v-text-field
                      variant="underlined"
                      v-model="solicitacao.documentoApresentado.registroCivil"
                      label="Registro civil"
                      :rules="required"
                    ></v-text-field>
                    <v-autocomplete
                      v-model="
                        solicitacao.documentoApresentado.ufMunicipioCartorio
                      "
                      :items="estados"
                      item-title="descricao"
                      item-value="id"
                      label="UF"
                      variant="underlined"
                    >
                    </v-autocomplete>
                    <v-text-field
                      v-model="solicitacao.documentoApresentado.zonaOficio"
                      variant="underlined"
                      label="Zona civil"
                      :rules="required"
                    ></v-text-field>
                    <div class="d-flex flex-row-reverse">
                      <v-btn
                        @click="createPost()"
                        append-icon="mdi-arrow-right"
                        color="blue-darken-3"
                        type="submit"
                        >Avançar</v-btn
                      >
                    </div>
                  </v-col>
                </v-row>
              </v-container>
            </v-form>
          </v-expansion-panel-text>
        </v-expansion-panel>
      </v-expansion-panels>
    </v-col>
  </v-row>
</template>
<script>
//import { onMounted, ref, reactive, watch, onBeforeUnmount } from "vue";
//import {listaTipoGratuidade} from '@/assets/mock/estadual/listaTipoGratuidade.js';

import axios from "axios";
import PessoaService from "@/service/PessoaService";

export default {
  name: "CriarSolicitacao",
  data() {
    return {
      solicitacao: {
        documentoApresentado: {},
      },
    };
  },
  methods: {
    createPost() {
      localStorage.setItem("solicitacao", JSON.stringify(this.solicitacao));

      PessoaService.documentoApresentado(this.solicitacao)
        .then(() => {
          console.log(this.solicitacao);
          console.log("Sucesso ao cadastrar");
          router.push({ name: "solicitacoes" });
        })
        .catch(() => {
          console.log("Erro ao cadastrar");
        });

      /*       axios
        .post('http://172.20.2.4:8082/v1/rg-ma/cadastro-solicitacao', this.solicitacao)
        .then((response) => {
          console.log(this.solicitacao)
          console.log(response)
        })
        .catch((error) => {
          console.log(error)
        }) */
    },
    criarSolicitacao() {
      /* criar solicitaçao */
    },
  },
};
</script>
