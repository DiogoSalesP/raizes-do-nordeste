package com.diogo.raizesdonordeste.mapper;

import com.diogo.raizesdonordeste.domain.Usuario;
import com.diogo.raizesdonordeste.dto.request.UsuarioRequestDTO;
import com.diogo.raizesdonordeste.dto.response.UsuarioResponseDTO;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setTelefone(dto.telefone());
        return usuario;
    }

    public static UsuarioResponseDTO toResponse(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getCadastro()
        );
    }
}
