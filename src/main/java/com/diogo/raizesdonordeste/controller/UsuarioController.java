package com.diogo.raizesdonordeste.controller;

import com.diogo.raizesdonordeste.domain.Usuario;
import com.diogo.raizesdonordeste.dto.request.AtualizarUsuarioRequestDTO;
import com.diogo.raizesdonordeste.dto.request.UsuarioRequestDTO;
import com.diogo.raizesdonordeste.dto.request.UsuarioRolesRequestDTO;
import com.diogo.raizesdonordeste.dto.response.UsuarioResponseDTO;
import com.diogo.raizesdonordeste.mapper.UsuarioMapper;
import com.diogo.raizesdonordeste.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public UsuarioResponseDTO criarUsuario(@RequestBody @Valid UsuarioRequestDTO dto) {
        Usuario usuario = usuarioService.criar(dto);
        return UsuarioMapper.toResponse(usuario);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    public Page<UsuarioResponseDTO> buscarTodosUsuarios(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        Page<Usuario> usuarios = usuarioService.buscarTodos(pagina, tamanhoPagina);
        return usuarios.map(UsuarioMapper::toResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    public UsuarioResponseDTO buscarUsuarioPorId(@PathVariable UUID id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return UsuarioMapper.toResponse(usuario);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    public UsuarioResponseDTO atualizarUsuarioPorId(@PathVariable UUID id, @RequestBody @Valid AtualizarUsuarioRequestDTO dto) {
        Usuario usuario = usuarioService.atualizar(id, dto);
        return UsuarioMapper.toResponse(usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('GERENTE')")
    public void deletarUsuarioPorId(@PathVariable UUID id) {
        usuarioService.deletar(id);
    }

    @PutMapping("/roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GERENTE')")
    public UsuarioResponseDTO atualizarRoles(@PathVariable UUID id, @RequestBody UsuarioRolesRequestDTO roles) {
        Usuario usuario = usuarioService.atualizarRoles(id, roles);
        return UsuarioMapper.toResponse(usuario);
    }
}
