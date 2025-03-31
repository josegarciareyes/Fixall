package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registro.usuarios.modelo.TipoUsuario;

public interface TipoUsuarioRepositorio extends JpaRepository<TipoUsuario, Long> {


// JpaRepository proporciona métodos básicos como findAll(), findById(), save(), deleteById()

    // Puedes agregar métodos personalizados si es necesario
    TipoUsuario findByNombre(String nombre); // Busca un TipoUsuario por su nombre
}
