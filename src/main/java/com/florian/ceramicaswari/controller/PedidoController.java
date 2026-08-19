package com.florian.ceramicaswari.controller;

import com.florian.ceramicaswari.model.Pedido;
import com.florian.ceramicaswari.service.PedidoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(
        name = "Pedidos",
        description = "Gestión de pedidos realizados por los clientes"
)
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // LISTAR TODOS LOS PEDIDOS
    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    // BUSCAR PEDIDO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(
            @PathVariable Integer id) {

        Pedido pedido =
                pedidoService.obtenerPorId(id);

        return ResponseEntity.ok(pedido);
    }

    // BUSCAR PEDIDOS POR CLIENTE
    @GetMapping("/cliente/{idCliente}")
    public List<Pedido> buscarPedidosPorCliente(
            @PathVariable Integer idCliente) {

        return pedidoService.buscarPorCliente(idCliente);
    }

    // BUSCAR PEDIDOS POR ESTADO
    @GetMapping("/buscar")
    public List<Pedido> buscarPedidosPorEstado(
            @RequestParam String estado) {

        return pedidoService.buscarPorEstado(estado);
    }

    // CREAR PEDIDO -> 201 CREATED
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(
            @Valid @RequestBody Pedido pedido) {

        Pedido nuevoPedido =
                pedidoService.crearPedido(pedido);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoPedido);
    }

    // ACTUALIZAR PEDIDO -> 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(
            @PathVariable Integer id,
            @Valid @RequestBody Pedido pedido) {

        Pedido pedidoActualizado =
                pedidoService.actualizarPedido(
                        id,
                        pedido
                );

        return ResponseEntity.ok(pedidoActualizado);
    }

    // ELIMINAR PEDIDO -> 204 NO CONTENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(
            @PathVariable Integer id) {

        pedidoService.eliminarPedido(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}