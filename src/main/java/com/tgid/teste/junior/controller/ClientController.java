package com.tgid.teste.junior.controller;

import com.tgid.teste.junior.dto.client.ClientInputDTO;
import com.tgid.teste.junior.exception.ResourceNotFoundException;
import com.tgid.teste.junior.model.Client;
import com.tgid.teste.junior.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/client")

public class ClientController {
    private final ClientService clientService;
    @PostMapping()
    public ResponseEntity<Client> insertClient(@Valid @RequestBody  ClientInputDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(dto));
    }

    @GetMapping()
    public ResponseEntity<List<Client>> getAllClient(){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getAllClient());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id){
        Client client = clientService.getClientById(id).orElseThrow(()-> new ResourceNotFoundException("Client not found"));
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @Valid @RequestBody ClientInputDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.updateClient(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }


}
