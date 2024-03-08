package com.tgid.teste.junior.mapper;

import com.tgid.teste.junior.dto.taxa.TaxInputDTO;
import com.tgid.teste.junior.model.Tax;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface TaxMapper {
    TaxMapper INSTANCE = Mappers.getMapper(TaxMapper.class);

    Tax TaxInsertDTOToTax(TaxInputDTO dto0);

}
