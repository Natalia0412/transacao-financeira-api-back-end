package com.tgid.teste.junior.repository.empresa;

import com.tgid.teste.junior.model.empresa.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    boolean existsByCnpj(String cnpj);
    //boolean existsByClientesListIn(List<Long> idsClientes);
}
