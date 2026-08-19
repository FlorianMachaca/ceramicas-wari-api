package com.florian.ceramicaswari.repository;

import com.florian.ceramicaswari.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository
        extends JpaRepository<Proveedor, Integer> {

    // BUSCAR POR RAZÓN SOCIAL
    List<Proveedor> findByRazonSocialContainingIgnoreCase(String razonSocial);

    // BUSCAR POR LOCALIDAD
    List<Proveedor> findByLocalidadIgnoreCase(String localidad);
}