package com.florian.ceramicaswari.controller;

import com.florian.ceramicaswari.exception.RecursoNoEncontradoException;
import com.florian.ceramicaswari.model.Producto;
import com.florian.ceramicaswari.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService productoService;

    // =========================================================
    // TEST 1: LISTAR PRODUCTOS -> 200 OK
    // =========================================================
    @Test
    void listarProductosDebeRetornar200() throws Exception {

        Producto producto = new Producto();

        producto.setIdProducto(1);
        producto.setCodigoProducto("PROD-001");
        producto.setNombre("Jarra ceremonial");
        producto.setPrecioLista(new BigDecimal("120.00"));
        producto.setStockActual(10);
        producto.setDescripcionCultural(
                "Producto artesanal de prueba"
        );

        when(productoService.listarProductos())
                .thenReturn(List.of(producto));

        mockMvc.perform(
                        get("/api/productos")
                )
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$[0].idProducto")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$[0].codigoProducto")
                                .value("PROD-001")
                )
                .andExpect(
                        jsonPath("$[0].nombre")
                                .value("Jarra ceremonial")
                )
                .andExpect(
                        jsonPath("$[0].stockActual")
                                .value(10)
                );
    }

    // =========================================================
    // TEST 2: BUSCAR PRODUCTO POR ID -> 200 OK
    // =========================================================
    @Test
    void buscarProductoPorIdDebeRetornar200() throws Exception {

        Producto producto = new Producto();

        producto.setIdProducto(1);
        producto.setCodigoProducto("PROD-001");
        producto.setNombre("Jarra ceremonial");
        producto.setPrecioLista(new BigDecimal("120.00"));
        producto.setStockActual(10);
        producto.setDescripcionCultural(
                "Producto artesanal de prueba"
        );

        when(productoService.obtenerPorId(1))
                .thenReturn(producto);

        mockMvc.perform(
                        get("/api/productos/1")
                )
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.idProducto")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$.nombre")
                                .value("Jarra ceremonial")
                );
    }

    // =========================================================
    // TEST 3: CREAR PRODUCTO -> 201 CREATED
    // =========================================================
    @Test
    void crearProductoDebeRetornar201() throws Exception {

        Producto productoGuardado = new Producto();

        productoGuardado.setIdProducto(10);
        productoGuardado.setCodigoProducto("TEST-001");
        productoGuardado.setNombre("Producto de prueba");
        productoGuardado.setPrecioLista(
                new BigDecimal("50.00")
        );
        productoGuardado.setStockActual(5);
        productoGuardado.setDescripcionCultural(
                "Producto utilizado en prueba automática"
        );

        when(
                productoService.guardarProducto(
                        any(Producto.class)
                )
        ).thenReturn(productoGuardado);

        String json = """
                {
                    "codigoProducto": "TEST-001",
                    "nombre": "Producto de prueba",
                    "precioLista": 50.00,
                    "stockActual": 5,
                    "descripcionCultural": "Producto utilizado en prueba automática"
                }
                """;

        mockMvc.perform(
                        post("/api/productos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isCreated())
                .andExpect(
                        jsonPath("$.idProducto")
                                .value(10)
                )
                .andExpect(
                        jsonPath("$.nombre")
                                .value("Producto de prueba")
                );
    }

    // =========================================================
    // TEST 4: PRODUCTO INVALIDO -> 400 BAD REQUEST
    // =========================================================
    @Test
    void crearProductoInvalidoDebeRetornar400() throws Exception {

        String json = """
                {
                    "codigoProducto": "",
                    "nombre": "",
                    "precioLista": -10.00,
                    "stockActual": -5,
                    "descripcionCultural": "Producto inválido"
                }
                """;

        mockMvc.perform(
                        post("/api/productos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.codigoProducto")
                                .exists()
                )
                .andExpect(
                        jsonPath("$.nombre")
                                .exists()
                )
                .andExpect(
                        jsonPath("$.precioLista")
                                .exists()
                )
                .andExpect(
                        jsonPath("$.stockActual")
                                .exists()
                );
    }

    // =========================================================
    // TEST 5: PRODUCTO NO EXISTE -> 404 NOT FOUND
    // =========================================================
    @Test
    void buscarProductoInexistenteDebeRetornar404()
            throws Exception {

        when(productoService.obtenerPorId(999))
                .thenThrow(
                        new RecursoNoEncontradoException(
                                "Producto con ID 999 no encontrado"
                        )
                );

        mockMvc.perform(
                        get("/api/productos/999")
                )
                .andExpect(status().isNotFound())
                .andExpect(
                        jsonPath("$.mensaje")
                                .value(
                                        "Producto con ID 999 no encontrado"
                                )
                );
    }
}