// import axios from "axios";
// const token = "eyJ4NXQjUzI1NiI6IjhFdU84S0djbnFnaGxGcHdhbUlybHpLRXhEcHFKeGpHVkRNNzByUFhydVkiLCJraWQiOiJhd2Jsb2ciLCJhbGciOiJSUzI1NiJ9.eyJwZXJmaXMiOlsiQ09OU1VMVEFTIiwiTkFUVVJFWkFTIiwiQ09OU1VMVEFfQ0VMVUxBUl9BUFAiLCJDT05TVUxUQV9NQU5EQURPIiwiR0RPQ1MiLCJDT05TVUxUQV9FTVBSRVNBX0FQUCIsIk9DT1JSRU5DSUFTIiwiQ09OU1VMVEFfVkVJQ1VMT19BUFAiLCJJTVBSSU1JUl9CTyIsIlNJR05BVEFSSU8iLCJFU1RBVElTVElDQVMiLCJBVURJVE9SSUEiLCJDQURBU1RST19WSVNJVEEiLCJDQURBU1RST19QRVNTT0EiLCJDT05TVUxUQV9DRUxVTEFSIiwiQ09OU1VMVEFfTEVHSVNMQUNBT19BUFAiLCJFTkRFUkVDTyIsIlZJU0lUQSIsIkNPTlNVTFRBX0NJREFEQU9fQVBQIiwiQ09OU1VMVEFfSU5ESVZJRFVPIiwiQ09OU1VMVEFfQ09JU0EiLCJWSVNVQUxJWkFSX0JPX0FQUCIsIlVOSURBREVTIiwiQ09OU1VMVEFfVU5JREFERV9BUFAiLCJDT05TVUxUQV9ET0NVTUVOVE8iLCJDT05TVUxUQVNfQVBQIiwiU0lHTUFfQVBQIiwiUEVTU09BUyIsIkNPTlNVTFRBX1ZFSUNVTE9fTkFDSU9OQUwiLCJQRVNRVUlTQVJfQk8iLCJVU1VBUklPUyIsIlZJU1VBTElaQVJfQk8iLCJDT05TVUxUQV9BUk1BIiwiQ09OU1VMVEFfVkVJQ1VMTyIsIkNPTlNVTFRBX0NIRVFVRSIsIkNBREFTVFJBUl9PRklDSU8iLCJOT1ZBX1BFU1NPQV9GSVNJQ0EiLCJBRE1JTklTVFJBQ0FPIiwiTElTVEFSX1ZJU0lUQVMiLCJWSVNVQUxJWkFSX01BTkRBRE9fQVBQIiwiQ09OU1VMVEFfREVURU5UT19BUFAiLCJQRVNRVUlTQVJfQk9fQVBQIl0sInN1YiI6IjExMS4xMTEuMTExLTExIiwiYXVkIjoiYXd1c2VyIiwibmJmIjoxNjgzNjMxOTg2LCJ1c2VyX2lkIjoiMTQxMCIsInNjb3BlIjpbIm15dXNlcjpyZWFkIiwicG9zdHM6d3JpdGUiLCJteXVzZXI6d3JpdGUiXSwiaXNzIjoiaHR0cDpcL1wvbG9jYWxob3N0OjgwMjAiLCJ1c3VhcmlvIjp7ImlkUGVzc29hIjo5NjM1OTgsImRhdGFTZW5oYVJlc2V0YWRhIjoiMjAyMi0xMS0yOSAxNDozNSIsInBvbGljaWFsIjpudWxsLCJtYXRyaWN1bGEiOiI4Njk5MjAiLCJub3ZhU2VuaGFVc3VhcmlvIjoiMjAyMi0xMS0zMCAxNDozNSIsImxvdGFjYW8iOiJTdXBlcnZpc8OjbyBkZSBJbmZvcm3DoXRpY2EiLCJub21lIjoiSk9BTyBIRU5SSVFVRSBTSUxWQSIsImxvZ2luIjoiMTExLjExMS4xMTEtMTEiLCJpZEZ1bmNpb25hcmlvIjoxNDEwLCJjYXJnbyI6IkNhcmdvIiwiaWRMb3RhY2FvIjoxMDQyLCJlbWFpbCI6ImpvYW8uc2lsdmFAc3NwLm1hLmdvdi5iciJ9LCJ1c2VyX2Z1bGxuYW1lIjoiMTExLjExMS4xMTEtMTEiLCJleHAiOjE2ODQ0OTU5ODYsImlhdCI6MTY4MzYzMTk4Nn0.jb5JjJM1OLlpEh5CTFJeqCWrGC6IcMSpYUv2flHjjVKwGsNwQb_w9rsco0G-DpZ4cz3weifLUVvDZkFTw1aymqpuYKG4DT2ya-zDFVcB_qAeK93XrMEC4LpJsGeO95fmUeGwa6Br-UD38HyPAFBr8N5kh4unB4Vd7-5ytfLJit0uFNcIFppcbPMCBkEDgqsoZGcjh9YDZ6ad89EWv_GObkUmcNyU4XveMlQ5V0nSTTWOvigps81TAK4OO52mu5BuzEfPXZYGLBHJcnJCKr32U1k2oH_7QPb1rmMaDb4-CMGp2LE6V8xliOCSmqQQQI05nT53ngZ2uUScu-gIypop_w"
// const config = {
//   headers: { Authorization: `Bearer ${token}` }
// };
import instance from "./axiosConfigMED";
import axios from "axios";


class PessoaService {
  cadastrarPessoa(agendamento) {
    return instance.post(`/api/pessoa/cadastro`, agendamento);
  }
  cadastrarPaciente(paciente) {
    return instance.post(`/api/paciente/cadastro`, paciente);
  }
  cadastrarAgendamento(agendamento) {
    console.log(JSON.stringify(agendamento))
    return instance.post(`/api/agendamento/cadastro`, agendamento);
  }
  login(dadoLogin) {
    return instance.post(`/auth/login`,dadoLogin);
  }
  cancelarAgendamento(id) {
    return instance.post(`/api/agendamento/cancelar/`+ id);
  }
  listaResumoPaciente() {
    return instance.get(`/api/paciente/lista`);
  }
  listaResumoPessoa() {
    return instance.get(`/api/pessoa/lista/resumo`);
  }

  listaResumoMedico() {
    return instance.get(`/medico/lista`);
  }
  listaAgendamentos() {
    return instance.get(`/api/agendamento/lista`);
  }
  async listaEspecialidade() {
    return await instance.get(`/especialidades/lista`);
  }
  consultarHorarioMedicoId(id) {
    return instance.get(`/atendimentos/medico/horario/`+ id);
  }

}


export default new PessoaService();
