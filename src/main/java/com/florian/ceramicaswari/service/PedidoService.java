package com.florian.ceramicaswari.service;

import com.florian.ceramicaswari.exception.RecursoNoEncontradoException;
import com.florian.ceramicaswari.model.Pedido;
import com.florian.ceramicaswari.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;

    public PedidoService(
            PedidoRepository pedidoRepository,
            ClienteService clienteService
    ) {
        this.pedidoRepository = pedidoRepository;
        this.clienteService = clienteService;
    }

    // LISTAR TODOS LOS PEDIDOS
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    // OBTENER PEDIDO POR ID
    public Pedido obtenerPorId(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Pedido con ID " + id + " no encontrado"
                        )
                );
    }

    // BUSCAR PEDIDOS DE UN CLIENTE
    public List<Pedido> buscarPorCliente(Integer idCliente) {

        // Primero comprobamos que el cliente exista
        clienteService.obtenerPorId(idCliente);

        return pedidoRepository.findByIdCliente(idCliente);
    }

    // BUSCAR PEDIDOS POR ESTADO
    public List<Pedido> buscarPorEstado(String estado) {
        return pedidoRepository
                .findByEstadoContainingIgnoreCase(estado);
    }

    // CREAR PEDIDO
    public Pedido crearPedido(Pedido pedido) {

        // No permitir pedidos para clientes inexistentes
        clienteService.obtenerPorId(pedido.getIdCliente());

        pedido.setIdPedido(null);

        return pedidoRepository.save(pedido);
    }

    // ACTUALIZAR PEDIDO
    public Pedido actualizarPedido(
            Integer id,
            Pedido pedido
    ) {

        // Verificar que el pedido exista
        obtenerPorId(id);

        // Verificar que el cliente exista
        clienteService.obtenerPorId(pedido.getIdCliente());

        pedido.setIdPedido(id);

        return pedidoRepository.save(pedido);
    }

    // ELIMINAR PEDIDO
    public void eliminarPedido(Integer id) {

        // Si no existe, genera nuestro 404 personalizado
        obtenerPorId(id);

        pedidoRepository.deleteById(id);
    }
}