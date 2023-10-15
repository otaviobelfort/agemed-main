<script setup>
import { ref } from 'vue';
const props = defineProps({
    data: {
        type: Object,
        required: true
    }
})
const fields = ref([
    { title: '#', align: 'center', key: 'ord' },
    { title: 'Código', align: 'center', key: 'codigo' },
    { title: 'DDD', align: 'center', key: 'ddi' },
    { title: 'Telefone', align: 'center', key: 'telefone' },
])
</script>
<template>
    <v-row class="form-label-resize">
        <v-data-table-virtual :headers="fields" :items="data.listaContatos" class="elevation-1"
            item-value="name"></v-data-table-virtual>
    </v-row>
</template>