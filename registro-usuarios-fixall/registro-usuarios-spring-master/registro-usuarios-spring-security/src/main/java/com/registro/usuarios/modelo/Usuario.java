package com.registro.usuarios.modelo;

//  https://copilot.microsoft.com/chats/3xizxFyWBfC21MZgKgKPC
// Aquí estoy probando si se actulizan los cambios que hago

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Atributos existentes

	@Column(name = "email", unique = true, nullable = false)
	private String email; // Email del usuario

	@Column(name = "password", nullable = false)
	private String password; // Contraseña encriptada del usuario

	@Column(nullable = false)
    private String nombre; // Nombre completo del usuario

    @Column(unique = true, nullable = false)
    private String cedula; // Número de cédula único del usuario

    @Column(nullable = false)
    private String direccion; // Dirección del usuario

    @Column(nullable = false)
    private String telefono; // Número de teléfono del usuario

	@ManyToOne
    @JoinColumn(name = "tipo_usuario_id", nullable = false)
    private TipoUsuario tipoUsuario; // Relación con la entidad TipoUsuario

	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(
			name = "usuarios_roles",
			joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id")
			)
	private Set<Rol> roles; // Relación muchos a muchos con la entidad Rol

    // Constructor por defecto (requerido por JPA)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Servicio> servicios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

	public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

	// Constructor con argumentos
    public Usuario(String nombre, String email, String password, String cedula, String direccion, String telefono,
                     TipoUsuario tipoUsuario, Set<Rol> roles, List<Servicio> servicios) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.cedula = cedula;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
        this.roles = roles;
        this.servicios = servicios;
    }

	// Constructor sin argumentos
	public Usuario() {
	}

}
