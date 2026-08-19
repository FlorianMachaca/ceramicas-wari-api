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

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "MATERIA_PRIMA", schema = "dbo")
public class MateriaPrima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia")
    private Integer idMateria;

    @NotBlank(message = "El nombre del material es obligatorio")
    @Size(
            max = 100,
            message = "El nombre del material no puede superar los 100 caracteres"
    )
    @Column(
            name = "nombre_material",
            nullable = false,
            length = 100
    )
    private String nombreMaterial;

    @NotBlank(message = "La unidad de medida es obligatoria")
    @Size(
            max = 20,
            message = "La unidad de medida no puede superar los 20 caracteres"
    )
    @Column(
            name = "unidad_medida",
            nullable = false,
            length = 20
    )
    private String unidadMedida;

    @NotNull(message = "El stock actual es obligatorio")
    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "El stock actual no puede ser negativo"
    )
    @Column(
            name = "stock_actual",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal stockActual;

    @NotNull(message = "El stock mínimo es obligatorio")
    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "El stock mínimo no puede ser negativo"
    )
    @Column(
            name = "stock_minimo",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal stockMinimo;

    @NotNull(message = "El proveedor es obligatorio")
    @Column(
            name = "id_proveedor",
            nullable = false
    )
    private Integer idProveedor;

    // MUCHAS MATERIAS PRIMAS PUEDEN PERTENECER A UN PROVEEDOR
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "id_proveedor",
            insertable = false,
            updatable = false
    )
    private Proveedor proveedor;

    public MateriaPrima() {
    }

    public MateriaPrima(
            Integer idMateria,
            String nombreMaterial,
            String unidadMedida,
            BigDecimal stockActual,
            BigDecimal stockMinimo,
            Integer idProveedor
    ) {
        this.idMateria = idMateria;
        this.nombreMaterial = nombreMaterial;
        this.unidadMedida = unidadMedida;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.idProveedor = idProveedor;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public BigDecimal getStockActual() {
        return stockActual;
    }

    public void setStockActual(BigDecimal stockActual) {
        this.stockActual = stockActual;
    }

    public BigDecimal getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(BigDecimal stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}