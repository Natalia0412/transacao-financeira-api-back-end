package com.tgid.teste.junior.service;

import com.tgid.teste.junior.dto.company.TransactionDTO;
import com.tgid.teste.junior.exception.ResourceInsufficientException;
import com.tgid.teste.junior.exception.ResourceNotFoundException;
import com.tgid.teste.junior.model.Client;
import com.tgid.teste.junior.model.Company;
import com.tgid.teste.junior.model.TaxEnum;
import com.tgid.teste.junior.service.notification.CallbackSender;
import com.tgid.teste.junior.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final CompanyService companyService;
    private final ClientService clientService;
    private final NotificationService notificationService;


    public Company ToDeposit(TransactionDTO dto) {
        Company company = companyService.getCompanyById(dto.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        Client client = clientService.getClientById(dto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        if (!company.getClientList().contains(client)) {
            throw new ResourceNotFoundException("Client does not belong to the specified company");
        }
        AtomicReference<Double> feeAmount = new AtomicReference<>(0.0);
        company.getTaxaList().forEach(tax -> {
            if(tax.getType() == TaxEnum.DEPOSIT){
                feeAmount.updateAndGet(v -> v + tax.getFeeAmount());
            }
        });
        if (feeAmount.get() > dto.getAmount()) {
            throw new ResourceInsufficientException("Insufficient amount to cover the fee.");
        }
        double newBalance = company.getBalance() + dto.getAmount() - feeAmount.get();
        company.setBalance(newBalance);
        Company updatedCompany = companyService.saveTransaction(company);
        String type ="DEPOSIT";
        CallbackSender.CallbackData(updatedCompany, dto,type );
        notificationService.test(client,updatedCompany, feeAmount, dto.getAmount(),type);
        return updatedCompany;
    }

    public Company withdrawn(TransactionDTO dto) {
        Company company = companyService.getCompanyById(dto.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        Client client = clientService.getClientById(dto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        if (!company.getClientList().contains(client)) {
            throw new ResourceNotFoundException("Client does not belong to the specified company");
        }
        AtomicReference<Double> feeAmount = new AtomicReference<>(0.0);
        company.getTaxaList().forEach(tax -> {
            if(tax.getType() == TaxEnum.WITHDRAWAL){
                feeAmount.updateAndGet(v -> v + tax.getFeeAmount());
            }
        });
        double processedAdministrativeFee = company.getBalance() - feeAmount.get();
        if (processedAdministrativeFee < dto.getAmount()) {
            throw new ResourceInsufficientException("Insufficient balance");
        }
        company.setBalance(processedAdministrativeFee - dto.getAmount());
        Company updatedCompany = companyService.saveTransaction(company);
        String type = "WITHDRAWAL";
        CallbackSender.CallbackData(updatedCompany, dto, type);
        notificationService.test(client,updatedCompany, feeAmount,dto.getAmount(), type);
        return updatedCompany;
    }

}
