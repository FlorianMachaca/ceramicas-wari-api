package com.florian.ceramicaswari.service;

import com.florian.ceramicaswari.exception.RecursoNoEncontradoException;
import com.florian.ceramicaswari.model.MateriaPrima;
import com.florian.ceramicaswari.repository.MateriaPrimaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaPrimaService {

    private final MateriaPrimaRepository materiaPrimaRepository;
    private final ProveedorService proveedorService;

    public MateriaPrimaService(
            MateriaPrimaRepository materiaPrimaRepository,
            ProveedorService proveedorService
    ) {
        this.materiaPrimaRepository = materiaPrimaRepository;
        this.proveedorService = proveedorService;
    }

    // LISTAR TODAS LAS MATERIAS PRIMAS
    public List<MateriaPrima> listarMateriasPrimas() {
        return materiaPrimaRepository.findAll();
    }

    // OBTENER MATERIA PRIMA POR ID
    public MateriaPrima obtenerPorId(Integer id) {
        return materiaPrimaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Materia prima con ID " + id + " no encontrada"
                        )
                );
    }

    // BUSCAR POR NOMBRE
    public List<MateriaPrima> buscarPorNombre(String nombre) {
        return materiaPrimaRepository
                .findByNombreMaterialContainingIgnoreCase(nombre);
    }

    // BUSCAR POR PROVEEDOR
    public List<MateriaPrima> buscarPorProveedor(Integer idProveedor) {

        // Verificar que el proveedor exista
        proveedorService.obtenerPorId(idProveedor);

        return materiaPrimaRepository
                .findByIdProveedor(idProveedor);
    }

    // BUSCAR MATERIALES CON STOCK BAJO
    public List<MateriaPrima> buscarStockBajo() {
        return materiaPrimaRepository.buscarConStockBajo();
    }

    // CREAR MATERIA PRIMA
    public MateriaPrima crearMateriaPrima(MateriaPrima materiaPrima) {

        // Verificar que el proveedor exista
        proveedorService.obtenerPorId(
                materiaPrima.getIdProveedor()
        );

        // SQL Server genera el ID
        materiaPrima.setIdMateria(null);

        return materiaPrimaRepository.save(materiaPrima);
    }

    // ACTUALIZAR MATERIA PRIMA
    public MateriaPrima actualizarMateriaPrima(
            Integer id,
            MateriaPrima materiaPrima
    ) {

        // Verificar que la materia exista
        obtenerPorId(id);

        // Verificar proveedor
        proveedorService.obtenerPorId(
                materiaPrima.getIdProveedor()
        );

        materiaPrima.setIdMateria(id);

        return materiaPrimaRepository.save(materiaPrima);
    }

    // ELIMINAR MATERIA PRIMA
    public void eliminarMateriaPrima(Integer id) {

        obtenerPorId(id);

        materiaPrimaRepository.deleteById(id);
    }
}