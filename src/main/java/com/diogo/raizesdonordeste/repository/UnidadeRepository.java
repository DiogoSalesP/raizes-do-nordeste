package com.diogo.raizesdonordeste.repository;

import com.diogo.raizesdonordeste.domain.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UnidadeRepository extends JpaRepository<Unidade, UUID> {
}
