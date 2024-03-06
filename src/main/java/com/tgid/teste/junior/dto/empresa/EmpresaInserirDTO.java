package com.tgid.teste.junior.dto.empresa;

import com.tgid.teste.junior.utils.anottations.Cnpj;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaInserirDTO {
    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @Cnpj
    @Size(min = 14, max = 14, message = "O CNPJ deve ter 14 dígitos")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "O cnpj não pode conter caracteres especiais")
    @NotBlank(message = "O cnpj não pode estar em branco")
    private String cnpj;

    @NotBlank(message = "O email não pode estar em branco")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @NotNull(message = "O saldo não pode estar em branco")
    private Double saldo;
}
