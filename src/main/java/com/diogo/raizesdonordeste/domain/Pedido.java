package com.diogo.raizesdonordeste.domain;

import com.diogo.raizesdonordeste.domain.enums.CanalPedido;
import com.diogo.raizesdonordeste.domain.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_pedido")
    private UUID idPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "canal_pedido", nullable = false)
    private CanalPedido canalPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusPedido status;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Usuario cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    public void adicionaItem(ItemPedido item) {
        itens.add(item);
        item.setPedido(this);
    }

    public void calculaValorTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemPedido item : itens) {
            BigDecimal subtotal = item
                    .getPrecoUnitario()
                    .multiply(BigDecimal.valueOf(item.getQuantidade()));

            total = total.add(subtotal);
        }
        this.valorTotal = total;
    }

}
