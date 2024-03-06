package com.tgid.teste.junior.dto.taxa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tgid.teste.junior.model.taxa.enums.TaxaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxaRespostaDTO extends TaxaInsertDTO{
    private Long id;
    private TaxaEnum tipo;
    private Double valor;
    private Long empresaId;
}
