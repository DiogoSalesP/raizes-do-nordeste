package com.diogo.raizesdonordeste.service;

import com.diogo.raizesdonordeste.domain.ProgramaFidelidade;
import com.diogo.raizesdonordeste.domain.Usuario;
import com.diogo.raizesdonordeste.domain.enums.NivelFidelidade;
import com.diogo.raizesdonordeste.dto.request.AtualizarUsuarioRequestDTO;
import com.diogo.raizesdonordeste.dto.request.UsuarioRequestDTO;
import com.diogo.raizesdonordeste.dto.request.UsuarioRolesRequestDTO;
import com.diogo.raizesdonordeste.exception.RegistroNaoEncontradoException;
import com.diogo.raizesdonordeste.mapper.UsuarioMapper;
import com.diogo.raizesdonordeste.repository.UsuarioRepository;
import com.diogo.raizesdonordeste.validator.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioValidator usuarioValidator;


    public Usuario criar(UsuarioRequestDTO dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        usuarioValidator.validarCriar(usuario);
        ProgramaFidelidade programaFidelidade = new ProgramaFidelidade();
        programaFidelidade.setNivel(NivelFidelidade.BRONZE);
        programaFidelidade.setSaldoPontos(0);
        programaFidelidade.setUsuario(usuario);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setRoles(new String[]{"GERENTE"});
        usuario.setProgramaFidelidade(programaFidelidade);
        return usuarioRepository.save(usuario);
    }

    public Page<Usuario> buscarTodos(Integer pagina, Integer tamanhoPagina) {
        Pageable pageable = PageRequest.of(pagina, tamanhoPagina);
        return usuarioRepository.findAll(pageable);
    }

    public Usuario buscarPorId(UUID id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Usuário", id));
    }

    public Usuario obterPorLogin(String login) {
        return usuarioRepository.findByEmail(login);
    }

    public Usuario atualizar(UUID id, AtualizarUsuarioRequestDTO dto) {
        Usuario usuario = buscarPorId(id);
        usuarioValidator.validarAtualizar(id, dto.email());
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setTelefone(dto.telefone());
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarRoles(UUID id, UsuarioRolesRequestDTO roles) {
        Usuario usuario = buscarPorId(id);
        usuario.setRoles(roles.roles());
        return usuarioRepository.save(usuario);
    }

    public void deletar(UUID id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }

}
