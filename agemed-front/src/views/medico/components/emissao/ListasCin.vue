<script setup>
import { ref } from 'vue';

const props = defineProps({
  data: {
    type: Object,
    required: true
  }
})

const telefones = ref(1);
const emails = ref(1);
const filiacao = ref(1);

const tipoTelItems = ref(["Fixo", "Celular"])

</script>

<template>
  <v-card class="mb-2">
    <v-form>
      <v-container>
        <v-row>
          <!-- Telefones -->
          <v-col cols="4">
            <v-card>
              <v-card-title>
                <div class="d-flex justify-space-between align-center">
                  <div>
                    <h4>Lista de Telefones</h4>
                  </div>
                  <div>
                    <v-btn :disabled="telefones > 2" @click="telefones++" class="pa-2 rounded mx-1" icon="mdi-plus"
                      size="small"></v-btn>
                    <v-btn :disabled="telefones < 2" @click="telefones--" icon="mdi-minus" class="pa-2 rounded"
                      size="small"></v-btn>
                  </div>
                </div>
              </v-card-title>
              <!-- TODO: criar transition leave left right -->
              <v-card-text>
                <v-row v-for="(item, index) in telefones" :key="index">
                  <v-col class="pb-0" cols="5">
                    <v-select :items="tipoTelItems" label="Tipo:" variant="underlined"></v-select>
                  </v-col>
                  <v-col class="pb-0" cols="2">
                    <v-text-field label="DDI:" variant="underlined"></v-text-field>
                  </v-col>
                  <v-col class="pb-0" cols="5">
                    <v-text-field label="Telefone:" variant="underlined"></v-text-field>
                  </v-col>
                </v-row>
              </v-card-text>
            </v-card>
          </v-col>
          <!-- Emails -->
          <v-col cols="4">
            <v-card>
              <v-card-title>
                <div class="d-flex justify-space-between align-center">
                  <div>
                    <h4>Lista de e-mails</h4>
                  </div>
                  <div>
                    <v-btn :disabled="emails > 2" @click="emails++" class="pa-2 rounded mx-1" icon="mdi-plus"
                      size="small"></v-btn>
                    <v-btn :disabled="emails < 2" @click="emails--" icon="mdi-minus" class="pa-2 rounded"
                      size="small"></v-btn>
                  </div>
                </div>
              </v-card-title>
              <!-- TODO: criar transition leave left right -->
              <v-card-text>
                <v-row v-for="(item, index) in emails" :key="index">
                  <v-col class="pb-0" cols="12">
                    <v-text-field label="Email:" variant="underlined"></v-text-field>
                  </v-col>
                </v-row>
              </v-card-text>
            </v-card>
          </v-col>
          <!-- Filiação -->
          <v-col cols="4">
            <v-card>
              <v-card-title>
                <div class="d-flex justify-space-between align-center">
                  <div>
                    <h4>Lista de filiação</h4>
                  </div>
                  <div>
                    <v-btn :disabled="filiacao > 5" @click="filiacao++" class="pa-2 rounded mx-1" icon="mdi-plus"
                      size="small"></v-btn>
                    <v-btn :disabled="filiacao < 2" @click="filiacao--" icon="mdi-minus" class="pa-2 rounded"
                      size="small"></v-btn>
                  </div>
                </div>
              </v-card-title>
              <!-- TODO: criar transition leave left right -->
              <v-card-text>
                <v-row v-for="(item, index) in filiacao" :key="index">
                  <v-col class="pb-0" cols="12">
                    <v-text-field label="Filiacao:" variant="underlined"></v-text-field>
                  </v-col>
                </v-row>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
  </v-card>
</template>
