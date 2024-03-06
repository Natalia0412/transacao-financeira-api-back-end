package com.tgid.teste.junior.dto.taxa;

import com.tgid.teste.junior.model.empresa.Empresa;
import com.tgid.teste.junior.model.taxa.enums.TaxaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaxaInsertDTO {
    @NotBlank(message = "O tipo não pode estar em branco")
    private TaxaEnum tipo;
    @NotNull(message = "O valor não pode estar em branco")
    private Double valor;
    @NotNull(message = "O valor não pode estar em branco")
    private Long empresaId;
}
