package com.registro.usuarios.servicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    /**
     * Registrar un nuevo servicio por parte de un cliente.
     */
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

    /**
     * Obtener los servicios registrados por un usuario específico.
     */
    public List<Servicio> obtenerServiciosPorUsuario(String emailUsuario) {
        // Buscar el usuario por su email
        Usuario usuario = usuarioRepositorio.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Buscar servicios relacionados al usuario
        return servicioRepositorio.findByUsuario(usuario);
    }

    /**
     * Obtener todos los servicios solicitados.
     */
    public List<Servicio> obtenerTodosLosServicios() {
        // Recuperar todos los servicios solicitados
        return servicioRepositorio.findAll();
    }

    /**
     * Actualizar los estados de los servicios.
     * @param estados Map que contiene el ID del servicio y el ID del nuevo estado.
     */
    public void actualizarEstadosServicios(Map<Long, Long> estados) {
        estados.forEach((key, value) -> {
            Long servicioId = key;
            Long estadoId = value;

            // Buscar el servicio por ID
            Servicio servicio = servicioRepositorio.findById(servicioId)
                    .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

            // Buscar el estado por ID
            Estado estado = estadoRepositorio.findById(estadoId)
                    .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

            // Actualizar el estado del servicio
            servicio.setEstado(estado);

            // Guardar el servicio actualizado
            servicioRepositorio.save(servicio);
        });
    }
}
