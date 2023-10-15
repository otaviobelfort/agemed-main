<script setup>
import { watch } from "vue";

const props = defineProps({
  data: {
    type: Object,
    required: true,
  },
});
const dataFormatada = (data) => {
  let d = data.split("T");
  let day = d[0].split("-");
  let date = `${day[2]}/${day[1]}/${day[0]}`;
  let hora = d[1];
  let dh = `${date} ${hora}`;
  return dh;
};

/* watch(() => props.data, (first, second) => {
    console.log(first, second);
}) */

const dateFormated = dataFormatada(props.data.dataHoraProtocoloRFB);
</script>
<template>
  <v-row class="form-label-resize">
    <!-- Protocolo RFB -->
    <v-col cols="4" class="pb-0">
      <v-text-field
        v-model="data.protocoloRFB"
        variant="underlined"
        label="Protocolo RFB:"
      ></v-text-field>
    </v-col>
    <!-- Protocolo OIC -->
    <v-col cols="4" class="pb-0">
      <v-text-field
        v-model="data.protocoloOIC"
        variant="underlined"
        label="Protocolo OIC:"
      ></v-text-field>
    </v-col>
    <!-- Data/Hora Protocolo RFB -->
    <v-col cols="4" class="pb-0">
      <v-text-field
        v-model="dateFormated"
        variant="underlined"
        label="Data/hora protocolo RFB:"
      ></v-text-field>
    </v-col>
    <!-- Código Município Emissor -->
    <v-col cols="4" class="py-0">
      <v-text-field
        v-model="data.emissaoCin.codMunEmissao"
        variant="underlined"
        label="Código município emissor:"
      ></v-text-field>
    </v-col>
    <!-- Nome Município Emissor -->
    <v-col cols="4" class="py-0">
      <v-text-field
        v-model="data.emissaoCin.nomeMunEmissao"
        variant="underlined"
        label="Nome município emissor:"
      ></v-text-field>
    </v-col>
    <!-- UF Município Emissor -->
    <v-col cols="4" class="py-0">
      <v-text-field
        v-model="data.emissaoCin.ufMunEmissao"
        variant="underlined"
        label="UF município emissor:"
      ></v-text-field>
    </v-col>
    <!-- Observação -->
    <v-col cols="8" class="py-0">
      <v-text-field
        v-model="data.observacao"
        variant="underlined"
        label="Observação:"
      ></v-text-field>
    </v-col>
    <!-- Ind. Consistência Biográfica -->
    <v-col cols="4" class="py-0">
      <v-text-field
        v-model="data.informacoesBiometricaBiografica.indicadorConsBiografica"
        variant="underlined"
        label="Indicador de consistência biográfica:"
      ></v-text-field>
    </v-col>
    <!-- Ind. Consistência Biométrica -->
    <v-col cols="4" class="py-0">
      <v-text-field
        v-model="data.informacoesBiometricaBiografica.indicadorConsBiometrica"
        variant="underlined"
        label="Indicador de consistência biométrica:"
      ></v-text-field>
    </v-col>
    <!-- Código da Situação -->
    <!-- TODO: falta arrumar o Código da Situação -->
    <v-col cols="4" class="py-0">
      <v-text-field
        v-model="data.codSituacao"
        variant="underlined"
        label="Código da situação:"
      ></v-text-field>
    </v-col>
    <!-- Hash Code da RFB -->
    <v-col cols="4" class="py-0">
      <v-text-field
        v-model="data.hashcodeRFB"
        variant="underlined"
        label="Hash code da RFB:"
      ></v-text-field>
    </v-col>
    <!-- Observação da Biometria -->
    <v-col cols="4" class="py-0">
      <v-text-field
        v-model="data.informacoesBiometricaBiografica.obsBiometria"
        variant="underlined"
        label="Observação da biometria:"
      ></v-text-field>
    </v-col>
    <!-- Data da Emissão -->
    <v-col cols="4" class="py-0">
      <v-text-field
        v-model="data.emissaoCin.dataEmissao"
        variant="underlined"
        label="Data da emissão:"
      ></v-text-field>
    </v-col>
    <!-- Biometria Válida -->
    <v-col cols="4" class="py-0">
      <v-text-field
        v-model="data.informacoesBiometricaBiografica.biometriaValida"
        variant="underlined"
        label="Biometria válida:"
      ></v-text-field>
    </v-col>
    <!-- Observação Identidade -->
    <v-col cols="4" class="py-0">
      <v-text-field
        v-model="data.obsEmissaoIdentidade"
        variant="underlined"
        label="Observação edentidade:"
      ></v-text-field>
    </v-col>
    <!-- Local de Naturalidade Exterior -->
    <v-col cols="4" class="py-0">
      <v-text-field
        v-model="data.naturalidadeExteriorCin.naturalidadeExterior"
        variant="underlined"
        label="Local de naturalidade exterior:"
      ></v-text-field>
    </v-col>
    <!-- Local de Domicílio no Exterior -->
    <v-col cols="4" class="py-0">
      <v-text-field
        v-model="data.naturalidadeExteriorCin.residenciaExterior"
        variant="underlined"
        label="Local de domicílio no exterior:"
      ></v-text-field>
    </v-col>
    <!-- Indicador de mãe no Registro -->
    <v-col cols="4" class="py-0">
      <v-text-field
        v-model="data.indicadorMaeRegistro"
        variant="underlined"
        label="Indicador de mãe no registro:"
      ></v-text-field>
    </v-col>
    <!-- Nome Social -->
    <v-col cols="4" class="py-0">
      <v-text-field
        v-model="data.nomeSocial"
        variant="underlined"
        label="Nome social:"
      ></v-text-field>
    </v-col>
  </v-row>
</template>