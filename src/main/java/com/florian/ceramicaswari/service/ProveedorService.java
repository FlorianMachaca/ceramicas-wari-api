package com.florian.ceramicaswari.service;

import com.florian.ceramicaswari.exception.RecursoNoEncontradoException;
import com.florian.ceramicaswari.model.Proveedor;
import com.florian.ceramicaswari.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(
            ProveedorRepository proveedorRepository
    ) {
        this.proveedorRepository = proveedorRepository;
    }

    // LISTAR TODOS LOS PROVEEDORES
    public List<Proveedor> listarProveedores() {
        return proveedorRepository.findAll();
    }

    // OBTENER PROVEEDOR POR ID
    public Proveedor obtenerPorId(Integer id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Proveedor con ID " + id + " no encontrado"
                        )
                );
    }

    // BUSCAR POR RAZÓN SOCIAL
    public List<Proveedor> buscarPorRazonSocial(String razonSocial) {
        return proveedorRepository
                .findByRazonSocialContainingIgnoreCase(razonSocial);
    }

    // BUSCAR POR LOCALIDAD
    public List<Proveedor> buscarPorLocalidad(String localidad) {
        return proveedorRepository
                .findByLocalidadIgnoreCase(localidad);
    }

    // CREAR PROVEEDOR
    public Proveedor crearProveedor(Proveedor proveedor) {

        // El ID lo genera SQL Server
        proveedor.setIdProveedor(null);

        return proveedorRepository.save(proveedor);
    }

    // ACTUALIZAR PROVEEDOR
    public Proveedor actualizarProveedor(
            Integer id,
            Proveedor proveedor
    ) {

        // Verificar que exista
        obtenerPorId(id);

        proveedor.setIdProveedor(id);

        return proveedorRepository.save(proveedor);
    }

    // ELIMINAR PROVEEDOR
    public void eliminarProveedor(Integer id) {

        // Genera 404 si no existe
        obtenerPorId(id);

        proveedorRepository.deleteById(id);
    }
}