package com.diogo.raizesdonordeste.service;

import com.diogo.raizesdonordeste.domain.Unidade;
import com.diogo.raizesdonordeste.dto.request.UnidadeRequestDTO;
import com.diogo.raizesdonordeste.exception.RegistroNaoEncontradoException;
import com.diogo.raizesdonordeste.mapper.UnidadeMapper;
import com.diogo.raizesdonordeste.repository.UnidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;

    public Unidade salvar(UnidadeRequestDTO dto) {
        Unidade unidade = UnidadeMapper.toEntity(dto);
        return unidadeRepository.save(unidade);
    }

    public Page<Unidade> buscarTodos(Integer pagina, Integer tamanhoPagina) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return unidadeRepository.findAll(pageRequest);
    }

    public Unidade buscarPorId(UUID id) {
        return unidadeRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Unidade", id));
    }

    public Unidade atualizar(UUID id, UnidadeRequestDTO dto) {
        Unidade unidade = buscarPorId(id);
        unidade.setNome(dto.nome());
        unidade.setEndereco(dto.endereco());
        unidade.setCidade(dto.cidade());
        unidade.setEstado(dto.estado());
        unidade.setTelefone(dto.telefone());
        return unidadeRepository.save(unidade);
    }

    public void deletar(UUID id) {
        Unidade unidade = buscarPorId(id);
        unidadeRepository.delete(unidade);
    }
}
