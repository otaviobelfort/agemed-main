<script setup>
import BreadCrumbComponets from "@/components/BreadCrumbComponets.vue";
</script>

<template>
  <bread-crumb-componets :data="$route.name" />
  <v-col cols="12" class="d-flex justify-center">
    <span class="text-h4"
      >Seja bem vindo ao AGEMED - Agendamento de consultas!</span
    >
  </v-col>
  <v-row class="mt-5">

     <!-- card 01 -->
    <v-card
      :key="index"
      class="mx-auto my-2"
      max-width="370"
    >
      <v-img
        src="https://cdn.pixabay.com/photo/2019/04/03/03/06/treatment-4099432_1280.jpg"
        height="370px"
        cover
      ></v-img>
      <v-card-title> Cuide sua saúde </v-card-title>
      <v-card-text >
        Cuidar do cuidar do nosso corpo e da nossa mente atrelado
        aos hábitos saudáveis ajudam a longevidade
      </v-card-text>
    </v-card>

     <!-- card 02 -->
    <v-card
      :key="index"
      class="mx-auto my-2"
      max-width="370"
    >
      <v-img
        src="https://cdn.pixabay.com/photo/2017/10/18/15/12/october-2864481_1280.jpg"
        height="370px"
        cover
      ></v-img>
      <v-card-title> Outubro Rosa </v-card-title>
      <v-card-text >
        Objetivo principal alertar a todos a respeito do câncer de mama e da importância de um diagnóstico precoce.
      </v-card-text>
    </v-card>

     <!-- card 03 -->
    <v-card
      :key="index"
      class="mx-auto my-2"
      max-width="370"
    >
      <v-img
        src="https://cdn.pixabay.com/photo/2015/12/09/17/11/vegetable-1085065_640.jpg"
        height="370px"
        cover
      ></v-img>
      <v-card-title> Alimentação saudável </v-card-title>
      <v-card-text >
        É o primeiro passo para uma vida longa e de qualidade.
         Uma alimentação variada e saudável ajuda a prevenir doenças.
      </v-card-text>
    </v-card>
  </v-row>
</template>
