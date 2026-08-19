package com.florian.ceramicaswari.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "CLIENTE", schema = "dbo")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @NotBlank(message = "El nombre o razón social es obligatorio")
    @Size(max = 150, message = "El nombre o razón social no puede superar los 150 caracteres")
    @Column(name = "nombres_razon_social", nullable = false, length = 150)
    private String nombresRazonSocial;

    @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
    @Column(name = "telefono", length = 20)
    private String telefono;

    @Email(message = "El correo electrónico no tiene un formato válido")
    @Size(max = 120, message = "El correo no puede superar los 120 caracteres")
    @Column(name = "email", length = 120)
    private String email;

    @NotBlank(message = "El país es obligatorio")
    @Size(max = 60, message = "El país no puede superar los 60 caracteres")
    @Column(name = "pais", nullable = false, length = 60)
    private String pais;

    @NotBlank(message = "La ciudad es obligatoria")
    @Size(max = 60, message = "La ciudad no puede superar los 60 caracteres")
    @Column(name = "ciudad", nullable = false, length = 60)
    private String ciudad;

    @NotNull(message = "La deuda pendiente es obligatoria")
    @Column(name = "deuda_pendiente", nullable = false, precision = 10, scale = 2)
    private BigDecimal deudaPendiente;

    @NotNull(message = "Los días de mora son obligatorios")
    @Column(name = "dias_mora", nullable = false)
    private Integer diasMora;

    public Cliente() {
    }

    public Cliente(
            Integer idCliente,
            String nombresRazonSocial,
            String telefono,
            String email,
            String pais,
            String ciudad,
            BigDecimal deudaPendiente,
            Integer diasMora
    ) {
        this.idCliente = idCliente;
        this.nombresRazonSocial = nombresRazonSocial;
        this.telefono = telefono;
        this.email = email;
        this.pais = pais;
        this.ciudad = ciudad;
        this.deudaPendiente = deudaPendiente;
        this.diasMora = diasMora;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombresRazonSocial() {
        return nombresRazonSocial;
    }

    public void setNombresRazonSocial(String nombresRazonSocial) {
        this.nombresRazonSocial = nombresRazonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public BigDecimal getDeudaPendiente() {
        return deudaPendiente;
    }

    public void setDeudaPendiente(BigDecimal deudaPendiente) {
        this.deudaPendiente = deudaPendiente;
    }

    public Integer getDiasMora() {
        return diasMora;
    }

    public void setDiasMora(Integer diasMora) {
        this.diasMora = diasMora;
    }
}