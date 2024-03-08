package com.tgid.teste.junior.controller;

import com.tgid.teste.junior.dto.taxa.TaxInputDTO;
import com.tgid.teste.junior.exception.ResourceNotFoundException;
import com.tgid.teste.junior.model.Tax;
import com.tgid.teste.junior.service.TaxaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tax")

public class TaxController {
    private final TaxaService taxService;

    @PostMapping()
    public ResponseEntity<Tax> insertTax(@Valid @RequestBody TaxInputDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(taxService.createTax(dto));
    }

    @GetMapping()
    public ResponseEntity<List<Tax>> getAllTax(){
        return ResponseEntity.status(HttpStatus.OK).body(taxService.getAllTax());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tax> getTaxById(@PathVariable Long id){
        Tax tax = taxService.getTaxById(id).orElseThrow(()->
                new ResourceNotFoundException("Tax not found"));
        return ResponseEntity.status(HttpStatus.OK).body(tax);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tax> updateTax(@PathVariable Long id,@Valid @RequestBody TaxInputDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(taxService.updateTax(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaxById(@PathVariable Long id){
        taxService.deleteTax(id);
        return ResponseEntity.noContent().build();
    }

}
