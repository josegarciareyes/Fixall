package com.registro.usuarios.controlador.dto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tecnico")
public class TecnicoController {

    @GetMapping("/home")
    public String homeTecnico() {
        return "tecnico/home"; // Página principal para técnicos
    }
}
