package com.registro.usuarios.controlador;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.registro.usuarios.controlador.dto.UsuarioRegistroDTO;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.servicio.TipoUsuarioServicio;
import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
public class RegistroControlador {

	
    private final UsuarioServicio usuarioServicio; // Servicio para la lógica de negocio relacionada con Usuario
    private final TipoUsuarioServicio tipoUsuarioServicio; // Servicio para manejar los tipos de usuario

    // Constructor con inyección de dependencias
    
    public RegistroControlador(@Lazy UsuarioServicio usuarioServicio, TipoUsuarioServicio tipoUsuarioServicio) {
        this.usuarioServicio = usuarioServicio; // Inicializa el servicio de Usuario
        this.tipoUsuarioServicio = tipoUsuarioServicio; // Inicializa el servicio de TipoUsuario
    }

    // Maneja solicitudes GET para mostrar el formulario de registro
    @GetMapping("/registro")
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("usuario", new Usuario()); 
        // Se envía un objeto vacío de Usuario al modelo para enlazar con el formulario
        
        model.addAttribute("tiposUsuario", tipoUsuarioServicio.obtenerTodosLosTipos()); 
        // Envía una lista de todos los tipos de usuario al modelo
        
        return "registro"; 
        // Devuelve el nombre de la vista "registro.html"
    }

    // Maneja solicitudes POST para procesar el registro de usuarios
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO, Model model) {
	   try {
		   usuarioServicio.registrarUsuario(registroDTO);
            // Llama al servicio para guardar el usuario en la base de datos
            
            return "redirect:/login?registroExitoso"; 
            // Redirige al login con un mensaje indicando éxito en el registro
        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar el usuario: " + e.getMessage()); 
            // Envía un mensaje de error al modelo para mostrarlo en la vista
            
            model.addAttribute("tiposUsuario", tipoUsuarioServicio.obtenerTodosLosTipos()); 
            // Recarga los tipos de usuario para que el formulario no pierda esta información
            
            return "registro"; 
            // Devuelve la vista "registro" para que el usuario pueda corregir errores
        }
    }
	
}
