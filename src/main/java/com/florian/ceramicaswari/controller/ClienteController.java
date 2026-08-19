package com.florian.ceramicaswari.controller;

import com.florian.ceramicaswari.model.Cliente;
import com.florian.ceramicaswari.service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(
        name = "Clientes",
        description = "Gestión de clientes nacionales e internacionales"
)
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // LISTAR TODOS LOS CLIENTES
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    // BUSCAR CLIENTE POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(
            @PathVariable Integer id) {

        Cliente cliente =
                clienteService.obtenerPorId(id);

        return ResponseEntity.ok(cliente);
    }

    // BUSCAR CLIENTES POR NOMBRE O RAZON SOCIAL
    @GetMapping("/buscar")
    public List<Cliente> buscarClientesPorNombre(
            @RequestParam String nombre) {

        return clienteService.buscarPorNombre(nombre);
    }

    // CREAR CLIENTE -> 201 CREATED
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(
            @Valid @RequestBody Cliente cliente) {

        cliente.setIdCliente(null);

        Cliente nuevoCliente =
                clienteService.guardarCliente(cliente);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoCliente);
    }

    // ACTUALIZAR CLIENTE -> 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(
            @PathVariable Integer id,
            @Valid @RequestBody Cliente cliente) {

        clienteService.obtenerPorId(id);

        cliente.setIdCliente(id);

        Cliente clienteActualizado =
                clienteService.guardarCliente(cliente);

        return ResponseEntity.ok(clienteActualizado);
    }

    // ELIMINAR CLIENTE -> 204 NO CONTENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(
            @PathVariable Integer id) {

        clienteService.eliminarCliente(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}