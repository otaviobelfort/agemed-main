<script setup>
import { items } from "./breadItems";
import { ref } from "vue";

const props = defineProps({
    data: {
        type: String,
        required: true,
    }
})
const bItems = ref(items[props.data])
</script>

<template>
    <v-breadcrumbs :items="bItems">
        <template #prepend>
            <v-icon size="small" icon="mdi-home" :class="bItems.disabled ? 'text-grey' : 'text-blue-darken-3'"></v-icon>
        </template>
        <template #divider>
            <v-icon size="small" icon="mdi-chevron-right" :class="bItems.disabled ? 'text-grey' : 'text-blue-darken-3'"></v-icon>
        </template>
        <template #title="{ item }">
            <span :class="bItems.disabled ? 'text-grey' : 'text-blue-darken-3'">{{ item.title }}</span>
        </template>
    </v-breadcrumbs>
</template>