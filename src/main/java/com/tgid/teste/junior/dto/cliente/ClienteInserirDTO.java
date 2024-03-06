package com.tgid.teste.junior.dto.cliente;

import com.tgid.teste.junior.utils.anottations.Cpf;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@NoArgsConstructor
public class ClienteInserirDTO {
    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;
    @Cpf
    @NotBlank(message = "O CPF não pode estar em branco")
    @Length(min = 11, max = 11, message = "O CPF deve ter 11 dígitos")
    @Pattern(regexp = "^[0-9]*$", message = "O CPF não pode conter caracteres especiais")
    private String cpf;
    @NotBlank(message = "O email não pode estar em branco")
    @Email(message = "Formato de e-mail inválido")
    private String email;
    private List<Long> empresas;
}
