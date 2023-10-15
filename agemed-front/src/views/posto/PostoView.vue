<script setup>
import BreadCrumbComponets from "@/components/BreadCrumbComponets.vue";
import PostoService from "@/service/PostoService";
import TabelaConsultaPosto from "./components/TabelaConsultaPosto.vue";
import FormCadastroPosto from "./components/FormCadastroPosto.vue";
import FormEdicaoPosto from "./components/FormEdicaoPosto.vue";
import { ref } from "vue";
import Loading from "@/components/Loading.vue";
let listTipoPosto = ref(["ONLINE", "OFFLINE", "CONVENIO"]);
let postoCadastro = ref({ ativo: "false" });
let tipoPostoCadastro = ref({ ativo: "false", codigo: null });
import { Toast } from "@/utils/services";
let formCadastroPosto = ref(null);
let formCadastroTipoPosto = ref(null);
import { rules } from "@/utils/validation";
const { required } = rules;
const tab = ref(null);
let salvarPosto = async () => {
  if (await validar()) {
    let posto = { ...postoCadastro.value };
    posto.codigo = parseInt(posto.codigo);
    posto.ativo = posto.ativo == "true" ? true : false;
    PostoService.cadastrarPosto(posto)
      .then(() => {
        Toast.fire("Cadastro realizado com sucesso!!", "success");
      })
      .catch((e) => {
        Toast.fire(e, "error");
      });
  }
};
let salvarTipoPosto = async () => {
  if (await validarFormTipoPosto()) {
    let tipoPosto = { ...tipoPostoCadastro.value };

    tipoPosto.ativo = tipoPosto.ativo == "true" ? true : false;
    PostoService.cadastrarTipoPosto(tipoPosto)
      .then(() => {
        Toast.fire("Cadastro realizado com sucesso!!", "success");
      })
      .catch((e) => {
        Toast.fire(e, "error");
      });
  }
};
const validar = async () => {
  const { valid } = await formCadastroPosto?.value.validate();
  return valid;
};
const validarFormTipoPosto = async () => {
  const { valid } = await formCadastroTipoPosto?.value.validate();
  return valid;
};
</script>
<template>

  <bread-crumb-componets :data="$route.name" />

  <v-row>
    <v-col>
      <v-expansion-panels>
        <v-expansion-panel>
          <v-expansion-panel-title>
            <v-avatar>
              <v-img height="20"> </v-img>
            </v-avatar>
            <h4>Posto</h4>
          </v-expansion-panel-title>
          <v-expansion-panel-text>
            <v-card>
              <v-card-title>
                <v-tabs v-model="tab" bg-color="transparent" color="basil" grow>
                  <v-tab value="one">Cadastrar</v-tab>
                  <v-tab value="two">Editar</v-tab>
                </v-tabs>
              </v-card-title>

              <v-card-text>
                <v-window v-model="tab">
                  <v-window-item value="one">
                    <FormCadastroPosto />
                  </v-window-item>
                  <v-window-item value="two">
                    <FormEdicaoPosto />
                  </v-window-item>
                </v-window>
              </v-card-text>
            </v-card>
          </v-expansion-panel-text>
        </v-expansion-panel>
      </v-expansion-panels>
    </v-col>
    <v-col class="d-flex flex-column" cols="12" md="6">
      <v-expansion-panels>
        <v-expansion-panel>
          <v-expansion-panel-title>
            <v-avatar>
              <v-img height="20"> </v-img>
            </v-avatar>
            <h4>Tipo de posto</h4>
          </v-expansion-panel-title>
          <v-expansion-panel-text>
            <v-form ref="formCadastroTipoPosto">
              <v-col cols="12">
                <v-row>
                  <v-col class="pb-0" cols="6">
                    <v-text-field
                      variant="underlined"
                      label="Descrição"
                      :rules="required"
                      v-model="tipoPostoCadastro.descricao"
                    ></v-text-field>
                  </v-col>
                  <v-col class="pb-0" cols="6">
                    <h4>Ativo:</h4>
                    <v-radio-group v-model="tipoPostoCadastro.ativo" inline>
                      <v-radio
                        class="mr-3 ml-5"
                        label="Sim"
                        value="true"
                        color="green-darken-3"
                      ></v-radio>
                      <v-radio
                        label="Não"
                        value="false"
                        color="red-darken-3"
                      ></v-radio>
                    </v-radio-group>
                  </v-col>
                </v-row>
              </v-col>
              <div class="d-flex justify-end">
                <v-btn
                  class="mx-2"
                  color="blue-darken-3"
                  @click="salvarTipoPosto"
                  >Salvar</v-btn
                >
              </div>
            </v-form>
          </v-expansion-panel-text>
        </v-expansion-panel>
      </v-expansion-panels>
    </v-col>
  </v-row>
</template>


<style>
</style>