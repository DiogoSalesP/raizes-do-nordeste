package com.diogo.raizesdonordeste.repository;

import com.diogo.raizesdonordeste.domain.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EstoqueRepository extends JpaRepository<Estoque, UUID> {
    Estoque findByProduto_idProduto(UUID idProduto);
}
