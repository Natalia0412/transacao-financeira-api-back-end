package com.tgid.teste.junior.dto.taxa;

import com.tgid.teste.junior.model.TaxEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaxInputDTO {
    @Enumerated(EnumType.ORDINAL)
    @NotNull(message = "type cannot be blank, types can be DEPOSIT or WITHDRAWAL")
    private TaxEnum type;
    @NotNull(message = "FeeAmount cannot be null")
    private Double feeAmount;

}
