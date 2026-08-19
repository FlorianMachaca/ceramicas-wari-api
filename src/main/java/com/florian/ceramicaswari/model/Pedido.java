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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "PEDIDO", schema = "dbo")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @NotNull(message = "La fecha del pedido es obligatoria")
    @Column(name = "fecha_pedido", nullable = false)
    private LocalDate fechaPedido;

    @NotNull(message = "El total del pedido es obligatorio")
    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "El total del pedido no puede ser negativo"
    )
    @Column(
            name = "total_pedido",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal totalPedido;

    @NotBlank(message = "El estado del pedido es obligatorio")
    @Size(
            max = 20,
            message = "El estado no puede superar los 20 caracteres"
    )
    @Pattern(
            regexp = "^(Pendiente|En producción|Entregado)$",
            message = "El estado debe ser Pendiente, En producción o Entregado"
    )
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @NotNull(message = "La fecha de entrega acordada es obligatoria")
    @Column(name = "fecha_entrega_acordada", nullable = false)
    private LocalDate fechaEntregaAcordada;

    @NotNull(message = "El cliente es obligatorio")
    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "id_cliente",
            insertable = false,
            updatable = false
    )
    private Cliente cliente;

    public Pedido() {
    }

    public Pedido(
            Integer idPedido,
            LocalDate fechaPedido,
            BigDecimal totalPedido,
            String estado,
            LocalDate fechaEntregaAcordada,
            Integer idCliente
    ) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.totalPedido = totalPedido;
        this.estado = estado;
        this.fechaEntregaAcordada = fechaEntregaAcordada;
        this.idCliente = idCliente;
    }

    @AssertTrue(
            message = "La fecha de entrega acordada no puede ser anterior a la fecha del pedido"
    )
    @JsonIgnore
    public boolean isFechaEntregaValida() {

        if (fechaPedido == null || fechaEntregaAcordada == null) {
            return true;
        }

        return !fechaEntregaAcordada.isBefore(fechaPedido);
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public BigDecimal getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(BigDecimal totalPedido) {
        this.totalPedido = totalPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaEntregaAcordada() {
        return fechaEntregaAcordada;
    }

    public void setFechaEntregaAcordada(LocalDate fechaEntregaAcordada) {
        this.fechaEntregaAcordada = fechaEntregaAcordada;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}