package com.tgid.teste.junior.controller;

import com.tgid.teste.junior.dto.company.TransactionDTO;
import com.tgid.teste.junior.model.Company;
import com.tgid.teste.junior.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor

public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<Company> toDeposit(@Valid @RequestBody TransactionDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.ToDeposit(dto));
    }

    @PostMapping("/withdrawn")
    public ResponseEntity<Company> toWithdrawn(@Valid @RequestBody TransactionDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.withdrawn(dto));
    }

}
