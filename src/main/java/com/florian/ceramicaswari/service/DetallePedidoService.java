package com.florian.ceramicaswari.service;

import com.florian.ceramicaswari.exception.RecursoNoEncontradoException;
import com.florian.ceramicaswari.model.DetallePedido;
import com.florian.ceramicaswari.repository.DetallePedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final PedidoService pedidoService;
    private final ProductoService productoService;

    public DetallePedidoService(
            DetallePedidoRepository detallePedidoRepository,
            PedidoService pedidoService,
            ProductoService productoService
    ) {
        this.detallePedidoRepository = detallePedidoRepository;
        this.pedidoService = pedidoService;
        this.productoService = productoService;
    }

    // LISTAR TODOS LOS DETALLES
    public List<DetallePedido> listarDetalles() {
        return detallePedidoRepository.findAll();
    }

    // OBTENER DETALLE POR ID
    public DetallePedido obtenerPorId(Integer id) {
        return detallePedidoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Detalle de pedido con ID " + id + " no encontrado"
                        )
                );
    }

    // BUSCAR DETALLES DE UN PEDIDO
    public List<DetallePedido> buscarPorPedido(Integer idPedido) {

        // Comprobar que el pedido exista
        pedidoService.obtenerPorId(idPedido);

        return detallePedidoRepository.findByIdPedido(idPedido);
    }

    // BUSCAR DETALLES DE UN PRODUCTO
    public List<DetallePedido> buscarPorProducto(Integer idProducto) {

        // Comprobar que el producto exista
        productoService.obtenerPorId(idProducto);

        return detallePedidoRepository.findByIdProducto(idProducto);
    }

    // CREAR DETALLE
    public DetallePedido crearDetalle(DetallePedido detalle) {

        // Comprobar que el pedido exista
        pedidoService.obtenerPorId(detalle.getIdPedido());

        // Comprobar que el producto exista
        productoService.obtenerPorId(detalle.getIdProducto());

        // El ID lo genera SQL Server
        detalle.setIdDetalle(null);

        return detallePedidoRepository.save(detalle);
    }

    // ACTUALIZAR DETALLE
    public DetallePedido actualizarDetalle(
            Integer id,
            DetallePedido detalle
    ) {

        // Comprobar que el detalle exista
        obtenerPorId(id);

        // Comprobar relaciones
        pedidoService.obtenerPorId(detalle.getIdPedido());
        productoService.obtenerPorId(detalle.getIdProducto());

        detalle.setIdDetalle(id);

        return detallePedidoRepository.save(detalle);
    }

    // ELIMINAR DETALLE
    public void eliminarDetalle(Integer id) {

        obtenerPorId(id);

        detallePedidoRepository.deleteById(id);
    }
}