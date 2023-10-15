import { defineStore } from "pinia";
import axios from "@axios";
import CryptoJS from "crypto-js";
import router from "@/router";

export const useAuthStore = defineStore("auth", () => {
  const baseUrl = "http://172.20.1.15:8010";
  const redirectUri = "http://172.20.2.242:8080/login";
  const codeVerifier = localStorage.getItem("code_verifier");
  const clientId = "sic";
  const tokenEndpoint = baseUrl + "/oauth2/token";
console.log("codeVerifier",codeVerifier)
  const gerarToken = (code) => {
    const data = new URLSearchParams({
      grant_type: "authorization_code",
      code: code,
      redirect_uri: redirectUri,
      client_id: clientId,
      code_verifier: codeVerifier,
    });
    const headers_object = {
      "Content-Type": "application/x-www-form-urlencoded",
      Accept: "/",
      Authorization: "Basic c2ljOjEyMzQ1Ng==",
    };
    //{headers_object}
    axios
      .post(tokenEndpoint, data,{headers:headers_object})
      .then((response) => {
        localStorage.setItem(
          "access_token",
          JSON.stringify(response.data.access_token)
        );
        router.replace("/");
        setTimeout(() => {
          document.location.reload();
        }, 500);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const gerarCode = () => {
    try {
      const codeVerifier = generateCodeVerifier();
      const codeChallenge = generateCodeChallenge(codeVerifier);
      //console.log("codeVerifier",codeVerifier)
     
      localStorage.setItem("code_verifier", codeVerifier);
      localStorage.setItem("codeChallenge", codeChallenge);

      let scope = "myuser:read myuser:write posts:write";

      let params = {
        response_type: "code",
        client_id: clientId,
        state: "12345",
        redirectUri: redirectUri,
        scope: scope,
        code_challenge: codeChallenge,
        code_challenge_method: "S256",
      };

      window.location.href =
        baseUrl + "/oauth2/authorize" + "?" + new URLSearchParams(params);
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

  const logout = () => {
    let token = JSON.parse(localStorage.getItem("access_token"))
    console.log("JSON.parse(localStorage.getItem('access_token'))",localStorage.getItem("access_token"))
      let baseUrlOauth = "http://172.20.1.15:8020";

      const data = new URLSearchParams({
      token: token});

      const basic_auth = "Basic YXdibG9nOjEyMzQ1Ng==";
      const headers = {
        "Content-Type": "application/x-www-form-urlencoded",
        Accept: "/",
        Authorization: basic_auth,
      };

      axios
      .post("http://172.20.1.15:8010" + '/oauth2/revoke', data,{ headers })
      .then((response) => {

      });

  


      localStorage.clear();

      
    
      window.location.href =  baseUrlOauth + '/auth2/logout-custom';
    
  

  };

  return { gerarToken, gerarCode, logout };
});
