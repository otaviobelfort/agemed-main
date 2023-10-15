
const home = [
    {
        title: 'Página Início',
        disabled: true,
        to: '/',
        icon: 'mdi-home',
    }
]

const solicitacao = [
    {
        title: 'Página Início',
        disabled: false,
        to: '/',
        icon: 'mdi-home'
    },
    {
        title: 'Agendamentos',
        disabled: true,
        icon: 'mdi-cogs'
    }
]

const emissao = [
    {
        title: 'Página Início',
        disabled: false,
        to: '/',
        icon: 'mdi-home'
    },
    {
        title: 'Emissão ',
        disabled: true,
        icon: 'mdi-card-account-details'
    }
]

const medico = [
    {
        title: 'Página Início',
        disabled: false,
        to: '/',
        icon: 'mdi-home'
    },
    {
        title: 'Médico',
        disabled: true,
        icon: 'mdi-card-account-details'
    }
]
const posto = [
    {
        title: 'Página Início',
        disabled: false,
        to: '/',
        icon: 'mdi-home'
    },
    {
        title: 'Posto',
        disabled: true,
        icon: 'mdi-post'
    }
]

const rgNacional = [
    {
        title: 'Página Início',
        disabled: false,
        to: '/',
        icon: 'mdi-home'
    },
    {
        title: 'RG Nacional',
        disabled: true,
        icon: 'mdi-card-account-details'
    }
]

const rgEstadual = [
    {
        title: 'Página Início',
        disabled: false,
        to: '/',
        icon: 'mdi-home'
    },
    {
        title: 'RG Estadual',
        disabled: true,
        icon: 'mdi-card-account-details'
    }
]

export const items = {
    home: home,
    solicitacoes: solicitacao,
    emissao: emissao,
    medico:medico,

    "rg-nacional": rgNacional,
    "rg-cadastro": rgEstadual
}
