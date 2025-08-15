package com.cibertec.cinemax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.cinemax.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
