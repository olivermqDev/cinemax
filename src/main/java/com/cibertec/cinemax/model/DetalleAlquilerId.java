package com.cibertec.cinemax.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DetalleAlquilerId implements Serializable {

    private Long id_alquiler;
    private Long id_pelicula;

  

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetalleAlquilerId)) return false;
        DetalleAlquilerId that = (DetalleAlquilerId) o;
        return Objects.equals(id_alquiler, that.id_alquiler) &&
               Objects.equals(id_pelicula, that.id_pelicula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_alquiler, id_pelicula);
    }
}
