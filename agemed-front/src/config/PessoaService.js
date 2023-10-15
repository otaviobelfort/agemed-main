import axios from "axios";

class PessoaService {
  cadastrarPessoaCompleto(pessoa) {
    return axios.post(`http://172.20.2.4:8082/`,pessoa);
  }
}

export default new PessoaService();