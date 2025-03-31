package com.registro.usuarios.servicio;


// Clase UsuarioServicioImpl implementa la lógica y conecta con el repositorio.
// Maneja toda la lógica de negocio relacionada con los usuarios, como guardar, actualizar, y buscar por tipo o email.


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.registro.usuarios.controlador.dto.UsuarioRegistroDTO;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

	
	private final UsuarioRepositorio usuarioRepositorio;
	private final BCryptPasswordEncoder passwordEncoder; // Codificador de contraseñas

    // Constructor para inyectar dependencias
    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio, @Lazy BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepositorio = usuarioRepositorio; // Inicializa el repositorio
        this.passwordEncoder = passwordEncoder; // Inicializa el codificador de contraseñas
    }

	
	// Método para registrar un usuario utilizando un DTO
	@Override
	public Usuario registrarUsuario(UsuarioRegistroDTO registroDTO) {
		
		Usuario usuario = new Usuario();
		
		usuario.setEmail(registroDTO.getEmail());
		usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword())); // Encripta la contraseña
		usuario.setNombre(registroDTO.getNombre());
		usuario.setCedula(registroDTO.getCedula());
		usuario.setDireccion(registroDTO.getDireccion());
		usuario.setTelefono(registroDTO.getTelefono());
		usuario.setTipoUsuario(registroDTO.getTipoUsuario());
		usuario.setRoles(registroDTO.getRoles());

		// Guarda el usuario en la base de datos
		return usuarioRepositorio.save(usuario);
	}

	    @Override
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			// Busca el usuario en la base de datos utilizando el repositorio
			Usuario usuario = usuarioRepositorio.findByEmail(email)
					.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));
		
			// Crea y devuelve un objeto UserDetails con la información del usuario
			return org.springframework.security.core.userdetails.User.builder()
					.username(usuario.getEmail())
					.password(usuario.getPassword())
					.authorities(mapearAutoridadesRoles(usuario.getRoles()))// Asigna los roles del usuario como autoridades
					.build();
		}

        private Set<? extends org.springframework.security.core.GrantedAuthority> mapearAutoridadesRoles(Set<com.registro.usuarios.modelo.Rol> roles) {
			// Convierte los roles en objetos GrantedAuthority compatibles con Spring Security
        return roles.stream()
            .map(rol -> new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + rol.getNombre()))
            .collect(Collectors.toSet());

}

	// Método para obtener todos los usuarios de la base de datos	
	@Override
	public List<Usuario> listarUsuarios() {
		// Recupera todos los usuarios almacenados en la base de datos
		return usuarioRepositorio.findAll();
	}

	// Método para actualizar un usuario existente
    @Override
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        return usuarioRepositorio.findById(id).map(usuario -> {
            usuario.setNombre(usuarioActualizado.getNombre()); // Actualiza el nombre
            usuario.setEmail(usuarioActualizado.getEmail()); // Actualiza el email
            usuario.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword())); // Encripta y actualiza la contraseña
            usuario.setDireccion(usuarioActualizado.getDireccion()); // Actualiza la dirección
            usuario.setTelefono(usuarioActualizado.getTelefono()); // Actualiza el teléfono
            usuario.setTipoUsuario(usuarioActualizado.getTipoUsuario()); // Actualiza el tipo de usuario
            return usuarioRepositorio.save(usuario); // Guarda y retorna el usuario actualizado
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + id));
    }

	@Override
    public List<Usuario> obtenerUsuariosPorTipo(String tipoUsuario) {
        // Recupera y devuelve usuarios filtrados por su tipo
        return usuarioRepositorio.findByTipoUsuario_Nombre(tipoUsuario);
    }

	@Override
    public Usuario buscarPorEmail(String email) {
        return usuarioRepositorio.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }
}

