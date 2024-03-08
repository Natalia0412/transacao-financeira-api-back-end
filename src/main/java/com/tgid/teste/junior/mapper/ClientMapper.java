package com.tgid.teste.junior.mapper;

import com.tgid.teste.junior.dto.client.ClientInputDTO;
import com.tgid.teste.junior.model.Client;
import com.tgid.teste.junior.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client ClientInputDTOToClient(ClientInputDTO dto);
    default List<Company> map(List<Long> companyIds) {
        if (companyIds == null) {
            return null;
        }

        return companyIds.stream()
                .map(companyId -> {
                    return Company.builder()
                            .id(companyId)
                            .build();

                })
                .collect(Collectors.toList());
    }

}
