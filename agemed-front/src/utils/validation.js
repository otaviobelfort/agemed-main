import { validaCpfCnpj } from "./validateCpfOrCnpj"

export const rules = {
    required: [v => !!v || "Este campo é obrigatório!"],
    email: [v => (/.+@.+\..+/.test(v)) || 'Este email não é válido!'],
    phone: [v => (v?.length > 9 && /[0-9-]+/.test(v)) || 'O número de telefone precisa ter pelo menos 9 dígitos'],
    number: [v => (/[^0-9]/.test(v)) || 'Somente números'],
    vCpf: [v => validaCpfCnpj(v) || 'CPF Inválido'],
    min10: [v => v?.length >= 10 || "No mínimo 10 caracteres"],
    max10: [v => v?.length <= 10 || "No mínimo 10 caracteres"],
    select: [v => !!v || "Selecione um item"]
}