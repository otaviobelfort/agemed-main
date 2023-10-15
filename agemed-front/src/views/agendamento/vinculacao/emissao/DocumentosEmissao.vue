<template>
  <v-card class="mb-2">
    <v-form>
      <v-container>
        <v-col cols="12">
          <v-row>
            <!-- COLUNA 01  -->
            <v-col class="pb-0" cols="6">
              <v-text-field
                label="CPF"
                variant="underlined"
              ></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="6">
              <v-text-field
                label="RG"
                variant="underlined"
              ></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="6">
              <v-text-field
                label="CRM"
                variant="underlined"
              ></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="6">
              <v-text-field
                label="CNS"
                variant="underlined"
              ></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="6">
              <v-text-field
                label="CNH"
                variant="underlined"
              ></v-text-field>
            </v-col>
            <v-col class="pb-0" cols="6">
              <v-text-field
                label="Passaporte"
                variant="underlined"
                v-mask="'###.###.###-##'"
              ></v-text-field>
            </v-col>

          </v-row>
        </v-col>
      </v-container>
    </v-form>
  </v-card>
</template>
<script setup>
import { onMounted, ref, reactive, watch, onBeforeUnmount } from "vue";


let documentos = ref({});

onMounted(() => {
});
onBeforeUnmount(() => {

});
</script>
