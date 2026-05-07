package com.smartlogix.pedidos;

import com.smartlogix.pedidos.model.EstadoPedido;
import com.smartlogix.pedidos.model.Pedido;
import com.smartlogix.pedidos.repository.PedidoRepository;
import com.smartlogix.pedidos.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidosMsApplicationTests {

    @Mock
    private PedidoRepository pedidoRepository;

    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pedidoService = new PedidoService(pedidoRepository);
    }

    @Test
    void listarTodos_retornaLista() {
        Pedido p = new Pedido();
        p.setId(1L);
        p.setClienteNombre("Juan");
        when(pedidoRepository.findAll()).thenReturn(List.of(p));

        List<Pedido> result = pedidoService.listarTodos();

        assertEquals(1, result.size());
        assertEquals("Juan", result.get(0).getClienteNombre());
    }

    @Test
    void buscarPorId_retornaPedido() {
        Pedido p = new Pedido();
        p.setId(1L);
        p.setClienteNombre("Maria");
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(p));

        Optional<Pedido> result = pedidoService.buscarPorId(1L);

        assertTrue(result.isPresent());
        assertEquals("Maria", result.get().getClienteNombre());
    }

    @Test
    void buscarPorId_retornaVacioCuandoNoExiste() {
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Pedido> result = pedidoService.buscarPorId(99L);

        assertFalse(result.isPresent());
    }

    @Test
    void guardar_retornaPedidoGuardado() {
        Pedido p = new Pedido();
        p.setClienteNombre("Carlos");
        when(pedidoRepository.save(p)).thenReturn(p);

        Pedido result = pedidoService.guardar(p);

        assertNotNull(result);
        assertEquals("Carlos", result.getClienteNombre());
    }

    @Test
    void cambiarEstado_actualizaEstado() {
        Pedido p = new Pedido();
        p.setId(1L);
        p.setEstado(EstadoPedido.PENDIENTE);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(p));
        when(pedidoRepository.save(p)).thenReturn(p);

        Pedido result = pedidoService.cambiarEstado(1L, EstadoPedido.DESPACHADO);

        assertEquals(EstadoPedido.DESPACHADO, result.getEstado());
    }

    @Test
    void cambiarEstado_lanzaExcepcionSiNoExiste() {
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> pedidoService.cambiarEstado(99L, EstadoPedido.CANCELADO));
    }

    @Test
    void eliminar_llamaDeleteById() {
        pedidoService.eliminar(1L);
        verify(pedidoRepository, times(1)).deleteById(1L);
    }

    @Test
    void buscarPorEstado_retornaLista() {
        Pedido p = new Pedido();
        p.setEstado(EstadoPedido.EN_PROCESO);
        when(pedidoRepository.findByEstado(EstadoPedido.EN_PROCESO)).thenReturn(List.of(p));

        List<Pedido> result = pedidoService.buscarPorEstado(EstadoPedido.EN_PROCESO);

        assertEquals(1, result.size());
        assertEquals(EstadoPedido.EN_PROCESO, result.get(0).getEstado());
    }
}