package com.florian.ceramicaswari.controller;

import com.florian.ceramicaswari.model.DetallePedido;
import com.florian.ceramicaswari.service.DetallePedidoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-pedido")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(
            DetallePedidoService detallePedidoService
    ) {
        this.detallePedidoService = detallePedidoService;
    }

    // LISTAR TODOS LOS DETALLES
    @GetMapping
    public List<DetallePedido> listarDetalles() {
        return detallePedidoService.listarDetalles();
    }

    // BUSCAR DETALLES DE UN PEDIDO
    @GetMapping("/pedido/{idPedido}")
    public List<DetallePedido> buscarDetallesPorPedido(
            @PathVariable Integer idPedido
    ) {
        return detallePedidoService.buscarPorPedido(idPedido);
    }

    // BUSCAR DETALLES DE UN PRODUCTO
    @GetMapping("/producto/{idProducto}")
    public List<DetallePedido> buscarDetallesPorProducto(
            @PathVariable Integer idProducto
    ) {
        return detallePedidoService.buscarPorProducto(idProducto);
    }

    // BUSCAR DETALLE POR ID
    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> buscarDetallePorId(
            @PathVariable Integer id
    ) {
        DetallePedido detalle =
                detallePedidoService.obtenerPorId(id);

        return ResponseEntity.ok(detalle);
    }

    // CREAR DETALLE
    @PostMapping
    public ResponseEntity<DetallePedido> crearDetalle(
            @Valid @RequestBody DetallePedido detalle
    ) {
        DetallePedido nuevoDetalle =
                detallePedidoService.crearDetalle(detalle);

        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(nuevoDetalle);
    }

    // ACTUALIZAR DETALLE
    @PutMapping("/{id}")
    public ResponseEntity<DetallePedido> actualizarDetalle(
            @PathVariable Integer id,
            @Valid @RequestBody DetallePedido detalle
    ) {
        DetallePedido detalleActualizado =
                detallePedidoService.actualizarDetalle(
                        id,
                        detalle
                );

        return ResponseEntity.ok(detalleActualizado);
    }

    // ELIMINAR DETALLE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalle(
            @PathVariable Integer id
    ) {
        detallePedidoService.eliminarDetalle(id);

        return ResponseEntity.noContent().build();
    }
}