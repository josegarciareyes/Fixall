package com.registro.usuarios.controlador.dto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.registro.usuarios.repositorio.EstadoRepositorio;
import com.registro.usuarios.servicio.ServicioServicioImpl;

@Controller
@RequestMapping("/tecnico")
public class TecnicoController {

    @Autowired
    private ServicioServicioImpl servicioServicio;

    @Autowired
    private EstadoRepositorio estadoRepositorio;

    @GetMapping("/home")
    public String mostrarHomeTecnico(Model model) {
        // Obtener todos los servicios solicitados
        model.addAttribute("serviciosSolicitados", servicioServicio.obtenerTodosLosServicios());

        // Pasar todos los estados disponibles al modelo
        model.addAttribute("estados", estadoRepositorio.findAll());

        return "tecnico/home";
    }

    @PostMapping("/actualizar-servicios")
    public String actualizarServicios(@RequestParam Map<String, String> requestParams) {
        // Crear un mapa para asociar ID del servicio con ID del estado
        Map<Long, Long> estados = new HashMap<>();

        // Filtrar solo los parámetros relevantes (que empiezan con "estado_")
        requestParams.forEach((key, value) -> {
            if (key.startsWith("estado_")) {
                try {
                    Long servicioId = Long.parseLong(key.replace("estado_", ""));
                    Long estadoId = Long.parseLong(value);
                    estados.put(servicioId, estadoId);
                } catch (NumberFormatException e) {
                    // Ignorar parámetros no válidos
                    System.err.println("Error al procesar el parámetro: " + key + " -> " + value);
                }
            }
        });

        // Actualizar los estados de los servicios
        servicioServicio.actualizarEstadosServicios(estados);

        return "redirect:/tecnico/home?actualizado";
    }

}
