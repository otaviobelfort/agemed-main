<template>
  <v-form ref="formCadastroPosto">
    <Loading v-show="overlay"/>
    <v-col cols="12">
      <v-row>
        <v-col class="pb-0" cols="6">
          <v-text-field
            variant="underlined"
            label="Descrição"
            :rules="required"
            v-model="postoCadastro.descricao"
          ></v-text-field>
        </v-col>
        <v-col class="pb-0" cols="6">
          <v-autocomplete
            :items="listTipoPosto"
            item-title="label"
            item-value="id"
            label="Tipo de posto"
            variant="underlined"
            :rules="required"
            v-model="postoCadastro.tipoPosto"
          >
          </v-autocomplete>
        </v-col>
        <v-col class="pb-0" cols="6">
          <h4>Ativo:</h4>
          <v-radio-group v-model="postoCadastro.ativo" inline>
            <v-radio
              class="mr-3 ml-5"
              label="Sim"
              value="true"
              color="green-darken-3"
            ></v-radio>
            <v-radio label="Não" value="false" color="red-darken-3"></v-radio>
          </v-radio-group>
        </v-col>
      </v-row>
    </v-col>
    <div class="d-flex justify-end">
      <v-btn class="mx-2" color="blue-darken-3" @click="salvarPosto"
        >Salvar</v-btn
      >
    </div>
  </v-form>
</template>

<script setup>
import { ref } from "vue";
import PostoService from "@/service/PostoService";
import TabelaConsultaPosto from "./TabelaConsultaPosto.vue";
import Loading from "@/components/Loading.vue";
import { rules } from "@/utils/validation";
const { required } = rules;
let postoCadastro = ref({ ativo: "false" });
let listTipoPosto = ref([
  { label: "ONLINE", id: "POSTO_ONLINE" },
  { label: "OFFLINE", id: "POSTO_OFFLINE" },
  { label: "MOVEL", id: "POSTO_MOVEL" },
]);
let overlay = ref(false);
let formCadastroPosto = ref(null);
import { Toast } from "@/utils/services";
let salvarPosto = async () => {
  if (await validarFormCadastroPosto()) {
    let posto = { ...postoCadastro.value };
    posto.codigo = parseInt(posto.codigo);
    posto.ativo = posto.ativo == "true" ? true : false;
    overlay.value=true
    PostoService.cadastrarPosto(posto)
      .then(() => {
        overlay.value=false
        Toast.fire("Cadastro realizado com sucesso!!", "success");
      })
      .catch((e) => {
        overlay.value=false
        Toast.fire(e, "error");
      });
  }
};
const validarFormCadastroPosto = async () => {
  const { valid } = await formCadastroPosto?.value.validate();
  return valid;
};
</script>

<style>
</style>