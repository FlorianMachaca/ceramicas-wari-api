package com.florian.ceramicaswari.controller;

import com.florian.ceramicaswari.model.DetallePedido;
import com.florian.ceramicaswari.service.DetallePedidoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-pedido")
@Tag(
        name = "Detalles de pedido",
        description = "Gestión de productos y cantidades asociados a los pedidos"
)
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

    // BUSCAR DETALLE POR ID
    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> buscarPorId(
            @PathVariable Integer id
    ) {

        DetallePedido detalle =
                detallePedidoService.obtenerPorId(id);

        return ResponseEntity.ok(detalle);
    }

    // BUSCAR DETALLES POR PEDIDO
    @GetMapping("/pedido/{idPedido}")
    public List<DetallePedido> buscarPorPedido(
            @PathVariable Integer idPedido
    ) {

        return detallePedidoService.buscarPorPedido(idPedido);
    }

    // BUSCAR DETALLES POR PRODUCTO
    @GetMapping("/producto/{idProducto}")
    public List<DetallePedido> buscarPorProducto(
            @PathVariable Integer idProducto
    ) {

        return detallePedidoService.buscarPorProducto(idProducto);
    }

    // CREAR DETALLE -> 201 CREATED
    @PostMapping
    public ResponseEntity<DetallePedido> crearDetalle(
            @Valid @RequestBody DetallePedido detallePedido
    ) {

        DetallePedido nuevoDetalle =
                detallePedidoService.crearDetalle(detallePedido);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoDetalle);
    }

    // ACTUALIZAR DETALLE -> 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<DetallePedido> actualizarDetalle(
            @PathVariable Integer id,
            @Valid @RequestBody DetallePedido detallePedido
    ) {

        DetallePedido detalleActualizado =
                detallePedidoService.actualizarDetalle(
                        id,
                        detallePedido
                );

        return ResponseEntity.ok(detalleActualizado);
    }

    // ELIMINAR DETALLE -> 204 NO CONTENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalle(
            @PathVariable Integer id
    ) {

        detallePedidoService.eliminarDetalle(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}