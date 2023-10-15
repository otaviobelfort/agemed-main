<script setup>
import BreadCrumbComponets from '@/components/BreadCrumbComponets.vue';
import { onMounted } from "vue";
import { Toast } from "@/utils/services";
import FormEmissaoEstadual from './components/FormEmissaoEstadual.vue';
</script>
<template>
    <bread-crumb-componets :data="$route.name" />
    <FormEmissaoEstadual />
</template>