package com.diogo.raizesdonordeste.repository;

import com.diogo.raizesdonordeste.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    List<Produto> findByDisponivel(Boolean disponivel);

}
