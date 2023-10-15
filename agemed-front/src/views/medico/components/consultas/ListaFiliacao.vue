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
    { title: 'Nome', align: 'center', key: 'nome' },
])
</script>
<template>
    <v-row class="form-label-resize">
        <v-data-table-virtual :headers="fields" :items="data.listaFiliacao" class="elevation-1" item-value="name">
            <template #item.ord="{ item, index }">
                {{ index + 1 }}
            </template>
            <template #item.nome="{ item }">
                {{ item.value }}
            </template>
        </v-data-table-virtual>
    </v-row>
</template>