package com.tgid.teste.junior.dto.taxa;

import com.tgid.teste.junior.model.TaxEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxaRespostaDTO extends TaxInputDTO {
    private Long id;
    private TaxEnum tipo;
    private Double valor;
    private Long empresaId;
}
