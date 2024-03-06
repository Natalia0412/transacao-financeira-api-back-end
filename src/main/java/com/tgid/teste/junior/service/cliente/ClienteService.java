package com.tgid.teste.junior.service.cliente;

import com.tgid.teste.junior.dto.cliente.ClienteInserirDTO;
import com.tgid.teste.junior.dto.cliente.ClienteRespostaDTO;
import com.tgid.teste.junior.model.cliente.Cliente;
import com.tgid.teste.junior.model.empresa.Empresa;
import com.tgid.teste.junior.repository.cliente.ClienteRepository;
import com.tgid.teste.junior.service.empresa.EmpresaService;
import com.tgid.teste.junior.utils.exception.ResourceConflict;
import com.tgid.teste.junior.utils.exception.ResourceNotFoundException;
import com.tgid.teste.junior.utils.mapper.cliente.ClienteMapper;
import com.tgid.teste.junior.utils.mapper.empresa.EmpresaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final EmpresaService empresaService;
    @Autowired
    private ClienteService (@Lazy EmpresaService empresaService, ClienteRepository clienteRepository){
        this.clienteRepository= clienteRepository;
        this.empresaService = empresaService;
    }


    public ClienteRespostaDTO criarCliente(ClienteInserirDTO clienteDto){
        if (this.verificarCpfExistente(clienteDto.getCpf())) {
            throw new ResourceConflict("CPF já cadastrado.");
        }

        clienteDto.getEmpresas().forEach(empresaId -> {
            if (!empresaService.verificarEmpresaExistente(empresaId)) {
                throw new ResourceNotFoundException("Empresa não encontrada");
            }
        });

        Cliente cliente = ClienteMapper.clienteDTOParaCliente(clienteDto);
        List<Empresa> empresaList = clienteDto.getEmpresas().stream().map(id -> {
            return EmpresaMapper.empresaDTOParaEmpresa(empresaService.retornarEmpresa(id));
        }).collect(Collectors.toList());

        cliente.setEmpresas(empresaList);
        clienteRepository.save(cliente);

        return  ClienteMapper.clienteParaClienteRespostaDTO(cliente);
    }

    public boolean verificarCpfExistente(String cpf){
        return clienteRepository.existsByCpf(cpf);
    }

    public Cliente retornarCliente(Long id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        if(!obj.isPresent()) throw new ResourceNotFoundException("Cliente não encontrado");
        return obj.get();
    }
    public ClienteRespostaDTO trazerClientePorId(Long id){
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return ClienteMapper.clienteParaClienteRespostaDTO(cliente);
    }



    public List<ClienteRespostaDTO> trazerTodosClientes(){
        return ClienteMapper.clienteParaClienteRespostaDTO(clienteRepository.findAll());
    }

}
