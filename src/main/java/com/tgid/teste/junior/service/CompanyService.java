package com.tgid.teste.junior.service;

import com.tgid.teste.junior.dto.company.CompanyaInputDTO;
import com.tgid.teste.junior.exception.ResourceConflictException;
import com.tgid.teste.junior.exception.ResourceNotFoundException;
import com.tgid.teste.junior.mapper.CompanyMapper;
import com.tgid.teste.junior.model.Client;
import com.tgid.teste.junior.model.Company;
import com.tgid.teste.junior.model.Tax;
import com.tgid.teste.junior.repository.CompanyRepository;
import com.tgid.teste.junior.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ClientService clientService;
    private final TaxaService taxService;
    private final NotificationService notificationService;
    public Company createCompany(CompanyaInputDTO dto){
        if(this.isEmailAlreadyRegistered(dto.getEmail()).orElse(false)) {
            throw new ResourceConflictException("Email already exists.");
        }
        if(this.isCnpjAlreadyRegistered(dto.getCnpj()).orElse(false)) {
            throw new ResourceConflictException("CNPJ already exists.");
        }
        Company company = CompanyMapper.INSTANCE.CompanyInsertDTOTOCompany(dto);
        company.setClientList(this.addClient(dto));
        company.setTaxaList(this.addTax(dto));
        return companyRepository.save(company);
    }

    public List<Company> getAllCompany(){return companyRepository.findAll();}

    public Optional<Company> getCompanyById(Long id){
        return  companyRepository.findById(id);
    }

    public Company updateCompany(Long id, CompanyaInputDTO dto){
        Company companyOld = this.getCompanyById(id).orElseThrow(() -> new ResourceNotFoundException("Company not found" ));
        if(!dto.getEmail().equals(companyOld.getEmail())){
            if(this.isEmailAlreadyRegistered(dto.getEmail()).orElse(false)) {
                throw new ResourceConflictException("Email already exists.");
            }
        }else if(!dto.getCnpj().equals(companyOld.getCnpj())){
            if(this.isCnpjAlreadyRegistered(dto.getCnpj()).orElse(false)) {
                throw new ResourceConflictException("CNPJ already exists.");
            }
        }
        Company company = CompanyMapper.INSTANCE.CompanyInsertDTOTOCompany(dto);
        company.setClientList(this.addClient(dto));
        company.setTaxaList(this.addTax(dto));
        company.setId(id);
        return companyRepository.save(company);
    }

    public  void deleteCompany(Long id){
        companyRepository.deleteById(id);
    }

    public Optional<Boolean> isEmailAlreadyRegistered(String email) {
        return companyRepository.existsByEmail(email);
    }

    public Optional<Boolean> isCnpjAlreadyRegistered(String cnpj) {
        return companyRepository.existsByCnpj(cnpj);
    }

    public List<Client> addClient(CompanyaInputDTO dto){
        List<Client> clients = new ArrayList<>();
        dto.getClientList().forEach(clientID -> {
            Client client = clientService.getClientById(clientID).orElseThrow(()-> new ResourceNotFoundException("Client not found with id: " + clientID));
            clients.add(client);
        } );
        return clients;
    }

    public List<Tax> addTax(CompanyaInputDTO dto){
        List<Tax> taxs = new ArrayList<>();
        dto.getTaxList().forEach(taxID -> {
            Tax tax = taxService.getTaxById(taxID).orElseThrow(()->
                    new ResourceNotFoundException("Tax not found with id: "+ taxID));
            taxs.add(tax);
        } );
        return taxs;
    }

    public Company saveTransaction(Company company){
        return  companyRepository.save(company);
    }

}
