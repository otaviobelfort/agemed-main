import axios from "axios";


export const Versao = "desenvolvimento";
export const ApiGateway =
  Versao === "desenvolvimento"
    ? "http://172.20.1.32:8010"
    : "https://gateway.ssp.ma.gov.br";

const instance = axios.create({
  baseURL: ApiGateway,
});
 
export default instance;