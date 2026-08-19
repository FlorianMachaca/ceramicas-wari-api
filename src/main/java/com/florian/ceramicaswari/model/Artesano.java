package com.florian.ceramicaswari.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ARTESANO", schema = "dbo")
public class Artesano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artesano")
    private Integer idArtesano;

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(
            regexp = "^[0-9]{8}$",
            message = "El DNI debe contener exactamente 8 números"
    )
    @Column(name = "dni", nullable = false, length = 8)
    private String dni;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(
            max = 80,
            message = "Los nombres no pueden superar los 80 caracteres"
    )
    @Column(name = "nombres", nullable = false, length = 80)
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(
            max = 100,
            message = "Los apellidos no pueden superar los 100 caracteres"
    )
    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @NotBlank(message = "La especialidad es obligatoria")
    @Size(
            max = 60,
            message = "La especialidad no puede superar los 60 caracteres"
    )
    @Column(name = "especialidad", nullable = false, length = 60)
    private String especialidad;

    public Artesano() {
    }

    public Artesano(
            Integer idArtesano,
            String dni,
            String nombres,
            String apellidos,
            String especialidad
    ) {
        this.idArtesano = idArtesano;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.especialidad = especialidad;
    }

    public Integer getIdArtesano() {
        return idArtesano;
    }

    public void setIdArtesano(Integer idArtesano) {
        this.idArtesano = idArtesano;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}