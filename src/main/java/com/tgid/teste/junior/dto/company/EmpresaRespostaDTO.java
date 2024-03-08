package com.tgid.teste.junior.dto.company;

import com.tgid.teste.junior.dto.taxa.TaxaRespostaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaRespostaDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private String email;
    private Double saldo;
    private List<TaxaRespostaDTO> taxas;
}
