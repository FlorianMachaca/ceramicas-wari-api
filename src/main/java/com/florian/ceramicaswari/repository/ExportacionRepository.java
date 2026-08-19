package com.florian.ceramicaswari.repository;

import com.florian.ceramicaswari.model.Exportacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExportacionRepository
        extends JpaRepository<Exportacion, Integer> {

    // BUSCAR EXPORTACIONES DE UN PEDIDO
    List<Exportacion> findByIdPedido(Integer idPedido);

    // BUSCAR EXPORTACIONES POR ESTADO
    List<Exportacion> findByEstadoEnvioIgnoreCase(String estadoEnvio);

    // BUSCAR POR COURIER
    List<Exportacion> findByCourierContainingIgnoreCase(String courier);

    // VERIFICAR SI UN PEDIDO YA TIENE EXPORTACION
    boolean existsByIdPedido(Integer idPedido);

    // VERIFICAR DUPLICADO AL ACTUALIZAR,
    // IGNORANDO LA EXPORTACION QUE SE ESTA MODIFICANDO
    boolean existsByIdPedidoAndIdExportacionNot(
            Integer idPedido,
            Integer idExportacion
    );
}