package com.florian.ceramicaswari.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "PRODUCCION", schema = "dbo")
public class Produccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produccion")
    private Integer idProduccion;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    // Puede ser NULL mientras la producción siga en proceso
    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @NotNull(message = "La cantidad de piezas es obligatoria")
    @Positive(message = "La cantidad de piezas debe ser mayor que 0")
    @Column(name = "cantidad_piezas", nullable = false)
    private Integer cantidadPiezas;

    @NotBlank(message = "La etapa actual es obligatoria")
    @Size(
            max = 20,
            message = "La etapa actual no puede superar los 20 caracteres"
    )
    @Pattern(
            regexp = "^(Modelado|Secado|Decoración|Cocción|Acabado)$",
            message = "La etapa debe ser Modelado, Secado, Decoración, Cocción o Acabado"
    )
    @Column(name = "etapa_actual", nullable = false, length = 20)
    private String etapaActual;

    @NotNull(message = "El producto es obligatorio")
    @Column(name = "id_producto", nullable = false)
    private Integer idProducto;

    @NotNull(message = "El artesano es obligatorio")
    @Column(name = "id_artesano", nullable = false)
    private Integer idArtesano;

    // MUCHAS PRODUCCIONES PUEDEN CORRESPONDER A UN PRODUCTO
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "id_producto",
            insertable = false,
            updatable = false
    )
    private Producto producto;

    // MUCHAS PRODUCCIONES PUEDEN ESTAR ASIGNADAS A UN ARTESANO
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "id_artesano",
            insertable = false,
            updatable = false
    )
    private Artesano artesano;

    public Produccion() {
    }

    public Produccion(
            Integer idProduccion,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            Integer cantidadPiezas,
            String etapaActual,
            Integer idProducto,
            Integer idArtesano
    ) {
        this.idProduccion = idProduccion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidadPiezas = cantidadPiezas;
        this.etapaActual = etapaActual;
        this.idProducto = idProducto;
        this.idArtesano = idArtesano;
    }

    // FECHA FIN PUEDE SER NULL.
    // SI EXISTE, NO PUEDE SER ANTERIOR A FECHA INICIO.
    @AssertTrue(
            message = "La fecha de fin no puede ser anterior a la fecha de inicio"
    )
    @JsonIgnore
    public boolean isFechaFinValida() {

        if (fechaInicio == null || fechaFin == null) {
            return true;
        }

        return !fechaFin.isBefore(fechaInicio);
    }

    public Integer getIdProduccion() {
        return idProduccion;
    }

    public void setIdProduccion(Integer idProduccion) {
        this.idProduccion = idProduccion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getCantidadPiezas() {
        return cantidadPiezas;
    }

    public void setCantidadPiezas(Integer cantidadPiezas) {
        this.cantidadPiezas = cantidadPiezas;
    }

    public String getEtapaActual() {
        return etapaActual;
    }

    public void setEtapaActual(String etapaActual) {
        this.etapaActual = etapaActual;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdArtesano() {
        return idArtesano;
    }

    public void setIdArtesano(Integer idArtesano) {
        this.idArtesano = idArtesano;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Artesano getArtesano() {
        return artesano;
    }

    public void setArtesano(Artesano artesano) {
        this.artesano = artesano;
    }
}
