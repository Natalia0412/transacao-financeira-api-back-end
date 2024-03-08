package com.tgid.teste.junior.repository;

import com.tgid.teste.junior.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Boolean> existsByEmail(String email);
    Optional<Boolean> existsByCpf(String cpf);

}
