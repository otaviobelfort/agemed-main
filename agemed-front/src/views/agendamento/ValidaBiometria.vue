<style scoped>
.erro-validacao {
  color: red;            /* Cor do texto da mensagem de erro */
  font-size: 14px;       /* Tamanho da fonte da mensagem de erro */
  margin-top: 5px;       /* Espaço superior para separar a mensagem de erro dos campos */
}
</style>
<script setup>
import { ref, watch,onBeforeMount } from "vue";
import PessoaService from "@/service/PessoaService";
import { onMounted } from "vue";
import { rules } from "@/utils/validation";
import { Toast } from "@/utils/services";

const form = ref(null);
const agendamento = ref({});
const biometria = ref({});
const listPacientes = ref([]);
const listEspecialidades = ref([]);
const listMedicos = ref([{}]);
const listHoraAtendementos = ref([{}]);
const mensagemDeErro = ref(null)

const { required } = rules;


const dialog = ref(false);



const props = defineProps({
  data: {
    type: Object,
    required: true,
  },
});
onMounted(() => {
  PessoaService.listaResumoPaciente().then((response) => {
    listPacientes.value = response.data
  });

  PessoaService.listaEspecialidade().then((response) => {
    listEspecialidades.value = response.data
   //this.listEspecialidades =  [...response.data]
  });
  PessoaService.listaResumoMedico().then((response) => {
    listMedicos.value = response.data
  });
  PessoaService.consultarHorarioMedicoId(13).then((response) => {
    listHoraAtendementos.value = response.data
  });

});


watch(props, () => {


  if (props.visible) dialog.value = true;
});
const carregaListaHorarioMedico = (id) => {

  PessoaService.consultarHorarioMedicoId(13).then((response) => {
    listHoraAtendementos.value = response.data
  });
 return listEspecialidades;

};


const validarCampos = (agendamento) => {
  if (!agendamento.codPessoa) {

    return 'O campo Paciente é obrigatório.';
  }
  if (!agendamento.codEspecialidade) {

    return 'O campo Especialidade é obrigatório.';
  }
  if (!agendamento.codMedico) {

    return 'O campo Médico é obrigatório.';
  }
  if (!agendamento.dataAtendimento) {

    return 'Escolha um Horário disponível !';
  }

  return '';
};

const salvarAgendamento = () => {

  console.log(JSON.stringify(agendamento.value))

  const mensagemDeErro = validarCampos(agendamento.value);
  if (mensagemDeErro) {
    Toast.fire("ERRO: " + mensagemDeErro, "error");
    dialog.value = true;
  }

  else {
    PessoaService.cadastrarAgendamento(agendamento.value).then((response) => {
      //Toast.fire("Agendamento feito com sucesso!", "success");

    });
    Toast.fire("Agendamento feito com sucesso!", "success");
    dialog.value = false;
  }



};


const convertDateFormat = (dateString) =>{
      const date = new Date(dateString);
      const day = date.getDate();
      const month = date.getMonth() + 1;
      const year = date.getFullYear();
      return `${day}/${month}/${year}`;
}



// const salvaAgendamento = () => {

//   localStorage.setItem("agendamento", JSON.stringify(this.agendamento));
//   if(agendamento.value.codPessoa===null){
//     Toast.fire("ERRO ao tentar realizar agendamento!", "error");
//   }

//   PessoaService.cadastrarAgendamento(this.agendamento)
//         .then(() => {
//           Toast.fire("Agendamento feito com sucesso!", "success");
//         })
//         .catch(() => {
//           Toast.fire("ERRO ao tentar realizar agendamento!", "error");
//         });
//   }

</script>
<template>
  <v-dialog
    v-model="dialog"
    persistent
    max-width="840"
    transition="dialog-top-transition"
  >
    <template #activator>
      <div>
        <v-btn class="ma-2"
        @click="dialog = true"
          color="blue-darken-3"
          type="submit"
          >  Agendar consulta
          <v-icon icon="mdi-account-plus"></v-icon>
        </v-btn
      ></div>
    </template>

    <v-card>
      <v-card-title class="headline text-center"
        > Agendar consulta</v-card-title
      >
      <v-divider />

      <v-card-text>
        <v-col cols="12">
          <v-form ref="form">
            <v-row>


              <v-col cols="6" class="px-1 py-0">

                <v-autocomplete
                    :items="listPacientes"
                    :custom-filter="customFilter"
                    item-title="nome"
                    item-value="id"
                    label="Paciente"
                    variant="underlined"
                    :rules="required"
                    v-model="agendamento.codPessoa"
                  >
                  <div v-if="mensagemDeErro" class="erro-validacao">{{ mensagemDeErro }}</div>
                </v-autocomplete>
              </v-col>
              <v-col cols="6" class="px-1 py-0">
                <v-autocomplete
                  :items="listEspecialidades"
                      item-title="especialidade"
                      item-value="id"
                      label="Especialidade"
                      variant="underlined"
                      :rules="required"
                      v-model="agendamento.codEspecialidade"
                    >
                </v-autocomplete>
              </v-col>
              <v-col cols="6" class="px-1 py-0">
                <v-autocomplete
                  :items="listMedicos"
                      item-title="nome"
                      item-value="id"
                      label="Médico"
                      variant="underlined"
                      :rules="required"
                      v-model="agendamento.codMedico"
                    >
                </v-autocomplete>
              </v-col>
              <v-col cols="6" class="px-1 py-0">
                <v-autocomplete
                  :items="listHoraAtendementos"
                      item-title="dataHora"
                      item-value="id"
                      label="Horário disponível"
                      variant="underlined"
                      :rules="required"
                      v-model="agendamento.dataAtendimento"
                    >

                </v-autocomplete>
              </v-col>
              <v-col cols="6" class="px-1 py-0">
                <v-radio-group
                  v-model="agendamento.alergico"
                  label="Alérgico?"
                  inline
                >
                  <v-radio
                   require="true"
                    label="Sim"
                    value="Sim"
                    color="green-darken-3"
                  ></v-radio>
                  <v-radio
                    label="Não"
                    value="Não"
                    color="red-darken-3"
                  ></v-radio>
                </v-radio-group>
              </v-col>

              <v-col cols="12" class="px-1 py-0">
                <v-textarea
                  v-model="agendamento.observacao"

                  label="Observação deficiencia/alergia"
                ></v-textarea>


              </v-col>
            </v-row>
          </v-form>
        </v-col>
      </v-card-text>

      <v-divider />
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          variant="elevated"
          color="grey darken-2"

          text
          @click="dialog = false"
          >Sair</v-btn
        >
        <v-btn
          variant="elevated"
          color="light-blue-darken-4"
          text
          @click="salvarAgendamento"
          >Agendar</v-btn
        >
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>
