package com.age.med.controller;
import com.age.med.domain.Usuario;
import com.age.med.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.criarUsuario(usuario);
    }

    @GetMapping("/lista")
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> obterTodosUsuarios() {
        return usuarioService.obterTodosUsuarios();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario obterUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obterUsuarioPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioService.atualizarUsuario(id, usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirUsuario(@PathVariable Long id) {
        usuarioService.excluirUsuario(id);
    }
}
