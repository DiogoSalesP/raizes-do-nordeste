package com.diogo.raizesdonordeste.repository;

import com.diogo.raizesdonordeste.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {
    Pagamento findByPedido_IdPedido(UUID idPedido);
}
