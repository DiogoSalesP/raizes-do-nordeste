package com.diogo.raizesdonordeste.controller;

import com.diogo.raizesdonordeste.domain.Usuario;
import com.diogo.raizesdonordeste.dto.request.UsuarioRequestDTO;
import com.diogo.raizesdonordeste.dto.response.UsuarioResponseDTO;
import com.diogo.raizesdonordeste.mapper.UsuarioMapper;
import com.diogo.raizesdonordeste.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO criarUsuario(@RequestBody UsuarioRequestDTO dto) {
        Usuario usuario = usuarioService.criar(dto);
        return UsuarioMapper.toResponse(usuario);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioResponseDTO> buscarTodosUsuarios() {
        return usuarioService.buscarTodos().stream()
                .map(UsuarioMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDTO buscarUsuarioPorId(@PathVariable UUID id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return UsuarioMapper.toResponse(usuario);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDTO atualizarUsuarioPorId(@PathVariable UUID id, @RequestBody UsuarioRequestDTO dto) {
        Usuario usuario = usuarioService.atualizar(id, dto);
        return UsuarioMapper.toResponse(usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuarioPorId(@PathVariable UUID id) {
        usuarioService.deletar(id);
    }

}
