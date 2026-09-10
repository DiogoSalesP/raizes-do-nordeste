package com.diogo.raizesdonordeste.domain;

import com.diogo.raizesdonordeste.domain.enums.NivelFidelidade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "programa_fidelidade")
public class ProgramaFidelidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_fidelidade")
    private UUID idFidelidade;

    @Column(name = "saldo_pontos", nullable = false)
    private Integer saldoPontos;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel", nullable = false)
    private NivelFidelidade nivel;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;
}
