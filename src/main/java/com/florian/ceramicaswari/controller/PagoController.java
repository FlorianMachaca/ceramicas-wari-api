package com.florian.ceramicaswari.controller;

import com.florian.ceramicaswari.model.Pago;
import com.florian.ceramicaswari.service.PagoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@Tag(
        name = "Pagos",
        description = "Gestión de pagos asociados a los pedidos"
)
public class PagoController {

    private final PagoService pagoService;

    public PagoController(
            PagoService pagoService
    ) {
        this.pagoService = pagoService;
    }

    // LISTAR TODOS LOS PAGOS
    @GetMapping
    public List<Pago> listarPagos() {
        return pagoService.listarPagos();
    }

    // BUSCAR PAGO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Pago> buscarPagoPorId(
            @PathVariable Integer id
    ) {

        Pago pago = pagoService.obtenerPorId(id);

        return ResponseEntity.ok(pago);
    }

    // BUSCAR PAGOS DE UN PEDIDO
    @GetMapping("/pedido/{idPedido}")
    public List<Pago> buscarPagosPorPedido(
            @PathVariable Integer idPedido
    ) {

        return pagoService.buscarPorPedido(idPedido);
    }

    // BUSCAR PAGOS POR TIPO
    @GetMapping("/tipo/{tipoPago}")
    public List<Pago> buscarPagosPorTipo(
            @PathVariable String tipoPago
    ) {

        return pagoService.buscarPorTipo(tipoPago);
    }

    // CREAR PAGO -> 201 CREATED
    @PostMapping
    public ResponseEntity<Pago> crearPago(
            @Valid @RequestBody Pago pago
    ) {

        Pago nuevoPago =
                pagoService.crearPago(pago);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoPago);
    }

    // ACTUALIZAR PAGO -> 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizarPago(
            @PathVariable Integer id,
            @Valid @RequestBody Pago pago
    ) {

        Pago pagoActualizado =
                pagoService.actualizarPago(
                        id,
                        pago
                );

        return ResponseEntity.ok(pagoActualizado);
    }

    // ELIMINAR PAGO -> 204 NO CONTENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(
            @PathVariable Integer id
    ) {

        pagoService.eliminarPago(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}