import axios from "axios";

const instance = axios.create({
  baseURL: "http://192.168.100.4:8084"
});
// const instance = axios.create({
//   baseURL: "http://192.168.100.4:8084",
//   headers: {
//     Authorization: `Bearer ${JSON.parse(localStorage.getItem("access_token"))}`
//   }
// });

// instance.interceptors.response.use(
//   function (response) {
//     return response;
//   },
//   function (error) {

//     let res = error.response;
//     if (
//       res.status == 401
//     ) {
//       // deslogar o usuário
//       window.location.href = "/login";
//     }

//     if (res.status == 503) {
//       console.log("Serviço indisponível, código 503");
//     }

//     return Promise.reject(error);
//   }
// );

export default instance;
