package com.florian.ceramicaswari.repository;

import com.florian.ceramicaswari.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository
        extends JpaRepository<Pago, Integer> {

    // BUSCAR PAGOS DE UN PEDIDO
    List<Pago> findByIdPedido(Integer idPedido);

    // BUSCAR PAGOS POR TIPO
    List<Pago> findByTipoPagoIgnoreCase(String tipoPago);
}
