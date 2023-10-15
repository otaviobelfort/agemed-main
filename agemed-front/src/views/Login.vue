

<template >

                <div >
                  <v-card  class="mx-auto mt-12 pa-0 pb-8" elevation="8"  max-width="348" rounded="lg">
                     <v-toolbar class="pa-8 pb-8" dark color="primary">
                        <v-toolbar-title>
                          <v-img class="mx-auto d-flex pb-1 "
                              src="@/assets/image/brasao-ma.png"
                              max-width="180"
                            ></v-img>

                          </v-toolbar-title>
                     </v-toolbar>
                     <v-card-text>

                          <h2  class="pa-5 pb-4"   > </h2>
                     <form ref="form" @submit.prevent="logar">
                            <v-text-field
                            v-model="dados.login"
                              label="EMAIL"
                              append-inner-icon="mdi-account"
                              variant="outlined"
                             ></v-text-field>

                            <v-text-field
                            v-model="dados.senha"
                              label="SENHA"
                              append-inner-icon="mdi-lock"
                              type="password"
                              variant="outlined"
                             ></v-text-field>


                           <!-- <div class="red--text"> {{errorMessage}}</div> -->
                           <v-btn class="mb-8" size="large" type="submit"  color="primary" block>
                            ENTRAR
                          </v-btn>


                          <!-- <div class="grey--text mt-4" v-on:click="isRegister = !isRegister;">
                              {{toggleMessage}}
                           </div> -->
                      </form>
                     </v-card-text>
                  </v-card>
                </div>
</template>


<!-- <script>
export default {
  data: () => ({
    visible: false,
  }),
}
</script> -->
<script>
import PessoaService from '@/service/PessoaService';

// const dados = ref({});

// const logar = () =>{
//           PessoaService.login(this.dados).then((response) => {
//           console.log(JSON.stringify(response.data));

//         });
// };
export default {
  data() {
    return {
      token1:"",
      dados:{},
      senha: '',
    };
  },
  methods: {
   async logar() {
        console.log(JSON.stringify(
          this.dados
        ))

        PessoaService.login(this.dados).then((response) => {
          console.log(JSON.stringify(response.data.token));
          Toast.fire("Bem vindo ao AGEMED!","success");
          //router.push({ path: "/agemed/home" });
          window.location.href = '/agemed/home';

       });
    },
  },
};


import axios from 'axios'; // Importe o Axios

import router from "@/router/index1";
import { Toast } from "@/utils/services";


// export default {
//   data(){
//     return{
//       dados :{}
//     }
//   },
//   methods: {
//     logar() {
//       const data = {
//         login: '60846589389',
//         senha: '123',
//       };

//       const config = {
//         method: 'post',
//         url: 'http://192.168.100.4:8084/auth/login',
//         headers: {
//           'Content-Type': 'application/json',
//         },
//         data: data,
//       };

//       axios(config)
//         .then(function (response) {
//           // A resposta da solicitação bem-sucedida
//           console.log(response.data);

//           Toast.fire("Bem vindo ao AGEMED!");
//           //this.router.push('/agemed/home');
//           // Trate a resposta, redirecione o usuário, etc.
//         })
//         .catch(function (error) {
//           // Trate os erros da solicitação
//           console.log('Erro de solicitação:', error);
//           // Trate os erros, exiba mensagens de erro, etc.
//         });
//         router.push({ path: "/agemed/home" });
//     },
//   },
// };

</script>
