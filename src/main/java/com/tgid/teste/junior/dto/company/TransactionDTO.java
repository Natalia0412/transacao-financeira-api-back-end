package com.tgid.teste.junior.dto.company;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    @NotNull(message = "CompanyId cannot be null")
    private Long companyId;
    @NotNull(message = "ClientId cannot be null")
    private Long clientId;
    @NotNull(message = "Amount cannot be null")
    private Double amount;

}
