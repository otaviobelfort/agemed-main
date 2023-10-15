<!-- <script setup>
import { onMounted, ref } from "vue";
import axios from "axios";
import CryptoJS from "crypto-js";
import router from "@/router";
import { useRoute } from "vue-router";
const route = useRoute();

onMounted(() => {
  const code = new URLSearchParams(route.query).get("code");
  if (code) {
    gerarToken(code);
  } else {
    gerarCode();
  }
});

const gerarToken = (code) => {
  const codeVerifier = localStorage.getItem("code_verifier");
  const clientId = "awuser";
  const redirectUri = "http://127.0.0.1:8080/login";
  const tokenEndpoint = "http://172.20.2.4:8020/oauth2/token";

  const data = new URLSearchParams({
    grant_type: "authorization_code",
    code: code,
    redirect_uri: redirectUri,
    client_id: clientId,
    code_verifier: codeVerifier,
  });

  axios
    .post(tokenEndpoint, data)
    .then((response) => {
      localStorage.removeItem("access_token");
      localStorage.setItem(
        "access_token",
        JSON.stringify(response.data.access_token)
      );
      router.replace("/");
    })
    .catch((error) => {
      console.error(error);
    });
};

const gerarCode = () => {
  try {
    const codeVerifier = generateCodeVerifier();
    const codeChallenge = generateCodeChallenge(codeVerifier);

    localStorage.setItem("code_verifier", codeVerifier);
    localStorage.setItem("codeChallenge", codeChallenge);

    let scope = "myuser:read myuser:write posts:write";
    let client_id = "awuser";

    window.location.href = `http://172.20.2.4:8020/oauth2/authorize?response_type=code&client_id=${client_id}&state=12345&redirect_uri=http://127.0.0.1:8080/login&scope=${scope}&code_challenge=${codeChallenge}&code_challenge_method=S256`;
  } catch (error) {
    console.error(error);
  }
};

const generateCodeVerifier = () => {
  const characters =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  const randomBytes = new Uint8Array(32);
  window.crypto.getRandomValues(randomBytes);
  const codeVerifier = Array.from(randomBytes)
    .map((byte) => characters[byte % characters.length])
    .join("");
  return codeVerifier;
};

const generateCodeChallenge = (codeVerifier) => {
  const hash = CryptoJS.SHA256(codeVerifier);
  const codeChallenge = hash
    .toString(CryptoJS.enc.Base64)
    .replace(/=/g, "")
    .replace(/\+/g, "-")
    .replace(/\//g, "_");
  return codeChallenge;
};
</script>
 -->
<!-- <template>
  <h1 class="mt-10 d-flex justify-center">Autenticando...</h1>
</template>
<template> -->
  <!-- <v-container fluid class="grey lighten-5">
      <router-view />
  </v-container>
  </template> -->


