package com.tgid.teste.junior.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRespostaDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private List<Long> empresas;
}
