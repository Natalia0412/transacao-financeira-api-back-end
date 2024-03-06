package com.tgid.teste.junior.controller.taxa;

import com.tgid.teste.junior.dto.cliente.ClienteRespostaDTO;
import com.tgid.teste.junior.dto.taxa.TaxaInsertDTO;
import com.tgid.teste.junior.dto.taxa.TaxaRespostaDTO;
import com.tgid.teste.junior.model.taxa.Taxa;
import com.tgid.teste.junior.service.taxa.TaxaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/taxa")
public class TaxaController {
    @Autowired
    private TaxaService taxaService;
    @PostMapping()
    public ResponseEntity<TaxaRespostaDTO> inserirTaxa(@RequestBody TaxaInsertDTO taxaInsertDTO){
        TaxaRespostaDTO dtoRes= taxaService.criarTaxa(taxaInsertDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(dtoRes);
    }
    
    @GetMapping()
    public ResponseEntity<List<TaxaRespostaDTO>> listarTaxa(){

        return ResponseEntity.status(HttpStatus.OK).body(taxaService.trazerTodasTaxa());
    }



    @GetMapping("/{id}")
    public ResponseEntity<TaxaRespostaDTO> buscarTaxaPorId(@PathVariable Long id){
        TaxaRespostaDTO dto = taxaService.retornarTaxaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
