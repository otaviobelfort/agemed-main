import Swal from 'sweetalert2'
import axios from 'axios'

export const production = false

const url = production ? "https://gateway.ssp.ma.gov.br" : "https://gateway-dev.ssp.ma.gov.br";
const urlCin = "http://172.20.2.4:8081"

const token = "eyJ4NXQjUzI1NiI6IjhFdU84S0djbnFnaGxGcHdhbUlybHpLRXhEcHFKeGpHVkRNNzByUFhydVkiLCJraWQiOiJhd2Jsb2ciLCJhbGciOiJSUzI1NiJ9.eyJwZXJmaXMiOlsiQ09OU1VMVEFTIiwiTkFUVVJFWkFTIiwiQ09OU1VMVEFfQ0VMVUxBUl9BUFAiLCJDT05TVUxUQV9NQU5EQURPIiwiR0RPQ1MiLCJDT05TVUxUQV9FTVBSRVNBX0FQUCIsIk9DT1JSRU5DSUFTIiwiQ09OU1VMVEFfVkVJQ1VMT19BUFAiLCJJTVBSSU1JUl9CTyIsIlNJR05BVEFSSU8iLCJFU1RBVElTVElDQVMiLCJBVURJVE9SSUEiLCJDQURBU1RST19WSVNJVEEiLCJDQURBU1RST19QRVNTT0EiLCJDT05TVUxUQV9DRUxVTEFSIiwiQ09OU1VMVEFfTEVHSVNMQUNBT19BUFAiLCJFTkRFUkVDTyIsIlZJU0lUQSIsIkNPTlNVTFRBX0NJREFEQU9fQVBQIiwiQ09OU1VMVEFfSU5ESVZJRFVPIiwiQ09OU1VMVEFfQ09JU0EiLCJWSVNVQUxJWkFSX0JPX0FQUCIsIlVOSURBREVTIiwiQ09OU1VMVEFfVU5JREFERV9BUFAiLCJDT05TVUxUQV9ET0NVTUVOVE8iLCJDT05TVUxUQVNfQVBQIiwiU0lHTUFfQVBQIiwiUEVTU09BUyIsIkNPTlNVTFRBX1ZFSUNVTE9fTkFDSU9OQUwiLCJQRVNRVUlTQVJfQk8iLCJVU1VBUklPUyIsIlZJU1VBTElaQVJfQk8iLCJDT05TVUxUQV9BUk1BIiwiQ09OU1VMVEFfVkVJQ1VMTyIsIkNPTlNVTFRBX0NIRVFVRSIsIkNBREFTVFJBUl9PRklDSU8iLCJOT1ZBX1BFU1NPQV9GSVNJQ0EiLCJBRE1JTklTVFJBQ0FPIiwiTElTVEFSX1ZJU0lUQVMiLCJWSVNVQUxJWkFSX01BTkRBRE9fQVBQIiwiQ09OU1VMVEFfREVURU5UT19BUFAiLCJQRVNRVUlTQVJfQk9fQVBQIl0sInN1YiI6IjExMS4xMTEuMTExLTExIiwiYXVkIjoiYXd1c2VyIiwibmJmIjoxNjgzNjMxOTg2LCJ1c2VyX2lkIjoiMTQxMCIsInNjb3BlIjpbIm15dXNlcjpyZWFkIiwicG9zdHM6d3JpdGUiLCJteXVzZXI6d3JpdGUiXSwiaXNzIjoiaHR0cDpcL1wvbG9jYWxob3N0OjgwMjAiLCJ1c3VhcmlvIjp7ImlkUGVzc29hIjo5NjM1OTgsImRhdGFTZW5oYVJlc2V0YWRhIjoiMjAyMi0xMS0yOSAxNDozNSIsInBvbGljaWFsIjpudWxsLCJtYXRyaWN1bGEiOiI4Njk5MjAiLCJub3ZhU2VuaGFVc3VhcmlvIjoiMjAyMi0xMS0zMCAxNDozNSIsImxvdGFjYW8iOiJTdXBlcnZpc8OjbyBkZSBJbmZvcm3DoXRpY2EiLCJub21lIjoiSk9BTyBIRU5SSVFVRSBTSUxWQSIsImxvZ2luIjoiMTExLjExMS4xMTEtMTEiLCJpZEZ1bmNpb25hcmlvIjoxNDEwLCJjYXJnbyI6IkNhcmdvIiwiaWRMb3RhY2FvIjoxMDQyLCJlbWFpbCI6ImpvYW8uc2lsdmFAc3NwLm1hLmdvdi5iciJ9LCJ1c2VyX2Z1bGxuYW1lIjoiMTExLjExMS4xMTEtMTEiLCJleHAiOjE2ODQ0OTU5ODYsImlhdCI6MTY4MzYzMTk4Nn0.jb5JjJM1OLlpEh5CTFJeqCWrGC6IcMSpYUv2flHjjVKwGsNwQb_w9rsco0G-DpZ4cz3weifLUVvDZkFTw1aymqpuYKG4DT2ya-zDFVcB_qAeK93XrMEC4LpJsGeO95fmUeGwa6Br-UD38HyPAFBr8N5kh4unB4Vd7-5ytfLJit0uFNcIFppcbPMCBkEDgqsoZGcjh9YDZ6ad89EWv_GObkUmcNyU4XveMlQ5V0nSTTWOvigps81TAK4OO52mu5BuzEfPXZYGLBHJcnJCKr32U1k2oH_7QPb1rmMaDb4-CMGp2LE6V8xliOCSmqQQQI05nT53ngZ2uUScu-gIypop_w"
const config = {
    headers: { Authorization: `Bearer ${token}` }
};

export const Versao = "desenvolvimento";
export const ApiGateway =
    Versao === "desenvolvimento"
        ? "http://172.20.1.32:8010"
        : "https://gateway.agemed.com";

export const api = (method, endpoint, params) => {
    return axios[method](url + endpoint, params, config)
}

export const apiCin = (method, endpoint, params) => {
    return axios[method](urlCin + endpoint, params, config)
}

//Mascara de CPF
export const mascaraCPF = (cpf) => {
    cpf = cpf.replace(/\D/g, "");
    cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2");
    cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2");
    cpf = cpf.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
    return cpf;
}

//Mascara de CNPJ
export const mascaraCNPJ = (cnpj) => {
    cnpj = cnpj.replace(/\D/g, "");
    cnpj = cnpj.replace(/^(\d{2})(\d{3})?(\d{3})?(\d{4})?(\d{2})?/, "$1.$2.$3/$4-$5");
    return cnpj;
}

// Toast Sweet Alert
export const Toast = {
    fire(title, icon, text, timer) {
        const Toast = Swal.mixin({
            toast: true,
            position: "top-right",
            iconColor: "white",
            showCloseButton: true,
            customClass: {
                popup: "colored-toast",
            },
            showConfirmButton: false,
            timerProgressBar: true,
            didOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        });
        Toast.fire({
            icon: icon,
            title: title,
            text: text,
            timer: timer || 5000
        });
    }
}

export const appLoading = document.getElementById('loading-bg');
