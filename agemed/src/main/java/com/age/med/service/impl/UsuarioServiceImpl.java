package com.age.med.service.impl;

import com.age.med.domain.Usuario;
import com.age.med.repository.UsuarioRepository;
import com.age.med.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obterTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obterUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + id));
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = obterUsuarioPorId(id);

        usuarioExistente.setCpf(usuarioAtualizado.getCpf());
        usuarioExistente.setSenha(usuarioAtualizado.getSenha());
        usuarioExistente.setSenhaTemporaria(usuarioAtualizado.getSenhaTemporaria());
        usuarioExistente.setAtivo(usuarioAtualizado.getAtivo());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setPessoa(usuarioAtualizado.getPessoa());

        return usuarioRepository.save(usuarioExistente);
    }

    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
