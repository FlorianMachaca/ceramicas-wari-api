package com.florian.ceramicaswari.controller;

import com.florian.ceramicaswari.model.MateriaPrima;
import com.florian.ceramicaswari.service.MateriaPrimaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materias-primas")
@Tag(
        name = "Materias primas",
        description = "Gestión de materiales, proveedores y control de stock"
)
public class MateriaPrimaController {

    private final MateriaPrimaService materiaPrimaService;

    public MateriaPrimaController(
            MateriaPrimaService materiaPrimaService
    ) {
        this.materiaPrimaService = materiaPrimaService;
    }

    // LISTAR TODAS LAS MATERIAS PRIMAS
    @GetMapping
    public List<MateriaPrima> listarMateriasPrimas() {
        return materiaPrimaService.listarMateriasPrimas();
    }

    // BUSCAR MATERIA PRIMA POR ID
    @GetMapping("/{id}")
    public ResponseEntity<MateriaPrima> buscarPorId(
            @PathVariable Integer id
    ) {

        MateriaPrima materiaPrima =
                materiaPrimaService.obtenerPorId(id);

        return ResponseEntity.ok(materiaPrima);
    }

    // BUSCAR POR NOMBRE
    @GetMapping("/buscar")
    public List<MateriaPrima> buscarPorNombre(
            @RequestParam String nombre
    ) {

        return materiaPrimaService.buscarPorNombre(nombre);
    }

    // BUSCAR POR PROVEEDOR
    @GetMapping("/proveedor/{idProveedor}")
    public List<MateriaPrima> buscarPorProveedor(
            @PathVariable Integer idProveedor
    ) {

        return materiaPrimaService.buscarPorProveedor(idProveedor);
    }

    // BUSCAR MATERIAS PRIMAS CON STOCK BAJO
    @GetMapping("/stock-bajo")
    public List<MateriaPrima> listarStockBajo() {

        return materiaPrimaService.buscarStockBajo();
    }

    // CREAR MATERIA PRIMA -> 201 CREATED
    @PostMapping
    public ResponseEntity<MateriaPrima> crearMateriaPrima(
            @Valid @RequestBody MateriaPrima materiaPrima
    ) {

        MateriaPrima nuevaMateriaPrima =
                materiaPrimaService.crearMateriaPrima(materiaPrima);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaMateriaPrima);
    }

    // ACTUALIZAR MATERIA PRIMA -> 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<MateriaPrima> actualizarMateriaPrima(
            @PathVariable Integer id,
            @Valid @RequestBody MateriaPrima materiaPrima
    ) {

        MateriaPrima materiaPrimaActualizada =
                materiaPrimaService.actualizarMateriaPrima(
                        id,
                        materiaPrima
                );

        return ResponseEntity.ok(materiaPrimaActualizada);
    }

    // ELIMINAR MATERIA PRIMA -> 204 NO CONTENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMateriaPrima(
            @PathVariable Integer id
    ) {

        materiaPrimaService.eliminarMateriaPrima(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
