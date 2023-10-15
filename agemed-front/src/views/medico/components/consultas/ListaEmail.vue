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
    { title: 'Código', align: 'center', key: 'cod' },
    { title: 'Email', align: 'center', key: 'email' },
])

</script>
<template>
    <v-row class="form-label-resize">
        <v-data-table-virtual :headers="fields" :items="data.listaEmail" class="elevation-1"
            item-value="name"></v-data-table-virtual>
    </v-row>
</template>