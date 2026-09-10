package com.diogo.raizesdonordeste.repository;

import com.diogo.raizesdonordeste.domain.ProgramaFidelidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProgramaFidelidadeRepository extends JpaRepository<ProgramaFidelidade, UUID> {

    ProgramaFidelidade findByUsuario_Email(String email);
}
