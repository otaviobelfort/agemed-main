<template>
    <div class="wrapper-stepper">
        <div class="d-flex justify-center">
            <div class="stepper">
                <div class="stepper-progress">
                    <div class="stepper-progress-bar" :style="'width:' + stepperProgress"></div>
                </div>

                <div class="stepper-item" :class="{ 'current': step == item, 'success': step > item }" v-for="item in steps"
                    :key="item">
                    <div class="stepper-item-counter">
                        <!-- <i class="fa-solid fa-2x fa-check text-primary icon-success"></i> -->
                        <v-icon v-if="step > item" icon="mdi-thumb-up" color="blue-darken-2"></v-icon>
                        <span v-else class="number">
                            {{ item }}
                        </span>
                    </div>
                    <span class="stepper-item-title">
                        {{ label_steps[item - 1] }}
                    </span>
                </div>
            </div>
        </div>


        <div class="stepper-content bg-light" v-for="item in steps" :key="item">
            <div class="stepper-pane" v-if="step == item">
                <transition appear name="fade">
                    <b-col cols="12" class="mb-4">
                        <slot :name="`step-${item}`" />
                    </b-col>
                </transition>

                <b-col cols="12">
                    <div class="controls d-flex justify-space-between">
                        <!-- <v-btn @click="step--" :disabled="step == 1">
                            voltar
                        </v-btn> -->
                        <div >
                            <slot name="btn-back" />
                        </div>
                        <!-- <v-btn v-if="step < steps"  @click="step++" :disabled="step == steps">
                            próximo
                        </v-btn> -->
                        <div  v-if="step < steps">
                            <slot name="btn-next" />
                        </div>
                        <div v-else>
                            <slot name="btn-finish" />
                        </div>
                        <!-- <b-button class="btn-min-w" variant="success" @click="mostrarToast()">
                            finalizar
                        </b-button> -->
                    </div>
                </b-col>
            </div>
        </div>


    </div>
</template>
<script>

export default {
    created(){
      
        console.log("this.step",this.step)
    },
    props: {
        steps: {
            type: Number,
            required: true,
            default: 0,
        },
        step: {
            type: Number,
            required: false,
            default: 1,
        },
        label_steps: {
            type: Array,
            required: true,
        }
    },
    data(){
     return {
        dadosCadastro:null,
     }
   },
    methods:{
        mostrarToast(){

           

        }
    },
    computed: {
        stepperProgress() {
            return (100 / (this.steps - 1)) * (this.step - 1) + '%'
        }
    },
}
</script>
<style scoped>
@import url("@/assets/css/stepper.css");

.btn-min-w {
    min-width: 120px;
}

.fade-enter-active,
.fade-leave-active {
    transition: opacity 1s;
}

.fade-enter,
.fade-leave-to {
    opacity: 0;
}
</style>