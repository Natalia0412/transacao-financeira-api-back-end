package com.tgid.teste.junior.utils.mapper.cliente;

import com.tgid.teste.junior.dto.cliente.ClienteInserirDTO;
import com.tgid.teste.junior.dto.cliente.ClienteRespostaDTO;
import com.tgid.teste.junior.model.cliente.Cliente;
import com.tgid.teste.junior.model.empresa.Empresa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {
    public static Cliente clienteDTOParaCliente(ClienteInserirDTO dto) {
        return Cliente.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .build();
    }

    public static List<ClienteRespostaDTO> clienteParaClienteRespostaDTO(List<Cliente> clientes){
        List<ClienteRespostaDTO> dto = new ArrayList<>();
        clientes.forEach(cliente -> {
            dto.add(clienteParaClienteRespostaDTO(cliente));
        });
        return dto;
    }

    public static ClienteRespostaDTO clienteParaClienteRespostaDTO(Cliente cliente){
        return ClienteRespostaDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .email(cliente.getEmail())
                .empresas(cliente.getEmpresas().stream().map(Empresa::getId).collect(Collectors.toList()))
                .build();
    }



}
