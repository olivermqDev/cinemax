package com.cibertec.cinemax.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data // <-- Genera Getters, Setters, toString, equals, hashCode
@NoArgsConstructor // <-- Genera un constructor sin argumentos
@AllArgsConstructor // <-- Genera un constructor con todos los argumentos
@Entity

public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(nullable = false, length = 50)
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<Alquiler> alquileres;

}
