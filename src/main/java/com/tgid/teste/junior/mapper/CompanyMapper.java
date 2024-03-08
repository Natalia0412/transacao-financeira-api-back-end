package com.tgid.teste.junior.mapper;

import com.tgid.teste.junior.dto.company.CompanyaInputDTO;
import com.tgid.teste.junior.model.Client;
import com.tgid.teste.junior.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")

public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);
    @Mapping(target = "clientList", source = "clientList", qualifiedByName = "mapClientIds")
    Company CompanyInsertDTOTOCompany(CompanyaInputDTO dto);
    @Named("mapClientIds")
    default List<Client> map(List<Long> clientId) {
        if (clientId == null) {
            return null;
        }

        return clientId.stream()
                .map(c -> {
                    return Client.builder()
                            .id(c)
                            .build();

                })
                .collect(Collectors.toList());
    }

}
