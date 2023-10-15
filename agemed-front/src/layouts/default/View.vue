<template>
  <v-main>
    <navbar-component />
    <div class="mx-15 px-5">
      <router-view />
    </div>
  </v-main>
</template>

<script setup>
import NavbarComponent from '@/components/NavbarComponent.vue';
</script>
