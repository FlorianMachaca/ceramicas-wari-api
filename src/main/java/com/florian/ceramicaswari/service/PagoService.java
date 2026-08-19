package com.florian.ceramicaswari.service;

import com.florian.ceramicaswari.exception.RecursoNoEncontradoException;
import com.florian.ceramicaswari.model.Pago;
import com.florian.ceramicaswari.repository.PagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;
    private final PedidoService pedidoService;

    public PagoService(
            PagoRepository pagoRepository,
            PedidoService pedidoService
    ) {
        this.pagoRepository = pagoRepository;
        this.pedidoService = pedidoService;
    }

    // LISTAR TODOS LOS PAGOS
    public List<Pago> listarPagos() {
        return pagoRepository.findAll();
    }

    // OBTENER PAGO POR ID
    public Pago obtenerPorId(Integer id) {
        return pagoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Pago con ID " + id + " no encontrado"
                        )
                );
    }

    // BUSCAR PAGOS DE UN PEDIDO
    public List<Pago> buscarPorPedido(Integer idPedido) {

        // Verificar que el pedido exista
        pedidoService.obtenerPorId(idPedido);

        return pagoRepository.findByIdPedido(idPedido);
    }

    // BUSCAR PAGOS POR TIPO
    public List<Pago> buscarPorTipo(String tipoPago) {
        return pagoRepository.findByTipoPagoIgnoreCase(tipoPago);
    }

    // CREAR PAGO
    public Pago crearPago(Pago pago) {

        // Verificar que el pedido exista
        pedidoService.obtenerPorId(
                pago.getIdPedido()
        );

        // SQL Server genera el ID
        pago.setIdPago(null);

        return pagoRepository.save(pago);
    }

    // ACTUALIZAR PAGO
    public Pago actualizarPago(
            Integer id,
            Pago pago
    ) {

        // Verificar que el pago exista
        obtenerPorId(id);

        // Verificar que el pedido exista
        pedidoService.obtenerPorId(
                pago.getIdPedido()
        );

        pago.setIdPago(id);

        return pagoRepository.save(pago);
    }

    // ELIMINAR PAGO
    public void eliminarPago(Integer id) {

        obtenerPorId(id);

        pagoRepository.deleteById(id);
    }
}