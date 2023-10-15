
import instance from "./axiosConfigMED";

class SolicitacaoService {
  listaIdPostIdUsuario(idPosto, idUsuario) {
    return instance.get(`/v1/solicitacao/lista`);
  }
}

export default new SolicitacaoService();
