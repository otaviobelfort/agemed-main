<script setup>
import {
  onMounted,
  onUnmounted,
  ref,
  watch,
  watchEffect,
} from "vue";
import Cropper from "cropperjs";

const props = defineProps({
  data: {
    type: Object,
    required: true
  }
})

const emit = defineEmits(['dialog'])

const imageInput = ref(null);
const selectedFile = ref(null);
const imageScr = ref(null);
const img = ref(null);
const fileReader = new FileReader();
const blobImg = ref(null);
let cropper = null;

fileReader.onload = (event) => {
  imageScr.value = event.target.result;
};

// TODO: Criar um evento para salvar enviar a imagem
/* const updateLogoCompany = () => {
  imageScr.value = null;
  company.logo = blobImg.value;
  api
    .put("company/api/logo/update", company)
    .then(() => {
      Toast.fire("Sua Logo foi atualizada com sucesso!", "success");
      getEvent.value = true;
    })
    .catch(() => {
      Toast.fire("Houve um erro ao atualizar sua logo", "error");
      getEvent.value = false;
    });
}; */

const fileChanged = (e) => {
  const files = e.target.files || e.dataTransfer.files;
  if (files.length) {
    selectedFile.value = files[0];
  }
};

const fileCleared = () => {
  selectedFile.value = null;
};

onMounted(() => {
  console.log(img.value)
  cropper = new Cropper(img.value, {
    aspectRatio: 3 / 4,
    minCropBoxWidth: 150,
    minCropBoxHeight: 200,
    dragMode: "move",
    background: true,
    cropBoxMovable: true,
    cropBoxResizable: true,
    zoomable: true,
    scalable: true,
    crop: () => {
      const canvas = cropper.getCroppedCanvas({
        width: 300,
        height: 400,
        imageSmoothingQuality: "low",
      });
      blobImg.value = canvas.toDataURL();
    },
  });
});

onUnmounted(() => {
  cropper.destroy();
});

watchEffect(() => {
  if (selectedFile.value) {
    fileReader.readAsDataURL(selectedFile.value);
  } else {
    imageScr.value = null;
  }
});

watch(imageScr, () => {
  if (imageScr.value) {
    cropper.replace(imageScr.value);
  }
},
  {
    flush: "post",
  }
);

const getImageCin = () => {
  props.data.foto = blobImg.value.split(",")[1];
  closeDialog();
}

const closeDialog = () => {
  emit("dialog");
}
</script>

<template>
  <v-card-text class="ma-2 d-flex justify-center align-center" style="min-height: 300px; max-height: 350px">
    <div v-show="imageScr" class="ma-2 mx-auto">
      <img id="img" :src="imageScr" height="350" ref="img" class="relative" />
    </div>
  </v-card-text>
  <v-divider />
  <v-card-actions>
    <v-spacer></v-spacer>

    <v-btn v-if="imageScr" variant="elevated" color="grey" icon="mdi-cancel" class="pa-2 rounded" @click="fileCleared"
      size="small"></v-btn>
    <!-- upload button -->
    <v-btn variant="elevated" color="light-blue-darken-4" class="pa-2 rounded" @click="imageInput.click()"
      icon="mdi-cloud-upload" size="small"></v-btn>
    <input type="file" ref="imageInput" accept=".jpg, .png, .gif" @change="fileChanged" style="display: none" />
    <!--/ upload button -->
    <v-btn id="btn-create-hide" type="submit" @click="getImageCin()" variant="elevated" color="green-darken-4"
      class="pa-2 rounded" :disabled="imageScr ? false : 'disabled'" icon="mdi-content-save-check" size="small"></v-btn>
    <!--/ sair button -->
    <v-btn variant="elevated" color="red-lighten-2" class="pa-2 rounded" size="small" icon="mdi-arrow-u-left-bottom-bold"
      @click="closeDialog"></v-btn>
  </v-card-actions>
</template>

<style scoped>
img {
  max-height: 350px !important;
}
</style>