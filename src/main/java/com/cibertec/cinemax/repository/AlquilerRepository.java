package com.cibertec.cinemax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.cinemax.model.Alquiler;
import com.cibertec.cinemax.model.Pelicula;
import java.util.List;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

    /**
     * Consulta JPQL para encontrar todos los cursos en los que un estudiante específico está matriculado.
     * "SELECT m.curso": Selecciona solo el objeto Curso de la matrícula.
     * "FROM Matricula m": Opera sobre la entidad Matricula, a la que llamamos 'm'.
     * "WHERE m.estudiante.id = :idEstudiante": Filtra por el ID del estudiante contenido en la matrícula.
     * ":idEstudiante" es un parámetro que pasaremos al método.
     */
    @Query("SELECT m.pelicula FROM Alquiler m WHERE m.cliente.id_cliente = :idCliente")
    List<Pelicula> findPeliculasByClienteId(@Param("idCliente") Long idCliente);
}