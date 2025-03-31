package com.registro.usuarios.modelo;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tipo_servicio_id", nullable = false)
    private TipoServicio tipoServicio;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
    private List<DetalleServicio> detalleServicios; // Relación con los detalles del servicio


    @Column(nullable = false)
    private String descripcion; // Campo para la descripción del servicio

    // Constructor sin argumentos
    public Servicio() {
    }

    // Constructor con argumentos
    public Servicio(Usuario usuario, TipoServicio tipoServicio, Estado estado, List<DetalleServicio> detalleServicios) {
        this.usuario = usuario;
        this.tipoServicio = tipoServicio;
        this.estado = estado;
        this.detalleServicios = detalleServicios;
    }
    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<DetalleServicio> getDetalleServicios() {
        return detalleServicios;
    }

    public void setDetalleServicios(List<DetalleServicio> detalleServicios) {
        this.detalleServicios = detalleServicios;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
