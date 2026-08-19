package com.florian.ceramicaswari.controller;

import com.florian.ceramicaswari.model.Proveedor;
import com.florian.ceramicaswari.service.ProveedorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@Tag(
        name = "Proveedores",
        description = "Gestión de proveedores de materiales e insumos"
)
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(
            ProveedorService proveedorService
    ) {
        this.proveedorService = proveedorService;
    }

    // LISTAR TODOS LOS PROVEEDORES
    @GetMapping
    public List<Proveedor> listarProveedores() {
        return proveedorService.listarProveedores();
    }

    // BUSCAR PROVEEDOR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> buscarPorId(
            @PathVariable Integer id
    ) {

        Proveedor proveedor =
                proveedorService.obtenerPorId(id);

        return ResponseEntity.ok(proveedor);
    }

    // BUSCAR POR RAZON SOCIAL
    @GetMapping("/buscar")
    public List<Proveedor> buscarPorNombre(
            @RequestParam String nombre
    ) {

        return proveedorService.buscarPorRazonSocial(nombre);
    }

    // BUSCAR POR LOCALIDAD
    @GetMapping("/localidad/{localidad}")
    public List<Proveedor> buscarPorLocalidad(
            @PathVariable String localidad
    ) {

        return proveedorService.buscarPorLocalidad(localidad);
    }

    // CREAR PROVEEDOR -> 201 CREATED
    @PostMapping
    public ResponseEntity<Proveedor> crearProveedor(
            @Valid @RequestBody Proveedor proveedor
    ) {

        Proveedor nuevoProveedor =
                proveedorService.crearProveedor(proveedor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoProveedor);
    }

    // ACTUALIZAR PROVEEDOR -> 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(
            @PathVariable Integer id,
            @Valid @RequestBody Proveedor proveedor
    ) {

        Proveedor proveedorActualizado =
                proveedorService.actualizarProveedor(
                        id,
                        proveedor
                );

        return ResponseEntity.ok(proveedorActualizado);
    }

    // ELIMINAR PROVEEDOR -> 204 NO CONTENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(
            @PathVariable Integer id
    ) {

        proveedorService.eliminarProveedor(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}