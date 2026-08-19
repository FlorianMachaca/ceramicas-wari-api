package com.florian.ceramicaswari.controller;

import com.florian.ceramicaswari.model.Artesano;
import com.florian.ceramicaswari.service.ArtesanoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artesanos")
@Tag(
        name = "Artesanos",
        description = "Gestión de artesanos responsables de la producción"
)
public class ArtesanoController {

    private final ArtesanoService artesanoService;

    public ArtesanoController(
            ArtesanoService artesanoService
    ) {
        this.artesanoService = artesanoService;
    }

    // LISTAR TODOS LOS ARTESANOS
    @GetMapping
    public List<Artesano> listarArtesanos() {
        return artesanoService.listarArtesanos();
    }

    // BUSCAR ARTESANO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Artesano> buscarPorId(
            @PathVariable Integer id
    ) {

        Artesano artesano =
                artesanoService.obtenerPorId(id);

        return ResponseEntity.ok(artesano);
    }

    // CREAR ARTESANO -> 201 CREATED
    @PostMapping
    public ResponseEntity<Artesano> crearArtesano(
            @Valid @RequestBody Artesano artesano
    ) {

        Artesano nuevoArtesano =
                artesanoService.crearArtesano(artesano);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoArtesano);
    }

    // ACTUALIZAR ARTESANO -> 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<Artesano> actualizarArtesano(
            @PathVariable Integer id,
            @Valid @RequestBody Artesano artesano
    ) {

        Artesano artesanoActualizado =
                artesanoService.actualizarArtesano(
                        id,
                        artesano
                );

        return ResponseEntity.ok(artesanoActualizado);
    }

    // ELIMINAR ARTESANO -> 204 NO CONTENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArtesano(
            @PathVariable Integer id
    ) {

        artesanoService.eliminarArtesano(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}