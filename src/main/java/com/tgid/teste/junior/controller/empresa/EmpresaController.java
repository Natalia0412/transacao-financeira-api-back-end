package com.tgid.teste.junior.controller.empresa;

import com.tgid.teste.junior.dto.empresa.EmpresaInserirDTO;
import com.tgid.teste.junior.dto.empresa.EmpresaRespostaDTO;
import com.tgid.teste.junior.dto.empresa.EmpresaTransacaoDTO;
import com.tgid.teste.junior.model.empresa.Empresa;
import com.tgid.teste.junior.service.empresa.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empresa")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;
    @PostMapping()
    public ResponseEntity<EmpresaRespostaDTO> inserirEmpresa(@RequestBody @Valid EmpresaInserirDTO empresaInserirDTO) {
        EmpresaRespostaDTO dtoRes = empresaService.criarEmpresa(empresaInserirDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoRes);
    }

    @GetMapping("{empresaId}")
    public ResponseEntity<EmpresaRespostaDTO> buscarEmpresaPorID(@PathVariable Long empresaId) {
        EmpresaRespostaDTO dtoRes = empresaService.retornarEmpresa(empresaId);
        return ResponseEntity.status(HttpStatus.OK).body(dtoRes);
    }



    @GetMapping()
    public ResponseEntity<List<EmpresaRespostaDTO>> buscarEmpresas() {
        return ResponseEntity.status(HttpStatus.OK).body(empresaService.listarTodasEmpresas());
    }



    @PostMapping("/{empresaId}/depositar")
    public Empresa depositar(@PathVariable Long empresaId, @RequestBody EmpresaTransacaoDTO dto) {
        return empresaService.depositar(empresaId, dto);
    }

    @PostMapping("/{empresaId}/sacar")
    public Empresa sacar(@PathVariable Long empresaId, @RequestBody EmpresaTransacaoDTO dto) {
        return empresaService.sacar(empresaId, dto);
    }
}
