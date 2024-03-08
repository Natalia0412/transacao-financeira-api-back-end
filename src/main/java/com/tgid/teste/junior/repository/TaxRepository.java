package com.tgid.teste.junior.repository;

import com.tgid.teste.junior.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepository extends JpaRepository<Tax, Long> {
}
