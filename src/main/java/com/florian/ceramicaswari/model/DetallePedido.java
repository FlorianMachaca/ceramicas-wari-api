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

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "DETALLE_PEDIDO", schema = "dbo")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    @NotNull(message = "El pedido es obligatorio")
    @Column(name = "id_pedido", nullable = false)
    private Integer idPedido;

    @NotNull(message = "El producto es obligatorio")
    @Column(name = "id_producto", nullable = false)
    private Integer idProducto;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor que 0")
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio unitario debe ser mayor que 0")
    @Column(
            name = "precio_unitario",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal precioUnitario;

    // SQL Server calcula automáticamente cantidad * precio_unitario
    @Column(
            name = "subtotal",
            insertable = false,
            updatable = false,
            precision = 21,
            scale = 2
    )
    private BigDecimal subtotal;

    @Size(
            max = 500,
            message = "Las observaciones no pueden superar los 500 caracteres"
    )
    @Column(name = "observaciones", length = 500)
    private String observaciones;

    // RELACIÓN: MUCHOS DETALLES PERTENECEN A UN PEDIDO
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "id_pedido",
            insertable = false,
            updatable = false
    )
    private Pedido pedido;

    // RELACIÓN: MUCHOS DETALLES PUEDEN REFERIRSE A UN PRODUCTO
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "id_producto",
            insertable = false,
            updatable = false
    )
    private Producto producto;

    public DetallePedido() {
    }

    public DetallePedido(
            Integer idDetalle,
            Integer idPedido,
            Integer idProducto,
            Integer cantidad,
            BigDecimal precioUnitario,
            String observaciones
    ) {
        this.idDetalle = idDetalle;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.observaciones = observaciones;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}