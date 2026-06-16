package com.smartlogix.pedidos.service;

import com.smartlogix.pedidos.model.EstadoPedido;
import com.smartlogix.pedidos.model.Pedido;
import com.smartlogix.pedidos.repository.PedidoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    private Pedido crearPedido(Long id, String cliente, EstadoPedido estado) {
        Pedido p = new Pedido();
        p.setId(id);
        p.setClienteNombre(cliente);
        p.setProductoId(1L);
        p.setProductoNombre("Laptop");
        p.setCantidad(2);
        p.setPrecioUnitario(999.99);
        p.setTotal(1999.98);
        p.setEstado(estado);
        p.setFechaPedido(LocalDateTime.now());
        return p;
    }

    @Test
    @DisplayName("CP-009: Debe guardar pedido correctamente")
    void debeGuardarPedido() {
        Pedido nuevo = crearPedido(null, "Juan Perez", EstadoPedido.PENDIENTE);
        Pedido guardado = crearPedido(1L, "Juan Perez", EstadoPedido.PENDIENTE);
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(guardado);

        Pedido resultado = pedidoService.guardar(nuevo);

        assertNotNull(resultado.getId());
        assertEquals("Juan Perez", resultado.getClienteNombre());
        assertEquals(EstadoPedido.PENDIENTE, resultado.getEstado());
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    @DisplayName("CP-010: Debe listar todos los pedidos")
    void debeListarTodos() {
        List<Pedido> lista = Arrays.asList(
            crearPedido(1L, "Juan Perez", EstadoPedido.PENDIENTE),
            crearPedido(2L, "Maria Lopez", EstadoPedido.EN_PROCESO)
        );
        when(pedidoRepository.findAll()).thenReturn(lista);

        List<Pedido> resultado = pedidoService.listarTodos();

        assertEquals(2, resultado.size());
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("CP-011: Debe buscar pedido por ID existente")
    void debeBuscarPorIdExistente() {
        Pedido p = crearPedido(1L, "Juan Perez", EstadoPedido.PENDIENTE);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(p));

        Optional<Pedido> resultado = pedidoService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Juan Perez", resultado.get().getClienteNombre());
    }

    @Test
    @DisplayName("CP-024: Debe retornar empty si pedido no existe")
    void debeRetornarEmptyPedidoNoExiste() {
        when(pedidoRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Pedido> resultado = pedidoService.buscarPorId(999L);

        assertFalse(resultado.isPresent());
    }

    @Test
    @DisplayName("CP-012: Debe cambiar estado de pedido a EN_PROCESO")
    void debeCambiarEstadoPedido() {
        Pedido existente = crearPedido(1L, "Juan Perez", EstadoPedido.PENDIENTE);
        Pedido actualizado = crearPedido(1L, "Juan Perez", EstadoPedido.EN_PROCESO);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(actualizado);

        Pedido resultado = pedidoService.cambiarEstado(1L, EstadoPedido.EN_PROCESO);

        assertEquals(EstadoPedido.EN_PROCESO, resultado.getEstado());
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    @DisplayName("CP-012b: Debe lanzar excepcion al cambiar estado de pedido inexistente")
    void debeLanzarExcepcionCambiarEstadoPedidoInexistente() {
        when(pedidoRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
            () -> pedidoService.cambiarEstado(999L, EstadoPedido.EN_PROCESO));
    }

    @Test
    @DisplayName("CP-013: Debe eliminar pedido")
    void debeEliminarPedido() {
        doNothing().when(pedidoRepository).deleteById(1L);

        assertDoesNotThrow(() -> pedidoService.eliminar(1L));
        verify(pedidoRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("CP-010b: Debe buscar pedidos por estado PENDIENTE")
    void debeBuscarPorEstado() {
        List<Pedido> lista = Arrays.asList(
            crearPedido(1L, "Juan", EstadoPedido.PENDIENTE),
            crearPedido(2L, "Maria", EstadoPedido.PENDIENTE)
        );
        when(pedidoRepository.findByEstado(EstadoPedido.PENDIENTE)).thenReturn(lista);

        List<Pedido> resultado = pedidoService.buscarPorEstado(EstadoPedido.PENDIENTE);

        assertEquals(2, resultado.size());
        assertEquals(EstadoPedido.PENDIENTE, resultado.get(0).getEstado());
    }
}