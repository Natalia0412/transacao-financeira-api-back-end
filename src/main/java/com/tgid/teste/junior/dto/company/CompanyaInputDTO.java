package com.tgid.teste.junior.dto.company;

import com.tgid.teste.junior.anottation.Cnpj;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyaInputDTO {
    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name cannot be null")
    private String name;
    @Cnpj
    @NotBlank(message = "CNPJ cannot be blank")
    @NotNull(message = "CNPJ cannot be null")
    @Length(min = 14, max = 14, message = "The CNPJ must have 14 digits")
    @Pattern(regexp = "^[0-9]*$", message = "The CNPJ cannot contain special characters")
    private String cnpj;
    @NotBlank(message = "Email cannot be blank")
    @NotNull(message = "Email cannot be null")
    private String email;
    @NotNull(message = "Balance cannot be null")
    private Double balance;
    @NotNull(message = "Clients is mandatory")
    private List<Long> clientList;
    @NotNull(message = "Taxs is mandatory")
    private List<Long> taxList;

}
