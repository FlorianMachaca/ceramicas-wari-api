package com.florian.ceramicaswari.service;

import com.florian.ceramicaswari.exception.ConflictoDatosException;
import com.florian.ceramicaswari.exception.RecursoNoEncontradoException;
import com.florian.ceramicaswari.model.Exportacion;
import com.florian.ceramicaswari.repository.ExportacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportacionService {

    private final ExportacionRepository exportacionRepository;
    private final PedidoService pedidoService;

    public ExportacionService(
            ExportacionRepository exportacionRepository,
            PedidoService pedidoService
    ) {
        this.exportacionRepository = exportacionRepository;
        this.pedidoService = pedidoService;
    }

    // LISTAR TODAS LAS EXPORTACIONES
    public List<Exportacion> listarExportaciones() {
        return exportacionRepository.findAll();
    }

    // OBTENER EXPORTACION POR ID
    public Exportacion obtenerPorId(Integer id) {
        return exportacionRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Exportación con ID " + id + " no encontrada"
                        )
                );
    }

    // BUSCAR EXPORTACIONES POR PEDIDO
    public List<Exportacion> buscarPorPedido(Integer idPedido) {

        // Verificar que el pedido exista
        pedidoService.obtenerPorId(idPedido);

        return exportacionRepository.findByIdPedido(idPedido);
    }

    // BUSCAR EXPORTACIONES POR ESTADO
    public List<Exportacion> buscarPorEstado(String estadoEnvio) {
        return exportacionRepository
                .findByEstadoEnvioIgnoreCase(estadoEnvio);
    }

    // BUSCAR EXPORTACIONES POR COURIER
    public List<Exportacion> buscarPorCourier(String courier) {
        return exportacionRepository
                .findByCourierContainingIgnoreCase(courier);
    }

    // CREAR EXPORTACION
    public Exportacion crearExportacion(
            Exportacion exportacion
    ) {

        Integer idPedido = exportacion.getIdPedido();

        // Verificar que el pedido exista
        pedidoService.obtenerPorId(idPedido);

        // Un pedido solo puede tener una exportación
        if (exportacionRepository.existsByIdPedido(idPedido)) {
            throw new ConflictoDatosException(
                    "El pedido con ID " + idPedido
                            + " ya tiene una exportación registrada"
            );
        }

        // SQL Server genera el ID
        exportacion.setIdExportacion(null);

        return exportacionRepository.save(exportacion);
    }

    // ACTUALIZAR EXPORTACION
    public Exportacion actualizarExportacion(
            Integer id,
            Exportacion exportacion
    ) {

        // Verificar que la exportación exista
        obtenerPorId(id);

        Integer idPedido = exportacion.getIdPedido();

        // Verificar que el pedido exista
        pedidoService.obtenerPorId(idPedido);

        // Evitar asignar un pedido que ya pertenece
        // a otra exportación
        if (exportacionRepository
                .existsByIdPedidoAndIdExportacionNot(
                        idPedido,
                        id
                )) {

            throw new ConflictoDatosException(
                    "El pedido con ID " + idPedido
                            + " ya tiene una exportación registrada"
            );
        }

        exportacion.setIdExportacion(id);

        return exportacionRepository.save(exportacion);
    }

    // ELIMINAR EXPORTACION
    public void eliminarExportacion(Integer id) {

        obtenerPorId(id);

        exportacionRepository.deleteById(id);
    }
}