package com.florian.ceramicaswari.service;

import com.florian.ceramicaswari.exception.RecursoNoEncontradoException;
import com.florian.ceramicaswari.model.Produccion;
import com.florian.ceramicaswari.repository.ProduccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduccionService {

    private final ProduccionRepository produccionRepository;
    private final ProductoService productoService;
    private final ArtesanoService artesanoService;

    public ProduccionService(
            ProduccionRepository produccionRepository,
            ProductoService productoService,
            ArtesanoService artesanoService
    ) {
        this.produccionRepository = produccionRepository;
        this.productoService = productoService;
        this.artesanoService = artesanoService;
    }

    // LISTAR TODAS LAS PRODUCCIONES
    public List<Produccion> listarProducciones() {
        return produccionRepository.findAll();
    }

    // BUSCAR PRODUCCIÓN POR ID
    public Produccion obtenerPorId(Integer id) {
        return produccionRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Producción con ID " + id + " no encontrada"
                        )
                );
    }

    // BUSCAR PRODUCCIONES POR PRODUCTO
    public List<Produccion> buscarPorProducto(Integer idProducto) {

        productoService.obtenerPorId(idProducto);

        return produccionRepository.findByIdProducto(idProducto);
    }

    // BUSCAR PRODUCCIONES POR ARTESANO
    public List<Produccion> buscarPorArtesano(Integer idArtesano) {

        artesanoService.obtenerPorId(idArtesano);

        return produccionRepository.findByIdArtesano(idArtesano);
    }

    // BUSCAR PRODUCCIONES POR ETAPA
    public List<Produccion> buscarPorEtapa(String etapaActual) {

        return produccionRepository
                .findByEtapaActualContainingIgnoreCase(etapaActual);
    }

    // CREAR PRODUCCIÓN
    public Produccion crearProduccion(Produccion produccion) {

        productoService.obtenerPorId(
                produccion.getIdProducto()
        );

        artesanoService.obtenerPorId(
                produccion.getIdArtesano()
        );

        produccion.setIdProduccion(null);

        return produccionRepository.save(produccion);
    }

    // ACTUALIZAR PRODUCCIÓN
    public Produccion actualizarProduccion(
            Integer id,
            Produccion produccion
    ) {

        obtenerPorId(id);

        productoService.obtenerPorId(
                produccion.getIdProducto()
        );

        artesanoService.obtenerPorId(
                produccion.getIdArtesano()
        );

        produccion.setIdProduccion(id);

        return produccionRepository.save(produccion);
    }

    // ELIMINAR PRODUCCIÓN
    public void eliminarProduccion(Integer id) {

        obtenerPorId(id);

        produccionRepository.deleteById(id);
    }
}