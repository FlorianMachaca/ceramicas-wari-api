package com.florian.ceramicaswari.repository;

import com.florian.ceramicaswari.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    // Buscar todos los pedidos de un cliente
    List<Pedido> findByIdCliente(Integer idCliente);

    // Buscar pedidos por estado
    List<Pedido> findByEstadoContainingIgnoreCase(String estado);
}