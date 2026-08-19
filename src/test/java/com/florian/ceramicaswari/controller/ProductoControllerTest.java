package com.florian.ceramicaswari.controller;

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

    // TEST 1: LISTAR PRODUCTOS
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

    // TEST 2: BUSCAR PRODUCTO POR ID
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

    // TEST 3: CREAR PRODUCTO Y COMPROBAR HTTP 201
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

        when(productoService.guardarProducto(any(Producto.class)))
                .thenReturn(productoGuardado);

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
}