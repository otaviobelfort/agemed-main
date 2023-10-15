package com.age.med.service;

import com.age.med.domain.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario criarUsuario(Usuario usuario);

    List<Usuario> obterTodosUsuarios();

    Usuario obterUsuarioPorId(Long id);

    Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado);

    void excluirUsuario(Long id);
}
