
class TokenService {
    
  tokenValido(token) {
    if (token) {
      const tokenFormatado = this.parseJwt(token);
      const expEmSegundos = tokenFormatado.exp * 1000;


      if (expEmSegundos >= new Date().getTime()) {
        return true;
      }
      return false;
    }
    return false;
  }


  parseJwt(token) {
    const prefixo = "Bearer ";
    if (token?.startsWith(prefixo)) {
      token = token.substring(0, prefixo.length);
    }
    const base64Url = token?.split(".")[1];
    const base64 = base64Url?.replace(/-/g, "+").replace(/_/g, "/");
    const jsonPayload = decodeURIComponent(
      window
        .atob(base64)
        .split("")
        .map(function (c) {
          return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
        })
        .join("")
    );
    return JSON.parse(jsonPayload);
  }
}

export default new TokenService();
