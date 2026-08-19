package com.florian.ceramicaswari.controller;

import com.florian.ceramicaswari.model.Exportacion;
import com.florian.ceramicaswari.service.ExportacionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exportaciones")
public class ExportacionController {

    private final ExportacionService exportacionService;

    public ExportacionController(
            ExportacionService exportacionService
    ) {
        this.exportacionService = exportacionService;
    }

    // LISTAR TODAS LAS EXPORTACIONES
    @GetMapping
    public List<Exportacion> listarExportaciones() {
        return exportacionService.listarExportaciones();
    }

    // BUSCAR EXPORTACION POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Exportacion> buscarExportacionPorId(
            @PathVariable Integer id
    ) {

        Exportacion exportacion =
                exportacionService.obtenerPorId(id);

        return ResponseEntity.ok(exportacion);
    }

    // BUSCAR EXPORTACIONES POR PEDIDO
    @GetMapping("/pedido/{idPedido}")
    public List<Exportacion> buscarPorPedido(
            @PathVariable Integer idPedido
    ) {

        return exportacionService.buscarPorPedido(idPedido);
    }

    // BUSCAR EXPORTACIONES POR ESTADO
    @GetMapping("/estado/{estadoEnvio}")
    public List<Exportacion> buscarPorEstado(
            @PathVariable String estadoEnvio
    ) {

        return exportacionService.buscarPorEstado(estadoEnvio);
    }

    // BUSCAR EXPORTACIONES POR COURIER
    @GetMapping("/buscar")
    public List<Exportacion> buscarPorCourier(
            @RequestParam String courier
    ) {

        return exportacionService.buscarPorCourier(courier);
    }

    // CREAR EXPORTACION -> 201 CREATED
    @PostMapping
    public ResponseEntity<Exportacion> crearExportacion(
            @Valid @RequestBody Exportacion exportacion
    ) {

        Exportacion nuevaExportacion =
                exportacionService.crearExportacion(exportacion);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaExportacion);
    }

    // ACTUALIZAR EXPORTACION -> 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<Exportacion> actualizarExportacion(
            @PathVariable Integer id,
            @Valid @RequestBody Exportacion exportacion
    ) {

        Exportacion exportacionActualizada =
                exportacionService.actualizarExportacion(
                        id,
                        exportacion
                );

        return ResponseEntity.ok(exportacionActualizada);
    }

    // ELIMINAR EXPORTACION -> 204 NO CONTENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarExportacion(
            @PathVariable Integer id
    ) {

        exportacionService.eliminarExportacion(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}