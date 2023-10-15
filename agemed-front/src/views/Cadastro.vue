<script setup>
import {onBeforeMount, ref } from "vue";
import BreadCrumbComponets from "@/components/BreadCrumbComponets.vue";
import { rules } from "@/utils/validation";
import { Toast } from "@/utils/services";

import PessoaService from "@/service/PessoaService";

const listFatorRh = ref([
  { id: 1, label: "A+" },
  { id: 2, label: "A-" },
  { id: 3, label: "B+" },
  { id: 4, label: "B-" },
  { id: 5, label: "AB+" },
  { id: 6, label: "AB-" },
  { id: 7, label: "O+" },
  { id: 8, label: "O-" },
]);

const listPlano = ref([
  { id: 1, label: "INDIVIDUAL" },
  { id: 2, label: "FAMILIAR" },
  { id: 3, label: "COLETIVO" },
]);
const listAlergico = ref([
  { id: 0, label: "Sim" },
  { id: 1, label: "Não" },
]);


const headers = ref([

    { key: 'id', title: 'Código' },
    { key: 'nome', title: 'Nome' },
    { key: 'data_nascimento', title: "Data nascimento" },
    { key: 'cpf', title: 'CPF' },
]);
const headersPaciente = ref([

    { key: 'id', title: 'Código' },
    { key: 'nome', title: 'Nome' },
    { key: 'cpf', title: 'CPF' },
]);

// let items = ref([{
//   id: 1,
//   nome: "Otávio Belfort",
//   data_nascimento: "23/02/1996",
//   cpf:"608.465.893-89"
// }]);
let items = ref([{
}]);
let pacientes = ref([{
}]);
const pages = ref({
  itemsPerPage: 5,
  page: 1,
  pageCount: 1,
  items: [10, 20, 50, 100, "todos"],
});

onBeforeMount(() => {
  PessoaService.listaResumoPessoa().then((response) => {
    //items.value = response.data;
    items.value = response.data
  });

  PessoaService.listaResumoPaciente().then((response) => {
    pacientes.value = response.data
  });
});


const estados = ref([]);

const sexo = ref([
  {
    id: 1,
    label: "MASCULINO",
  },
  {
    id: 2,
    label: "FEMININO",
  }
]);
const formNacional = ref(null);
const formEstadual = ref(null);
const cpf = ref(null);
const { email, vCpf, required } = rules;

// onMounted(() => {
//   ConsultaListasService.buscarListaPorNomeTabela("estado")
//     .then((response) => {
//       estados.value = response.data;
//     })
//     .catch((e) => console.log(e));
// });



const validateNacional = async () => {
  const { valid } = await formNacional?.value.validate();
  getConsultaNacionalCPF();
};
const validateEstadual = async () => {
  const { valid } = await formEstadual?.value.validate();
};

</script>

<template>
  <bread-crumb-componets :data="$route.name" />
  <v-row>
             <!-- PESSOA -->
    <v-col class="d-flex flex-column" cols="12" md="6">
      <v-expansion-panels>
        <v-expansion-panel>
          <v-expansion-panel-title>
            <v-avatar  height="50" icon="mdi-account-details">
              <!-- <v-img height="20" src="@/assets/image/ma.svg"> </v-img> -->
            </v-avatar>
            <h4>Cadastro de paciente</h4>
          </v-expansion-panel-title>
          <v-expansion-panel-text>



            <v-card>
              <v-card-title>
                <v-tabs v-model="tab" bg-color="transparent" color="basil" grow>
                  <v-tab value="one">Cadastrar</v-tab>
                  <v-tab value="two">Lista de pacientes</v-tab>
                </v-tabs>
              </v-card-title>

              <v-card-text>
                <v-window v-model="tab">
                  <v-window-item value="one">
            <v-form @submit.prevent="criarSolicitacao" ref="formEstadual">
              <!-- <v-container> -->
                <v-row>

                  <v-col cols="12" sm="6">
                    <v-autocomplete
                      v-model="paciente.pessoa"
                      :items="items"
                      item-title="nome"
                      item-value="id"
                      label="Pessoa"
                      variant="underlined"
                    >
                    </v-autocomplete>
                    <v-autocomplete
                      v-model="paciente.tipoSanguineo"
                      :items="listFatorRh"
                      item-title="label"
                      item-value="label"
                      label="Tipo sanguíneo"
                      variant="underlined"
                    >
                    </v-autocomplete>
                    <v-autocomplete
                      v-model="paciente.tipoPlano"
                      :items="listPlano"
                      item-title="label"
                      item-value="label"
                      label="Tipo de plano"
                      variant="underlined"
                    >
                    </v-autocomplete>
                    <v-text-field
                     label="Número plano"
                      variant="underlined"
                      v-model="paciente.numeroPlano"
                      :rules="required"
                  ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-text-field

                     label="Altura"
                      variant="underlined"
                      v-model="paciente.altura"
                      :rules="required"
                  ></v-text-field>
                  <v-text-field
                     label="Peso"
                      variant="underlined"
                      v-model="paciente.peso"
                      :rules="required"
                  ></v-text-field>
                  <v-autocomplete
                      v-model="paciente.alergico"
                      :items="listAlergico"
                      item-title="label"
                      item-value="label"
                      label="Alérgico?"
                      variant="underlined"
                    >
                    </v-autocomplete>


                  </v-col>
                  <v-col cols="12" sm="12">
                  <v-text-field
                     label="Observação"
                      variant="underlined"
                      v-model="paciente.observacao"
                      :rules="required"
                  ></v-text-field>
                </v-col>

                  <v-col cols="12" sm="6">

                  <div class="d-flex">
                      <v-btn class="ma-2"
                        @click="createPaciente()"
                        color="blue-darken-3"
                        type="submit"
                        >  Salvar
                        <v-icon
                              icon="mdi-content-save"
                            ></v-icon>
                        </v-btn

                      ></div>
                  </v-col>

                </v-row>
              <!-- </v-container> -->
            </v-form>
            <!-- <TabelaConsultaPosto
                         @preencherFormEdicaoPosto="preencherFormEdicaoPosto"
                         :resultadoConsultaPosto="store.resultadoConsultaPosto"
           class="mt-3"
          />
          <FormCadastroPosto />-->
                  </v-window-item>
                  <v-window-item value="two">
                      <v-card>
                        <v-data-table
                          :headers="headersPaciente"
                          :items="pacientes"
                          :search="search"
                          :items-per-page="pages.itemsPerPage"
                          :page="pages.page"
                        >
                          <!-- <template #item.check="{ item }">
                            <v-checkbox
                              :id="'checkBoxPosto' + item.raw.id"
                              class="mt-5"
                              @click="preencherFormEdicaoPosto(item.raw)"
                            ></v-checkbox>
                          </template> -->
                          <template #item.id="{ item }">
                            {{ item.raw.id || "Sem dados" }}
                          </template>
                          <template #item.nome="{ item }">
                            {{ item.raw.nome || "Sem dados" }}
                          </template>
                          <template #item.cpf="{ item }">
                            {{ item.raw.cpf || "Sem dados" }}
                          </template>
                          <!-- <template #item.actions="{ item }">
                            <v-btn
                              dark
                              size="30px"
                              title="Deletar posto"
                              icon="mdi-delete"
                              class="mx-1 text-info rounded"
                              @click="confirmaRemovePosto(item.raw)"
                            />
                          </template> -->
                          <template #bottom> </template>
                        </v-data-table>
                      </v-card>

                  </v-window-item>
                </v-window>
              </v-card-text>
            </v-card>






          </v-expansion-panel-text>
        </v-expansion-panel>
      </v-expansion-panels>
    </v-col>

    <!-- PESSOA -->
    <v-col class="d-flex flex-column" cols="12" md="6">
      <v-expansion-panels>
        <v-expansion-panel>
          <v-expansion-panel-title>
            <v-avatar  height="50" icon="mdi-account-details">
              <!-- <v-img height="20" src="@/assets/image/ma.svg"> </v-img> -->
            </v-avatar>
            <h4>Cadastro de pessoa</h4>
          </v-expansion-panel-title>
          <v-expansion-panel-text>



            <v-card>
              <v-card-title>
                <v-tabs v-model="tab" bg-color="transparent" color="basil" grow>
                  <v-tab value="one">Cadastrar</v-tab>
                  <v-tab value="two">Lista de pessoas</v-tab>
                </v-tabs>
              </v-card-title>

              <v-card-text>
                <v-window v-model="tab">
                  <v-window-item value="one">




            <v-form @submit.prevent="criarSolicitacao" ref="formEstadual">
              <!-- <v-container> -->
                <v-row>

                  <v-col cols="12" sm="6">
                  <v-text-field
                      v-model="agendamento.nome"
                      variant="underlined"
                      label="Nome"
                      :rules="required"
                    ></v-text-field>
                    <v-text-field
                      v-model="agendamento.mae"
                      variant="underlined"
                      label="Mãe"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-text-field
                    type="date"
                     label="Data de nascimento"
                      variant="underlined"
                      v-model="agendamento.dataNascimento"
                      :rules="required"
                  ></v-text-field>
                  <v-autocomplete
                      v-model="agendamento.sexo"
                      :items="sexo"
                      item-title="label"
                      item-value="label"
                      label="Sexo"
                      variant="underlined"
                    >
                    </v-autocomplete>

                  </v-col>


                  <!-- Endereço -->
                  <v-col cols="12" sm="12">
                    <h4> Endereço </h4>
                    <v-divider></v-divider>
                  </v-col>
                  <v-col cols="12" sm="6">
                  <v-text-field
                      v-model="agendamento.endereco.cep"
                      variant="underlined"
                      label="CEP"
                      :rules="required"
                    ></v-text-field>
                    <v-text-field
                      v-model="agendamento.endereco.cidade"
                      variant="underlined"
                      label="Cidade"
                      :rules="required"
                    ></v-text-field>
                    <v-text-field
                      v-model="agendamento.endereco.logradouro"
                      variant="underlined"
                      label="Logradouro"
                      :rules="required"
                    ></v-text-field>
                    <v-text-field
                      v-model="agendamento.endereco.complemento"
                      variant="underlined"
                      label="Complemento"
                      :rules="required"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-text-field
                      v-model="agendamento.endereco.uf"
                      variant="underlined"
                      label="UF"
                      :rules="required"
                    ></v-text-field>
                    <v-text-field
                      v-model="agendamento.endereco.numero"
                      variant="underlined"
                      label="Número"
                      :rules="required"
                    ></v-text-field>
                    <v-text-field
                      v-model="agendamento.endereco.bairro"
                      variant="underlined"
                      label="Bairro"
                      :rules="required"
                    ></v-text-field>

                  </v-col>



                  <v-col cols="12" sm="12">
                    <h4> Documentos </h4>
                    <v-divider></v-divider>
                  </v-col>
                  <v-col cols="12" sm="6">
                  <v-text-field
                      v-model="agendamento.documentos.cpf"
                      variant="underlined"
                      label="CPF"
                      :rules="required"
                    ></v-text-field>
                    <v-text-field
                      v-model="agendamento.documentos.rg"
                      variant="underlined"
                      label="RG"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-text-field
                      v-model="agendamento.documentos.sus"
                      variant="underlined"
                      label="SUS"
                    ></v-text-field>
                    <v-text-field
                      v-model="agendamento.documentos.cnh"
                      variant="underlined"
                      label="CNH"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="12">
                    <h4> Contato </h4>
                    <v-divider></v-divider>
                  </v-col>
                  <v-col cols="12" sm="6">
                  <v-text-field
                      v-model="agendamento.contato.dddCelular"
                      variant="underlined"
                      label="DDD"
                      :rules="required"
                    ></v-text-field>
                    <v-text-field
                      v-model="agendamento.contato.dddTelefone"
                      variant="underlined"
                      label="DDD"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-text-field
                      v-model="agendamento.contato.celular"
                      variant="underlined"
                      label="Celular"
                    ></v-text-field>
                    <v-text-field
                      v-model="agendamento.contato.telefone"
                      variant="underlined"
                      label="Telefone"

                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">

                  <div class="d-flex">
                      <v-btn class="ma-2"
                        @click="salvarPessoa()"
                        color="blue-darken-3"
                        type="submit"
                        >  Salvar
                        <v-icon
                              icon="mdi-content-save"
                            ></v-icon>
                        </v-btn

                      ></div>
                  </v-col>

                </v-row>
              <!-- </v-container> -->
            </v-form>
            <!-- <TabelaConsultaPosto
                         @preencherFormEdicaoPosto="preencherFormEdicaoPosto"
                         :resultadoConsultaPosto="store.resultadoConsultaPosto"
           class="mt-3"
          />
          <FormCadastroPosto />-->
                  </v-window-item>
                  <v-window-item value="two">
                      <v-card>
                        <v-data-table
                          :headers="headers"
                          :items="items"
                          :search="search"
                          :items-per-page="pages.itemsPerPage"
                          :page="pages.page"
                        >
                          <!-- <template #item.check="{ item }">
                            <v-checkbox
                              :id="'checkBoxPosto' + item.raw.id"
                              class="mt-5"
                              @click="preencherFormEdicaoPosto(item.raw)"
                            ></v-checkbox>
                          </template> -->
                          <template #item.nome="{ item }">
                            {{ item.raw.nome || "Sem dados" }}
                          </template>
                          <template #item.data_nascimento="{ item }">
                            {{ item.raw.data_nascimento || "Sem dados" }}
                          </template>
                          <template #item.cpf="{ item }">
                            {{ item.raw.cpf || "Sem dados" }}
                          </template>
                          <!-- <template #item.actions="{ item }">
                            <v-btn
                              dark
                              size="30px"
                              title="Deletar posto"
                              icon="mdi-delete"
                              class="mx-1 text-info rounded"
                              @click="confirmaRemovePosto(item.raw)"
                            />
                          </template> -->
                          <template #bottom> </template>
                        </v-data-table>
                      </v-card>

                  </v-window-item>
                </v-window>
              </v-card-text>
            </v-card>

          </v-expansion-panel-text>
        </v-expansion-panel>
      </v-expansion-panels>
    </v-col>
  </v-row>
</template>

<script>
//const agendamento = ref();

// agendamento: {
//         endereco: {},
//         documentos: {},
//         contato: {},
//       }

export default {
  name: "CriarSolicitacao",
  data() {
    return {
      agendamento: {
        endereco: {},
        documentos: {},
        contato: {},
      },
      paciente:{},
      tab:null,
    };
  },
  methods: {
    salvarPessoa() {
      //localStorage.setItem("agendamento", JSON.stringify(this.agendamento));
      PessoaService.cadastrarPessoa(this.agendamento).then((response) => {
        Toast.fire("Sucesso ao cadastrar pessoa!", "success");
        })
        .catch(() => {
          Toast.fire("Erro ao cadastrar pessoa!", "error");
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
    createPaciente() {
      //localStorage.setItem("paciente", JSON.stringify(this.paciente));

      PessoaService.cadastrarPaciente(this.paciente)
        .then((response) => {
          Toast.fire("Sucesso ao cadastrar paciente!", "success");
        })
        .catch(() => {
          Toast.fire("Erro ao cadastrar paciente!", "error");
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
  },
};
</script>
