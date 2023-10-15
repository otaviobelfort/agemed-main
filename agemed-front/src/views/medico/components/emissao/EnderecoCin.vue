<script setup>
import { onMounted, ref, reactive, watch } from "vue";
import { listCodigoNacionalidade } from "@/assets/mock/estadual/listCodigoNacionalidade";

const props = defineProps({
  data: {
    type: Object,
    required: true
  }
})

const endereco = props?.data?.endereco
</script>

<template>
  <v-card class="mb-2">
    <v-form>
      <v-container>
        <v-col cols="12">
          <v-row>
            <!-- COLUNA 01  -->
            <v-col class="pb-0" cols="4">
              <v-autocomplete v-model="endereco.codNacionalidade" :items="listCodigoNacionalidade"
                title="Nome do país de nacionalidade (BRA, PRT)" item-title="label" item-value="codigo"
                label="Nome do país de nacionalidade" variant="underlined">
              </v-autocomplete>
            </v-col>
            <!-- <v-col class="pb-0" cols="4">
              <v-text-field
                label="Código do país de domicílio"
                variant="underlined"
              ></v-text-field>
            </v-col> -->
            <v-col class="pb-0" cols="4">
              <v-autocomplete v-model="endereco.nomeMunicEndereco" :items="listCodigoNacionalidade"
                title="Nome do município de naturalidade" item-title="label" item-value="codigo"
                label="Nome do município de naturalidade" variant="underlined">
              </v-autocomplete>
            </v-col>
            <v-col class="pb-0" cols="4">
              <v-autocomplete v-model="endereco.nomeMunicDomicilio" :items="listCodigoNacionalidade"
                title="Não deverá ter nenhuma informação se o endereço for exterior!" item-title="label"
                item-value="codigo" label="Nome do município de domicílio" variant="underlined">
              </v-autocomplete>
            </v-col>
            <v-col class="pb-0" cols="4">
              <v-text-field v-model="endereco.cep" label="CEP do domicílio" variant="underlined"></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="4">
              <v-text-field v-model="endereco.logradouro" label="Logradouro de domicílio"
                variant="underlined"></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="4">
              <v-text-field v-model="endereco.nroLogradouro" label="Número do domicílio"
                variant="underlined"></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="4">
              <v-text-field v-model="endereco.bairroLogradouro" label="Bairro do domicílio"
                variant="underlined"></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="4">
              <v-text-field v-model="endereco.compLogradouro" label="Complemento do domicílio"
                variant="underlined"></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="4">
              <v-autocomplete v-model="endereco.codMunicEnderecoIBGE" :items="listCodigoNacionalidade" title=""
                item-title="label" item-value="codigo" label="Código do município de emissão da carteira"
                variant="underlined">
              </v-autocomplete>
            </v-col>
            <v-col class="pb-0" cols="4">
              <v-text-field title="Exemplo: Miami – Flórida - EUA" label="Naturalidade exterior"
                variant="underlined"></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="4">
              <v-text-field title="Local de residência do exterior" label=" Residência exterior"
                variant="underlined"></v-text-field>
            </v-col>


          </v-row>
        </v-col>
      </v-container>
    </v-form>
  </v-card></template>
