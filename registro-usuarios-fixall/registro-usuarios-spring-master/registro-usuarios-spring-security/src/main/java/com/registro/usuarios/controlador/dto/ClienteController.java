package com.registro.usuarios.controlador.dto;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.registro.usuarios.repositorio.TipoServicioRepositorio;
import com.registro.usuarios.servicio.ServicioServicioImpl;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private TipoServicioRepositorio tipoServicioRepositorio;

    @Autowired
    private ServicioServicioImpl servicioServicio;

    @GetMapping("/home")
    public String mostrarHomeCliente(Model model, Principal principal) {
        // Obtener email del usuario autenticado
        String emailUsuario = principal.getName();

        // Pasar los tipos de servicio al modelo
        model.addAttribute("tipoServicios", tipoServicioRepositorio.findAll());

        // Pasar los servicios registrados por el usuario al modelo
        model.addAttribute("serviciosRegistrados", servicioServicio.obtenerServiciosPorUsuario(emailUsuario));

        return "cliente/home";
    }

    @PostMapping("/registrar-servicio")
    public String registrarServicio(@RequestParam Long tipoServicioId,
                                    @RequestParam String descripcion,
                                    Principal principal) {
        // Obtener el email del usuario autenticado
        String emailUsuario = principal.getName();

        // Registrar el servicio
        servicioServicio.registrarServicio(emailUsuario, tipoServicioId, descripcion);

        return "redirect:/cliente/home?exito"; // Redirigir a la página principal con un mensaje de éxito
    }
}

