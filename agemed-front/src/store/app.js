import { defineStore } from "pinia";
import TokenService from "@/service/TokenService";

const access_token = JSON.parse(localStorage.getItem("access_token"));

export const useAppStore = defineStore("app", {
  state() {
    return {
      token: access_token,
      tokenResolvido: access_token ? TokenService.parseJwt(access_token) : null,
      user: access_token ? TokenService.parseJwt(access_token).usuario: null,
    };
  },
  actions: {},
  getters: {},
});
