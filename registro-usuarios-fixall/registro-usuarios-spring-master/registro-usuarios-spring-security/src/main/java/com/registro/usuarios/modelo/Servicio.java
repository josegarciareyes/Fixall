package com.registro.usuarios.modelo;




import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario; // Relación con Usuario

    @ManyToOne
    @JoinColumn(name = "tipo_servicio_id", nullable = false)
    private TipoServicio tipoServicio; // Relación con TipoServicio

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detalle_servicio_id", nullable = false)
    private DetalleServicio detalleServicio; // Relación con DetalleServicio

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado; // Relación con Estado del servicio

    // Constructor vacío
    public Servicio() {
    }

    // Constructor completo
    public Servicio(Usuario usuario, TipoServicio tipoServicio, DetalleServicio detalleServicio, Estado estado) {
        this.usuario = usuario;
        this.tipoServicio = tipoServicio;
        this.detalleServicio = detalleServicio;
        this.estado = estado;
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

    public DetalleServicio getDetalleServicio() {
        return detalleServicio;
    }

    public void setDetalleServicio(DetalleServicio detalleServicio) {
        this.detalleServicio = detalleServicio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
