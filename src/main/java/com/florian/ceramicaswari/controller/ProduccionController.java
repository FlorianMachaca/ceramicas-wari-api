package com.florian.ceramicaswari.controller;

import com.florian.ceramicaswari.model.Produccion;
import com.florian.ceramicaswari.service.ProduccionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producciones")
public class ProduccionController {

    private final ProduccionService produccionService;

    public ProduccionController(
            ProduccionService produccionService
    ) {
        this.produccionService = produccionService;
    }

    // LISTAR TODAS LAS PRODUCCIONES
    @GetMapping
    public List<Produccion> listarProducciones() {
        return produccionService.listarProducciones();
    }

    // BUSCAR PRODUCCION POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Produccion> buscarPorId(
            @PathVariable Integer id
    ) {

        Produccion produccion =
                produccionService.obtenerPorId(id);

        return ResponseEntity.ok(produccion);
    }

    // BUSCAR PRODUCCIONES POR PRODUCTO
    @GetMapping("/producto/{idProducto}")
    public List<Produccion> buscarPorProducto(
            @PathVariable Integer idProducto
    ) {

        return produccionService.buscarPorProducto(idProducto);
    }

    // BUSCAR PRODUCCIONES POR ARTESANO
    @GetMapping("/artesano/{idArtesano}")
    public List<Produccion> buscarPorArtesano(
            @PathVariable Integer idArtesano
    ) {

        return produccionService.buscarPorArtesano(idArtesano);
    }

    // BUSCAR PRODUCCIONES POR ETAPA
    @GetMapping("/buscar")
    public List<Produccion> buscarPorEtapa(
            @RequestParam String etapa
    ) {

        return produccionService.buscarPorEtapa(etapa);
    }

    // CREAR PRODUCCION -> 201 CREATED
    @PostMapping
    public ResponseEntity<Produccion> crearProduccion(
            @Valid @RequestBody Produccion produccion
    ) {

        Produccion nuevaProduccion =
                produccionService.crearProduccion(produccion);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaProduccion);
    }

    // ACTUALIZAR PRODUCCION -> 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<Produccion> actualizarProduccion(
            @PathVariable Integer id,
            @Valid @RequestBody Produccion produccion
    ) {

        Produccion produccionActualizada =
                produccionService.actualizarProduccion(
                        id,
                        produccion
                );

        return ResponseEntity.ok(produccionActualizada);
    }

    // ELIMINAR PRODUCCION -> 204 NO CONTENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProduccion(
            @PathVariable Integer id
    ) {

        produccionService.eliminarProduccion(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}