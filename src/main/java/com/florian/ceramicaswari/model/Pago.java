package com.florian.ceramicaswari.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "PAGO", schema = "dbo")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Integer idPago;

    @NotNull(message = "La fecha de pago es obligatoria")
    @Column(
            name = "fecha_pago",
            nullable = false
    )
    private LocalDate fechaPago;

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor que 0")
    @Column(
            name = "monto",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal monto;

    @NotBlank(message = "El tipo de pago es obligatorio")
    @Size(
            max = 15,
            message = "El tipo de pago no puede superar los 15 caracteres"
    )
    @Pattern(
            regexp = "^(Contado|Saldo|Adelanto)$",
            message = "El tipo de pago debe ser Contado, Saldo o Adelanto"
    )
    @Column(
            name = "tipo_pago",
            nullable = false,
            length = 15
    )
    private String tipoPago;

    @NotNull(message = "El pedido es obligatorio")
    @Column(
            name = "id_pedido",
            nullable = false
    )
    private Integer idPedido;

    // MUCHOS PAGOS PUEDEN PERTENECER A UN PEDIDO
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "id_pedido",
            insertable = false,
            updatable = false
    )
    private Pedido pedido;

    public Pago() {
    }

    public Pago(
            Integer idPago,
            LocalDate fechaPago,
            BigDecimal monto,
            String tipoPago,
            Integer idPedido
    ) {
        this.idPago = idPago;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.tipoPago = tipoPago;
        this.idPedido = idPedido;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
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
