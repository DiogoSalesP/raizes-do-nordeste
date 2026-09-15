package com.diogo.raizesdonordeste.service;

import com.diogo.raizesdonordeste.domain.Unidade;
import com.diogo.raizesdonordeste.dto.request.UnidadeRequestDTO;
import com.diogo.raizesdonordeste.exception.RegistroNaoEncontradoException;
import com.diogo.raizesdonordeste.mapper.UnidadeMapper;
import com.diogo.raizesdonordeste.repository.UnidadeRepository;
import lombok.RequiredArgsConstructor;
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

    public List<Unidade> buscarTodos() {
        return unidadeRepository.findAll();
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
