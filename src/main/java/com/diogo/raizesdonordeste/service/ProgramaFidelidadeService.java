package com.diogo.raizesdonordeste.service;

import com.diogo.raizesdonordeste.domain.ProgramaFidelidade;
import com.diogo.raizesdonordeste.domain.enums.NivelFidelidade;
import com.diogo.raizesdonordeste.dto.request.ProgramaFidelidadeRequestDTO;
import com.diogo.raizesdonordeste.exception.RegistroNaoEncontradoException;
import com.diogo.raizesdonordeste.repository.ProgramaFidelidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        return fidelidadeRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Programa de fidelidade", id));
    }

    public ProgramaFidelidade buscarPorEmail(String email) {
        return fidelidadeRepository.findByUsuario_Email(email);
    }

    public ProgramaFidelidade atualizar(String email, BigDecimal valorTotal) {
        ProgramaFidelidade fidelidade = buscarPorEmail(email);
        int novoSaldo = fidelidade.getSaldoPontos() + valorTotal.intValue();
        fidelidade.setSaldoPontos(novoSaldo);
        if (novoSaldo < 500) {
            fidelidade.setNivel(NivelFidelidade.BRONZE);
        } else if (novoSaldo < 1000) {
            fidelidade.setNivel(NivelFidelidade.PRATA);
        } else {
            fidelidade.setNivel(NivelFidelidade.OURO);
        }
        return fidelidadeRepository.save(fidelidade);
    }
}
