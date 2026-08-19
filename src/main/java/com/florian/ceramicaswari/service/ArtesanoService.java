package com.florian.ceramicaswari.service;

import com.florian.ceramicaswari.exception.RecursoNoEncontradoException;
import com.florian.ceramicaswari.model.Artesano;
import com.florian.ceramicaswari.repository.ArtesanoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtesanoService {

    private final ArtesanoRepository artesanoRepository;

    public ArtesanoService(
            ArtesanoRepository artesanoRepository
    ) {
        this.artesanoRepository = artesanoRepository;
    }

    // LISTAR TODOS LOS ARTESANOS
    public List<Artesano> listarArtesanos() {
        return artesanoRepository.findAll();
    }

    // OBTENER ARTESANO POR ID
    public Artesano obtenerPorId(Integer id) {
        return artesanoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Artesano con ID " + id + " no encontrado"
                        )
                );
    }

    // CREAR ARTESANO
    public Artesano crearArtesano(Artesano artesano) {

        // SQL Server genera el ID automáticamente
        artesano.setIdArtesano(null);

        return artesanoRepository.save(artesano);
    }

    // ACTUALIZAR ARTESANO
    public Artesano actualizarArtesano(
            Integer id,
            Artesano artesano
    ) {

        // Comprobar que exista
        obtenerPorId(id);

        artesano.setIdArtesano(id);

        return artesanoRepository.save(artesano);
    }

    // ELIMINAR ARTESANO
    public void eliminarArtesano(Integer id) {

        obtenerPorId(id);

        artesanoRepository.deleteById(id);
    }
}