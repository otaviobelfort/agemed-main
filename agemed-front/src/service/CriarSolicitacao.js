import axios from 'axios'

export default {
    name: 'CriarSolicitacao',
    data() {
        return {
            solicitacao: { },
        }
    },
    methods: {
        createPost() {
            axios
                .post('http://172.20.2.4:8082/agendamento', this.solicitacao)
                .then((response) => {
                    console.log(response)
                })
                .catch((error) => {
                    console.log(error)
                })
        },
    },
}
