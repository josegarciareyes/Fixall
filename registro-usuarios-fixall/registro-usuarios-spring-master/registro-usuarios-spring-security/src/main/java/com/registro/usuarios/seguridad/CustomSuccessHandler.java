package com.registro.usuarios.seguridad;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.servicio.UsuarioServicio;



@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private final UsuarioServicio usuarioServicio; // Inyectamos el servicio para obtener detalles del usuario

    public CustomSuccessHandler(@Lazy UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio; // Inicializamos el servicio
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Obtén el usuario autenticado desde el contexto de Spring Security
        User userDetails = (User) authentication.getPrincipal(); // Obtiene los detalles del usuario autenticado

        // Utiliza el servicio para buscar el usuario en la base de datos
        Usuario usuario = usuarioServicio.buscarPorEmail(userDetails.getUsername()); // Busca el usuario por email (username)

        // Verifica el tipo de usuario y redirige
        String tipoUsuario = usuario.getTipoUsuario().getNombre(); // Obtiene el tipo de usuario

        if ("Cliente".equalsIgnoreCase(tipoUsuario)) {
            response.sendRedirect("/cliente/home"); // Redirige a la página del Cliente
        } else if ("Técnico".equalsIgnoreCase(tipoUsuario)) {
            response.sendRedirect("/tecnico/home"); // Redirige a la página del Técnico
        } else {
            response.sendRedirect("/access-denied"); // Página para casos inesperados
        }
    }
}
