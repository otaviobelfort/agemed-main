<script setup>
import { onBeforeRouteLeave } from 'vue-router';
import BreadCrumbComponets from '@/components/BreadCrumbComponets.vue';
import FormConsulta from "./components/FormConsulta.vue";
import { ref } from "vue"
import FormEmissaoNacional from './components/FormEmissaoNacional.vue';

const tab = ref(null);
const consulta = JSON.parse(localStorage.getItem("consultaNacional"));
const data = ref(consulta.retornoConsultada[0]);
const emitir = ref(data.value.situacaoCadastro.descSitCad === "REGULAR" ? true : false);

onBeforeRouteLeave((to, from, next) => {
    localStorage.removeItem("consultaNacinal");
    next();
})
</script>
<template>
    <bread-crumb-componets :data="$route.name" />
    <v-tabs v-model="tab" color="blue-accent-4">
        <v-tab value="tab1">Consulta por CPF</v-tab>
        <v-tab v-if="emitir" value="tab2">Solicitar RG</v-tab>
    </v-tabs>
    <v-window v-model="tab">
        <v-window-item value="tab1">
            <v-card variant="transparent">
                <v-card-text>
                    <form-consulta :data="data" />
                </v-card-text>
            </v-card>
        </v-window-item>
        <v-window-item value="tab2" v-if="emitir">
            <v-card variant="transparent">
                <v-card-text>
                    <FormEmissaoNacional :data="data" />
                </v-card-text>
            </v-card>
        </v-window-item>
    </v-window>
</template>