package com.tgid.teste.junior.service.taxa;

import com.tgid.teste.junior.dto.taxa.TaxaInsertDTO;
import com.tgid.teste.junior.dto.taxa.TaxaRespostaDTO;
import com.tgid.teste.junior.model.empresa.Empresa;
import com.tgid.teste.junior.model.taxa.Taxa;
import com.tgid.teste.junior.repository.empresa.EmpresaRepository;
import com.tgid.teste.junior.repository.taxa.TaxaRepository;
import com.tgid.teste.junior.utils.exception.ResourceNotFoundException;
import com.tgid.teste.junior.utils.mapper.taxa.TaxaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaxaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private TaxaRepository taxaRepository;

    public TaxaRespostaDTO criarTaxa(TaxaInsertDTO taxaDto){
        Taxa taxa = TaxaMapper.taxaDTOParaTaxa(taxaDto);
        Empresa empresa = empresaRepository.findById(taxaDto.getEmpresaId())
                .orElseThrow(RuntimeException::new);
        taxa.setEmpresa(empresa);
        taxaRepository.save(taxa);
        return  TaxaMapper.taxaParaTaxaRespostaDTO(taxa);
    }
    public List<TaxaRespostaDTO> trazerTodasTaxa(){

        return   TaxaMapper.taxaParaTaxaRespostaDTO(taxaRepository.findAll());
    }

    public Taxa retornarTaxa(Long id) {
        Optional<Taxa> obj = taxaRepository.findById(id);
        if(!obj.isPresent()) throw new ResourceNotFoundException("Taxa não encontrado");
        return obj.get();
    }

    public TaxaRespostaDTO retornarTaxaPorId(Long id) {
        Taxa taxa = taxaRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Taxa não encontrado");
        });
        return TaxaMapper.taxaParaTaxaRespostaDTO(taxa);

    }

}
