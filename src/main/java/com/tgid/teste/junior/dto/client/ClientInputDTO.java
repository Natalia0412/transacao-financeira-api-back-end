package com.tgid.teste.junior.dto.client;

import com.tgid.teste.junior.anottation.Cpf;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientInputDTO {
    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name cannot be null")
    private String name;
    @Cpf
    @NotBlank(message = "CPF cannot be blank")
    @NotNull(message = "CPF cannot be null")
    @Length(min = 11, max = 11, message = "The CPF must have 11 digits")
    @Pattern(regexp = "^[0-9]*$", message = "The CPF cannot contain special characters")
    private String cpf;
    @NotBlank(message = "Email cannot be blank")
    @NotNull(message = "Email cannot be null")
    private String email;

}
