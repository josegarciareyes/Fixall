package com.registro.usuarios.servicio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.DetalleServicio;
import com.registro.usuarios.modelo.Estado;
import com.registro.usuarios.modelo.Servicio;
import com.registro.usuarios.modelo.TipoServicio;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.repositorio.EstadoRepositorio;
import com.registro.usuarios.repositorio.ServicioRepositorio;
import com.registro.usuarios.repositorio.TipoServicioRepositorio;
import com.registro.usuarios.repositorio.UsuarioRepositorio;


@Service
public class ServicioServicioImpl {
    
    @Autowired
    private ServicioRepositorio servicioRepositorio;

    @Autowired
    private TipoServicioRepositorio tipoServicioRepositorio;

    @Autowired
    private EstadoRepositorio estadoRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio; // Necesitamos el usuario para asociarlo al servicio

    public void registrarServicio(String emailUsuario, Long tipoServicioId, String descripcion) {
        // Buscar el usuario por su email
        Usuario usuario = usuarioRepositorio.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Buscar el tipo de servicio
        TipoServicio tipoServicio = tipoServicioRepositorio.findById(tipoServicioId)
                .orElseThrow(() -> new RuntimeException("Tipo de Servicio no encontrado"));

        // Buscar el estado "Pendiente"
        Estado estadoPendiente = estadoRepositorio.findByNombre("Pendiente")
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        // Crear el detalle del servicio
        DetalleServicio detalleServicio = new DetalleServicio(descripcion, LocalDateTime.now());

        // Crear el servicio
        Servicio servicio = new Servicio(usuario, tipoServicio, detalleServicio, estadoPendiente);

        // Asociar el servicio al detalle de servicio
        detalleServicio.setServicio(servicio);

        // Guardar el servicio (el detalle también se guarda automáticamente debido a CascadeType.ALL)
        servicioRepositorio.save(servicio);
    }

    public List<Servicio> obtenerServiciosPorUsuario(String emailUsuario) {
        // Buscar el usuario por su email
        Usuario usuario = usuarioRepositorio.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Buscar servicios relacionados al usuario
        return servicioRepositorio.findByUsuario(usuario);
    }
}
