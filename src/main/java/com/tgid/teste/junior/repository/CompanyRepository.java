package com.tgid.teste.junior.repository;

import com.tgid.teste.junior.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Boolean> existsByEmail(String email);
    Optional<Boolean> existsByCnpj(String cnpj);

}
