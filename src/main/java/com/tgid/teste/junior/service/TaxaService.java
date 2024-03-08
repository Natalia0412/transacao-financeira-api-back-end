package com.tgid.teste.junior.service;

import com.tgid.teste.junior.dto.taxa.TaxInputDTO;
import com.tgid.teste.junior.mapper.TaxMapper;
import com.tgid.teste.junior.model.Tax;
import com.tgid.teste.junior.repository.TaxRepository;
import com.tgid.teste.junior.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class TaxaService {
    private  final TaxRepository taxRepository;

    public Tax createTax(TaxInputDTO dto){
        return taxRepository.save(TaxMapper.INSTANCE.TaxInsertDTOToTax(dto));
    }

    public List<Tax>getAllTax(){
        return taxRepository.findAll();
    }

    public Optional<Tax> getTaxById(Long id){
        return taxRepository.findById(id);
    }

    public Tax updateTax(Long id, TaxInputDTO dto){
        Tax taxOld = this.getTaxById(id).orElseThrow(()->
                new ResourceNotFoundException("Tax not found"));
        Tax tax = TaxMapper.INSTANCE.TaxInsertDTOToTax(dto);
        tax.setId(id);
        return taxRepository.save(tax);
    }

    public void deleteTax(Long id) { taxRepository.deleteById(id);}

}
