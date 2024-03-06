package com.tgid.teste.junior.model.taxa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tgid.teste.junior.model.empresa.Empresa;
import com.tgid.teste.junior.model.taxa.enums.TaxaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "TB_TAXA", schema = "public")
public class Taxa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    private TaxaEnum tipo;
    @Column
    private Double valor;
    @ManyToOne()
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

}
