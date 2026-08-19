package com.florian.ceramicaswari.repository;

import com.florian.ceramicaswari.model.MateriaPrima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaPrimaRepository
        extends JpaRepository<MateriaPrima, Integer> {

    // BUSCAR POR NOMBRE DEL MATERIAL
    List<MateriaPrima> findByNombreMaterialContainingIgnoreCase(String nombreMaterial);

    // BUSCAR POR PROVEEDOR
    List<MateriaPrima> findByIdProveedor(Integer idProveedor);

    // BUSCAR MATERIALES CON STOCK BAJO
    @Query("""
           SELECT m
           FROM MateriaPrima m
           WHERE m.stockActual < m.stockMinimo
           """)
    List<MateriaPrima> buscarConStockBajo();
}