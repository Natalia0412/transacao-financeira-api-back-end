package com.tgid.teste.junior.controller;

import com.tgid.teste.junior.dto.company.CompanyaInputDTO;
import com.tgid.teste.junior.dto.company.EmpresaRespostaDTO;
import com.tgid.teste.junior.dto.company.TransactionDTO;
import com.tgid.teste.junior.exception.ResourceNotFoundException;
import com.tgid.teste.junior.model.Company;
import com.tgid.teste.junior.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/company")

public class CompanyController {
    private final CompanyService companyService;

    @PostMapping()
    public ResponseEntity<Company> insertCompany(@Valid @RequestBody CompanyaInputDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.createCompany(dto));
    }

    @GetMapping()
    public ResponseEntity<List<Company>> getAllCompany(){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getAllCompany());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getAllCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID" ));
        return ResponseEntity.ok().body(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @Valid @RequestBody CompanyaInputDTO dto){
        return  ResponseEntity.status(HttpStatus.OK).body(companyService.updateCompany(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }



}
