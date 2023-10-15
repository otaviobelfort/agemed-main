<script setup>
const props = defineProps({
  dadosBasicos: {
    type: Object,
    required: true,
  },
});
const getImageCin = (image) => {
    let imageFormated = image.split(",");
    if (imageFormated[0] === 'data:image/png;base64') return image
    else return `data:image/png;base64,${image}`
}
</script>
<template>
    <v-row>
        <v-col cols="3" class="d-flex align-center justify-center px-1 mx-1">
            <v-avatar rounded class="avatar-d-basicos">
                <v-img v-if="dadosBasicos.avatarImg" :src="getImageCin(dadosBasicos.avatarImg)" alt="imagem"></v-img>
                <v-icon v-else icon="mdi-account"></v-icon>
            </v-avatar>
        </v-col>
        <v-col cols="4" class="mx-2">
            <strong>Nome: </strong> <br />
            <span>{{ dadosBasicos.nome }}</span> <br />
            <strong>Data Nascimento: </strong> <br />
            <span>{{ dadosBasicos.nasc }}</span> <br />
            <strong>CPF: </strong> <br />
            <span>{{ dadosBasicos.cpf }}</span> <br />
            <strong>Data Inscrição: </strong> <br />
            <span>{{ dadosBasicos.inscricao }}</span> <br />
            <strong>Situação Cadastral: </strong> <br />
            <span v-if="dadosBasicos.sitCad == 'REGULAR'" class="pa-1 px-3 bg-green-darken-3 rounded">
                {{ dadosBasicos.sitCad }}
            </span>
            <span v-else class="pa-1 px-3 bg-red-darken-4 rounded">
                {{ dadosBasicos.sitCad }}
            </span>
        </v-col>
        <v-col cols="4">
            <strong>Data Situação Cadastral: </strong> <br />
            <span>{{ dadosBasicos.dataSitCad }}</span> <br />
            <strong>Nome Social: </strong> <br />
            <span>{{ dadosBasicos.nomeSocial }}</span> <br />
            <strong>Nome da Mãe: </strong> <br />
            <span>{{ dadosBasicos.mae }}</span> <br />
            <strong>Ano do óbito: </strong> <br />
            <span>{{ dadosBasicos.obito }}</span>
            <!-- <v-btn class="mt-3" size="sm" variant="success">solicitar RG</v-btn> -->
        </v-col>
    </v-row>
</template>
<style scoped>
.avatar-d-basicos {
    border: 1px solid #ebebeb;
    min-height: calc(2.5rem * 4);
    min-width: calc(2.5rem * 3);
}

.avatar-d-basicos i {
    font-size: 120px;
}
</style>