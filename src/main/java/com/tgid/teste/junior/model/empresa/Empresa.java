package com.tgid.teste.junior.model.empresa;

import com.tgid.teste.junior.model.taxa.Taxa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@Table(name = "TB_EMPRESA", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cnpj;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Double saldo;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Taxa> taxaList;
}
