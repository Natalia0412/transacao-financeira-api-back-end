package com.tgid.teste.junior.service;

import com.tgid.teste.junior.dto.client.ClientInputDTO;
import com.tgid.teste.junior.exception.ResourceConflictException;
import com.tgid.teste.junior.mapper.ClientMapper;
import com.tgid.teste.junior.model.Client;
import com.tgid.teste.junior.repository.ClientRepository;
import com.tgid.teste.junior.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private  final  ClientMapper clientMapper;

    public Client createClient(ClientInputDTO dto){
        if(this.isEmailAlreadyRegistered(dto.getEmail()).orElse(false)) {
            throw new ResourceConflictException("Email already exists.");
        }
        if(this.isCPFAlreadyRegistered(dto.getCpf()).orElse(false)) {
            throw new ResourceConflictException("CPF already exists.");
        }
        Client client =  ClientMapper.INSTANCE.ClientInputDTOToClient(dto);
        return clientRepository.save(client );
    }

    public List<Client> getAllClient(){
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id){
        return  clientRepository.findById(id);
    }

    public Client updateClient(Long id, ClientInputDTO dto){
        Client clientOld = this.getClientById(id).orElseThrow(()-> new  ResourceNotFoundException("Client not found"));;
        if(!dto.getEmail().equals(clientOld.getEmail())){
            if(this.isEmailAlreadyRegistered(dto.getEmail()).orElse(false)) {
                throw new ResourceConflictException("Email already exists.");
            }
        }else if(!dto.getCpf().equals(clientOld.getCpf())){
            if(this.isCPFAlreadyRegistered(dto.getCpf()).orElse(false)) {
                throw new ResourceConflictException("CPF already exists.");
            }
        }
        Client client = ClientMapper.INSTANCE.ClientInputDTOToClient(dto);
        client.setId(id);
        return clientRepository.save(client);
    }

    public void deleteClient(Long id){
        clientRepository.deleteById(id);
    }

    public Optional<Boolean> isEmailAlreadyRegistered(String email) {
        return clientRepository.existsByEmail(email);
    }

    public Optional<Boolean> isCPFAlreadyRegistered(String cpf) {
        return clientRepository.existsByCpf(cpf);
    }


}
