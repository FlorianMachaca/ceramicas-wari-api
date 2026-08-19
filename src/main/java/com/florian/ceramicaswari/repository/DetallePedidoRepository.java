package com.florian.ceramicaswari.repository;

import com.florian.ceramicaswari.model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository
        extends JpaRepository<DetallePedido, Integer> {

    // BUSCAR TODOS LOS DETALLES DE UN PEDIDO
    List<DetallePedido> findByIdPedido(Integer idPedido);

    // BUSCAR TODOS LOS DETALLES DE UN PRODUCTO
    List<DetallePedido> findByIdProducto(Integer idProducto);
}