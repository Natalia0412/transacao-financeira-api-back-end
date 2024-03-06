package com.tgid.teste.junior.controller.cliente;

import com.tgid.teste.junior.dto.cliente.ClienteInserirDTO;
import com.tgid.teste.junior.dto.cliente.ClienteRespostaDTO;
import com.tgid.teste.junior.model.cliente.Cliente;
import com.tgid.teste.junior.service.cliente.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @PostMapping()
    public ResponseEntity<ClienteRespostaDTO> inserirCliente(@RequestBody @Valid ClienteInserirDTO clienteDto){
        ClienteRespostaDTO dtoRes = clienteService.criarCliente(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoRes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteRespostaDTO> mostrarCliente(@PathVariable Long id) {
        ClienteRespostaDTO clienteRespostaDTO = clienteService.trazerClientePorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(clienteRespostaDTO);
    }



    @GetMapping()
    public ResponseEntity<List<ClienteRespostaDTO>> mostrarCliente() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.trazerTodosClientes());
    }


}
