<template>
  <v-card class="mb-2">
    <v-form>
      <v-container>
        <v-col cols="12">
          <v-row>
            <v-col class="pb-0" cols="6">
              <v-text-field
                label="DDD do celular"
                variant="underlined"
              ></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="6">
              <v-text-field
                label="Celular"
                variant="underlined"
              ></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="6">
              <v-text-field
                label="DDD do telefone"
                variant="underlined"
              ></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="6">
              <v-text-field
                label="Telefone"
                variant="underlined"
              ></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="6">
              <v-text-field
                label="E-mail"
                variant="underlined"
                v-mask="'###.###.###-##'"
              ></v-text-field>
            </v-col>
      
          </v-row>
        </v-col>
      </v-container>
    </v-form>
  </v-card>
</template>
  
   <script setup>
import { onMounted, ref, watch, onBeforeUnmount } from "vue";
/* impodia do diart PessoaService from "@/service/PessoaService";
*/
import { Toast } from "@/utils/services"; 

/* let deficiencia = ref({
  observacao: "",
  alergico: "false",
  auditiva: "false",
  mental: "false",
  motora: "false",
  visual: "false",
  sindromeDown: "false",
  portadorTea: "false",
});


const props = defineProps({
  finished: {
    type: Boolean,
    required: true,
  },
});

watch(
  () => props.finished,
  (first, second) => {
    editarPessoaCompleta();
  }
);

onMounted(() => {
 recuperarDeficienciaLocalStorage(); 
});

onBeforeUnmount(() => {
  salvarDeficienciaLocalStorage();
});

const salvarDeficienciaLocalStorage=()=>{
  if (JSON.parse(localStorage.getItem("armazenarDadosEdicao")) != null) {
    let dadosEdicao = JSON.parse(localStorage.getItem("armazenarDadosEdicao"));
    dadosEdicao.deficiencia = deficiencia.value;
    dadosEdicao.deficiencia.alergico =
      dadosEdicao.deficiencia.alergico == "true" ? true : false;
    dadosEdicao.deficiencia.auditiva =
      dadosEdicao.deficiencia.auditiva == "true" ? true : false;
    dadosEdicao.deficiencia.mental =
      dadosEdicao.deficiencia.mental == "true" ? true : false;
    dadosEdicao.deficiencia.motora =
      dadosEdicao.deficiencia.motora == "true" ? true : false;
    dadosEdicao.deficiencia.visual =
      dadosEdicao.deficiencia.visual == "true" ? true : false;
    dadosEdicao.deficiencia.sindromeDown =
      dadosEdicao.deficiencia.sindromeDown == "true" ? true : false;
    dadosEdicao.deficiencia.portadorTea =
      dadosEdicao.deficiencia.portadorTea == "true" ? true : false;

    localStorage.setItem("armazenarDadosEdicao", JSON.stringify(dadosEdicao));
  }
}
const recuperarDeficienciaLocalStorage=()=>{
  if (JSON.parse(localStorage.getItem("armazenarDadosEdicao")) != null) {
    let dadosEdicao = JSON.parse(localStorage.getItem("armazenarDadosEdicao"));

    dadosEdicao.deficiencia.alergico =
      dadosEdicao.deficiencia.alergico == true ? "true" : "false";
    dadosEdicao.deficiencia.auditiva =
      dadosEdicao.deficiencia.auditiva == true ? "true" : "false";
    dadosEdicao.deficiencia.mental =
      dadosEdicao.deficiencia.mental == true ? "true" : "false";
    dadosEdicao.deficiencia.motora =
      dadosEdicao.deficiencia.motora == true ? "true" : "false";
    dadosEdicao.deficiencia.visual =
      dadosEdicao.deficiencia.visual == true ? "true" : "false";
    dadosEdicao.deficiencia.sindromeDown =
      dadosEdicao.deficiencia.sindromeDown == true ? "true" : "false";
    dadosEdicao.deficiencia.portadorTea =
      dadosEdicao.deficiencia.portadorTea == true ? "true" : "false";

    deficiencia.value = dadosEdicao.deficiencia;
  }
}

const editarPessoaCompleta = () => {
  let dadosEdicao = JSON.parse(localStorage.getItem("armazenarDadosEdicao"));
  let idPessoaEdicao = JSON.parse(localStorage.getItem("idPessoaEdicao"));

  dadosEdicao=formatarCpfEdicao(dadosEdicao);

  dadosEdicao=formatarCepEdicao(dadosEdicao);

  PessoaService.atualizarPessoa(dadosEdicao, idPessoaEdicao)
    .then(() => {
      Toast.fire("Atualização realizada com sucesso!!", "success");
    })
    .catch((e) => {
      Toast.fire("Falha ao atualizar!!", "error");
    });
};

const formatarCpfEdicao=(dadosEdicao)=>{
  dadosEdicao.documentos.cpf = dadosEdicao.documentos.cpf.replace(".", "");
  dadosEdicao.documentos.cpf = dadosEdicao.documentos.cpf.replace(".", "");
  dadosEdicao.documentos.cpf = dadosEdicao.documentos.cpf.replace("-", "");

  dadosEdicao.documentos.cpf = parseInt(dadosEdicao.documentos.cpf);
  
  return dadosEdicao;
}

const formatarCepEdicao=(dadosEdicao)=>{
   dadosEdicao.endereco.cep = dadosEdicao.endereco.cep.replace(".", "");
  dadosEdicao.endereco.cep = dadosEdicao.endereco.cep.replace("-", "");

  dadosEdicao.endereco.cep = parseInt(dadosEdicao.endereco.cep); 

  return dadosEdicao;
} */


</script>
  
  <style>
</style>