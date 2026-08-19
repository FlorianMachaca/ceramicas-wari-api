package com.florian.ceramicaswari.repository;

import com.florian.ceramicaswari.model.Produccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduccionRepository
        extends JpaRepository<Produccion, Integer> {

    // BUSCAR PRODUCCIONES POR PRODUCTO
    List<Produccion> findByIdProducto(Integer idProducto);

    // BUSCAR PRODUCCIONES POR ARTESANO
    List<Produccion> findByIdArtesano(Integer idArtesano);

    // BUSCAR PRODUCCIONES POR ETAPA
    List<Produccion> findByEtapaActualContainingIgnoreCase(String etapaActual);
}