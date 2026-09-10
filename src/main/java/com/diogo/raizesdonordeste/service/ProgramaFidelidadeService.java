package com.diogo.raizesdonordeste.service;

import com.diogo.raizesdonordeste.domain.ProgramaFidelidade;
import com.diogo.raizesdonordeste.dto.request.ProgramaFidelidadeRequestDTO;
import com.diogo.raizesdonordeste.repository.ProgramaFidelidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProgramaFidelidadeService {

    private final ProgramaFidelidadeRepository fidelidadeRepository;

    public List<ProgramaFidelidade> buscarTodos() {
        return fidelidadeRepository.findAll();
    }

    public ProgramaFidelidade buscarPorId(UUID id) {
        return fidelidadeRepository.findById(id).orElse(null);
    }

    public ProgramaFidelidade buscarPorEmail(String email) {
        return fidelidadeRepository.findByUsuario_Email(email);
    }

    public ProgramaFidelidade atualizar(UUID id, ProgramaFidelidadeRequestDTO dto) {
        ProgramaFidelidade fidelidade = buscarPorId(id);
        fidelidade.setNivel(dto.nivel());
        fidelidade.setSaldoPontos(dto.saldoPontos());
        return fidelidadeRepository.save(fidelidade);
    }
}
