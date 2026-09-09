package com.diogo.raizesdonordeste.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "unidade")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_unidade")
    private UUID idUnidade;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "endereco", nullable = false, length = 100)
    private String endereco;

    @Column(name = "cidade", nullable = false, length = 100)
    private String cidade;

    @Column(name = "estado", nullable = false, length = 2)
    private String estado;

    @Column(name = "telefone", nullable = false, length = 11)
    private String telefone;

    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> produtos;

//    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Usuario> funcionarios;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
