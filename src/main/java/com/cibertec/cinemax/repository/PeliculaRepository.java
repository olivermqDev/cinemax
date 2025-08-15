package com.cibertec.cinemax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.cinemax.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {}
