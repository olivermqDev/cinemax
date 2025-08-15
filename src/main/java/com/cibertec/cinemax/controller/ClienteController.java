package com.cibertec.cinemax.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cibertec.cinemax.model.Cliente;
import com.cibertec.cinemax.model.Pelicula;
import com.cibertec.cinemax.repository.ClienteRepository;
import com.cibertec.cinemax.repository.AlquilerRepository;

@Controller
public class ClienteController {
    
    @Autowired // Inyección de dependencias: Spring nos da una instancia del repositorio.
    private ClienteRepository clienteRepository;
    @Autowired
    private AlquilerRepository alquilerRepository;

    // --- READ: Muestra la lista de todos los clientes ---
    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        // Model es el objeto que usamos para pasar datos del controlador a la vista.
        model.addAttribute("listaClientes", clienteRepository.findAll());
        return "listado-clientes"; // Devuelve el nombre del archivo HTML a renderizar.
    }

    // --- CREATE (Paso 1): Muestra el formulario para un nuevo cliente ---
    @GetMapping("/clientes/nuevo")
    public String mostrarFormularioDeNuevoCliente(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "formulario-cliente";
    }

    // --- CREATE (Paso 2): Procesa y guarda el cliente del formulario ---
    @PostMapping("/clientes")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente) {
        // @ModelAttribute vincula los datos del formulario al objeto Cliente.
        clienteRepository.save(cliente);
        return "redirect:/clientes"; // Redirige al usuario de vuelta a la lista.
    }

    // --- UPDATE (Paso 1): Muestra el formulario para editar un cliente existente ---
    @GetMapping("/clientes/editar/{id_cliente}")
    public String mostrarFormularioDeEditar(@PathVariable Long id_cliente, Model model) {
        // @PathVariable obtiene el 'id' de la URL.
        Cliente cliente = clienteRepository.findById(id_cliente)
                .orElseThrow(() -> new IllegalArgumentException("Id de cliente inválido:" + id_cliente));
        model.addAttribute("cliente", cliente);
        return "formulario-cliente"; // Reutilizamos el mismo formulario.
    }
    
    // --- UPDATE (Paso 2): Procesa y guarda los cambios del cliente editado ---
    // NOTA: Este método se fusiona con guardarCliente. El save() de JPA es inteligente:
    // si el objeto tiene un ID, hace un UPDATE; si no lo tiene, hace un INSERT.

    // --- DELETE: Elimina un cliente ---
    @GetMapping("/clientes/eliminar/{id_cliente}")
    public String eliminarCliente(@PathVariable Long id_cliente) {
        clienteRepository.deleteById(id_cliente);
        return "redirect:/clientes";
    }

    // --- READ DETAILS: Muestra los detalles de un cliente y sus cursos matriculados ---
    @GetMapping("/clientes/detalle/{id_cliente}")
    public String verDetalleCliente(@PathVariable Long id_cliente, Model model) {
        Cliente cliente = clienteRepository.findById(id_cliente)
                .orElseThrow(() -> new IllegalArgumentException("Id de cliente inválido:" + id_cliente));

        // Usamos nuestra consulta personalizada para obtener los cursos
        List<Pelicula> peliculasAlquiladas = alquilerRepository.findPeliculasByClienteId(id_cliente);

        model.addAttribute("cliente", cliente);
        model.addAttribute("peliculas", peliculasAlquiladas);

        return "detalle-cliente"; // Necesitaremos crear esta nueva vista
    }


}
