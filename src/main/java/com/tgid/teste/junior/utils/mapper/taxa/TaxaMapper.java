package com.tgid.teste.junior.utils.mapper.taxa;

import com.tgid.teste.junior.dto.taxa.TaxaInsertDTO;
import com.tgid.teste.junior.dto.taxa.TaxaRespostaDTO;
import com.tgid.teste.junior.model.taxa.Taxa;

import java.util.ArrayList;
import java.util.List;

public class TaxaMapper {


    public static Taxa taxaDTOParaTaxa(TaxaInsertDTO dto){
        return Taxa.builder()
                .tipo(dto.getTipo())
                .valor(dto.getValor())
                .build();
    }

    public static List<Taxa> taxaDTOParaTaxa(List<TaxaRespostaDTO> dtos) {
        List<Taxa> taxaList = new ArrayList<>();
        dtos.forEach(taxaRespostaDTO -> {
            taxaList.add(taxaDTOParaTaxa(taxaRespostaDTO));
        });
        return taxaList;
    }

    public static Taxa taxaDTOParaTaxa(TaxaRespostaDTO dto){
        return Taxa.builder()
                .id(dto.getId())
                .tipo(dto.getTipo())
                .valor(dto.getValor())
                .build();
    }

    public static List<TaxaRespostaDTO> taxaParaTaxaRespostaDTO(List<Taxa> taxas) {
        List<TaxaRespostaDTO> taxaRespostaDTOSet = new ArrayList<>();
        taxas.forEach(taxa -> {
            taxaRespostaDTOSet.add(taxaParaTaxaRespostaDTO(taxa));
        });
        return taxaRespostaDTOSet;
    }

    public static TaxaRespostaDTO taxaParaTaxaRespostaDTO(Taxa taxa) {
        return TaxaRespostaDTO.builder()
                .id(taxa.getId())
                .tipo(taxa.getTipo())
                .valor(taxa.getValor())
                .empresaId(taxa.getEmpresa().getId())
                .build();
    }


}
