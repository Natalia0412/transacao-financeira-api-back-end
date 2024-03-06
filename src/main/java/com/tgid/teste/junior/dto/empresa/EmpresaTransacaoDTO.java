package com.tgid.teste.junior.dto.empresa;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpresaTransacaoDTO {
    private Double valor;
    private Long Taxa;
}
