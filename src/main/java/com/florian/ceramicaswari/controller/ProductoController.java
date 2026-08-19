package com.florian.ceramicaswari.controller;

import com.florian.ceramicaswari.model.Producto;
import com.florian.ceramicaswari.service.ProductoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(
        name = "Productos",
        description = "Gestión de productos artesanales"
)
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // LISTAR TODOS LOS PRODUCTOS
    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    // BUSCAR PRODUCTOS POR NOMBRE
    @GetMapping("/buscar")
    public List<Producto> buscarPorNombre(
            @RequestParam String nombre) {

        return productoService.buscarPorNombre(nombre);
    }

    // BUSCAR PRODUCTO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarProductoPorId(
            @PathVariable Integer id) {

        Producto producto = productoService.obtenerPorId(id);

        return ResponseEntity.ok(producto);
    }

    // CREAR PRODUCTO -> 201 CREATED
    @PostMapping
    public ResponseEntity<Producto> crearProducto(
            @Valid @RequestBody Producto producto) {

        Producto nuevoProducto =
                productoService.guardarProducto(producto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoProducto);
    }

    // ACTUALIZAR PRODUCTO -> 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Integer id,
            @Valid @RequestBody Producto producto) {

        productoService.obtenerPorId(id);

        producto.setIdProducto(id);

        Producto productoActualizado =
                productoService.guardarProducto(producto);

        return ResponseEntity.ok(productoActualizado);
    }

    // ELIMINAR PRODUCTO -> 204 NO CONTENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(
            @PathVariable Integer id) {

        productoService.obtenerPorId(id);

        productoService.eliminarProducto(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}