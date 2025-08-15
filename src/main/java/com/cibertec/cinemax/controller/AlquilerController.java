package com.cibertec.cinemax.controller;


import com.cibertec.cinemax.model.Alquiler;
import com.cibertec.cinemax.repository.PeliculaRepository;
import com.cibertec.cinemax.repository.ClienteRepository;
import com.cibertec.cinemax.repository.AlquilerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDate;

@Controller
public class AlquilerController {

    @Autowired private AlquilerRepository alquilerRepository;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private PeliculaRepository peliculaRepository;

    // Muestra el formulario para realizar una nueva matrícula
    @GetMapping("/alquileres/nueva")
    public String mostrarFormularioDeAlquiler(Model model) {
        // Obtenemos todos los clientes y peliculas para los dropdowns
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("peliculas", peliculaRepository.findAll());
        model.addAttribute("alquiler", new Alquiler());
        return "formulario-alquiler";
    }

    // Procesa el guardado el nuevo alquiler
    @PostMapping("/alquileres")
    public String guardarAlquiler(@ModelAttribute("alquiler") Alquiler alquiler) {
        alquiler.setFecha(LocalDate.now());
        alquilerRepository.save(alquiler);
        // Redirigimos a los detalles del estudiante para ver la nueva matrícula
        return "redirect:/clientes/detalle/" + alquiler.getCliente().getId_cliente();
    }
}