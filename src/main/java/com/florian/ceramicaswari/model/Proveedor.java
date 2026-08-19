package com.florian.ceramicaswari.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "PROVEEDOR", schema = "dbo")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @NotBlank(message = "La razón social es obligatoria")
    @Size(
            max = 150,
            message = "La razón social no puede superar los 150 caracteres"
    )
    @Column(
            name = "razon_social",
            nullable = false,
            length = 150
    )
    private String razonSocial;

    @NotBlank(message = "La localidad es obligatoria")
    @Pattern(
            regexp = "^(Acosvinchos|San Miguel|Quinua)$",
            message = "La localidad debe ser Acosvinchos, San Miguel o Quinua"
    )
    @Size(
            max = 40,
            message = "La localidad no puede superar los 40 caracteres"
    )
    @Column(
            name = "localidad",
            nullable = false,
            length = 40
    )
    private String localidad;

    @Size(
            max = 20,
            message = "El teléfono no puede superar los 20 caracteres"
    )
    @Column(
            name = "telefono",
            length = 20
    )
    private String telefono;

    @NotNull(message = "Debe indicar si el proveedor cuenta con certificado de calidad")
    @Column(
            name = "certificado_calidad",
            nullable = false
    )
    private Boolean certificadoCalidad;

    @NotNull(message = "El plazo de crédito es obligatorio")
    @Min(
            value = 0,
            message = "El plazo de crédito no puede ser menor a 0 días"
    )
    @Max(
            value = 30,
            message = "El plazo de crédito no puede superar los 30 días"
    )
    @Column(
            name = "plazo_credito_dias",
            nullable = false
    )
    private Integer plazoCreditoDias;

    public Proveedor() {
    }

    public Proveedor(
            Integer idProveedor,
            String razonSocial,
            String localidad,
            String telefono,
            Boolean certificadoCalidad,
            Integer plazoCreditoDias
    ) {
        this.idProveedor = idProveedor;
        this.razonSocial = razonSocial;
        this.localidad = localidad;
        this.telefono = telefono;
        this.certificadoCalidad = certificadoCalidad;
        this.plazoCreditoDias = plazoCreditoDias;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getCertificadoCalidad() {
        return certificadoCalidad;
    }

    public void setCertificadoCalidad(Boolean certificadoCalidad) {
        this.certificadoCalidad = certificadoCalidad;
    }

    public Integer getPlazoCreditoDias() {
        return plazoCreditoDias;
    }

    public void setPlazoCreditoDias(Integer plazoCreditoDias) {
        this.plazoCreditoDias = plazoCreditoDias;
    }
}