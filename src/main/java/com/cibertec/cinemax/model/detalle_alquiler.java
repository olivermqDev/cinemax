package com.cibertec.cinemax.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class detalle_alquiler {

    @EmbeddedId
    private DetalleAlquilerId id;

    @ManyToOne
    @MapsId("id_alquiler")
    @JoinColumn(name = "id_alquiler")
    private Alquiler alquiler;

    @ManyToOne
    @MapsId("id_pelicula")
    @JoinColumn(name = "id_pelicula")
    private Pelicula pelicula;

    @Column(nullable = false)
    private int cantidad;

    
}
