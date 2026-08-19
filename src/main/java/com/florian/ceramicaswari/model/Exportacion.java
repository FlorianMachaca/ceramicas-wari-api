package com.florian.ceramicaswari.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "EXPORTACION", schema = "dbo")
public class Exportacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exportacion")
    private Integer idExportacion;

    @NotNull(message = "La fecha de despacho es obligatoria")
    @Column(
            name = "fecha_despacho",
            nullable = false
    )
    private LocalDate fechaDespacho;

    @NotBlank(message = "El courier es obligatorio")
    @Size(
            max = 80,
            message = "El courier no puede superar los 80 caracteres"
    )
    @Column(
            name = "courier",
            nullable = false,
            length = 80
    )
    private String courier;

    @NotBlank(message = "El número de seguimiento es obligatorio")
    @Size(
            max = 50,
            message = "El número de seguimiento no puede superar los 50 caracteres"
    )
    @Column(
            name = "num_seguimiento",
            nullable = false,
            length = 50
    )
    private String numSeguimiento;

    @NotBlank(message = "El estado de envío es obligatorio")
    @Size(
            max = 20,
            message = "El estado de envío no puede superar los 20 caracteres"
    )
    @Pattern(
            regexp = "^(Embalado|Enviado|Entregado)$",
            message = "El estado de envío debe ser Embalado, Enviado o Entregado"
    )
    @Column(
            name = "estado_envio",
            nullable = false,
            length = 20
    )
    private String estadoEnvio;

    @NotNull(message = "El pedido es obligatorio")
    @Column(
            name = "id_pedido",
            nullable = false
    )
    private Integer idPedido;

    // MUCHAS EXPORTACIONES PUEDEN ESTAR ASOCIADAS A UN PEDIDO
    @OneToOne(fetch = FetchType.EAGER)
@JoinColumn(
        name = "id_pedido",
        insertable = false,
        updatable = false
)
private Pedido pedido;

    public Exportacion() {
    }

    public Exportacion(
            Integer idExportacion,
            LocalDate fechaDespacho,
            String courier,
            String numSeguimiento,
            String estadoEnvio,
            Integer idPedido
    ) {
        this.idExportacion = idExportacion;
        this.fechaDespacho = fechaDespacho;
        this.courier = courier;
        this.numSeguimiento = numSeguimiento;
        this.estadoEnvio = estadoEnvio;
        this.idPedido = idPedido;
    }

    public Integer getIdExportacion() {
        return idExportacion;
    }

    public void setIdExportacion(Integer idExportacion) {
        this.idExportacion = idExportacion;
    }

    public LocalDate getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(LocalDate fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public String getNumSeguimiento() {
        return numSeguimiento;
    }

    public void setNumSeguimiento(String numSeguimiento) {
        this.numSeguimiento = numSeguimiento;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}