package com.florian.ceramicaswari.service;

import com.florian.ceramicaswari.exception.RecursoNoEncontradoException;
import com.florian.ceramicaswari.model.Cliente;
import com.florian.ceramicaswari.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // LISTAR TODOS LOS CLIENTES
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // OBTENER CLIENTE POR ID
    public Cliente obtenerPorId(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cliente con ID " + id + " no encontrado"
                        )
                );
    }

    // BUSCAR CLIENTES POR NOMBRE O RAZÓN SOCIAL
    public List<Cliente> buscarPorNombre(String nombre) {
        return clienteRepository
                .findByNombresRazonSocialContainingIgnoreCase(nombre);
    }

    // CREAR O ACTUALIZAR CLIENTE
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // ELIMINAR CLIENTE
    public void eliminarCliente(Integer id) {
        obtenerPorId(id);
        clienteRepository.deleteById(id);
    }
}