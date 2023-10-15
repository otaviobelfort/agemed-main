export const tabvalue = name => {
    let router = {
        "home": 0,
        "solicitacoes": 1,
        "medico": 5,
        "emissao": 2,
        "rg-nacional": 3,
        "": 4,
    }
    return router[name];
}