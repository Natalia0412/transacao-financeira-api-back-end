package com.tgid.teste.junior.repository.taxa;

import com.tgid.teste.junior.model.taxa.Taxa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaxaRepository extends JpaRepository<Taxa, Long> {
}
