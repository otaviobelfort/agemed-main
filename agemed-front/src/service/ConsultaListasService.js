import instance from "./axiosConfig";

class ConsultaListasService {
  buscarListaPorNomeTabela(nome) {
    return instance.get(`/lista/resumo/${nome}`);
  }




}

export default new ConsultaListasService();
