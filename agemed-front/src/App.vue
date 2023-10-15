<template>
  <div v-if="Versao === 'desenvolvimentos'">
    <div id="tarjaTreinamento">
      <span>Treinamento</span>
    </div>
    <router-view />
  </div>
  <div v-else>
    <router-view />
  </div>
</template>

<script setup>
import { Versao } from "@/config/axiosConfig";
</script>
<style>
@import url("@/assets/css/style.css");
</style>
