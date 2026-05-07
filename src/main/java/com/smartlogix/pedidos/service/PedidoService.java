package com.smartlogix.pedidos.service;

import com.smartlogix.pedidos.model.EstadoPedido;
import com.smartlogix.pedidos.model.Pedido;
import com.smartlogix.pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido actualizar(Long id, Pedido pedidoActualizado) {
        Pedido existente = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
        existente.setClienteNombre(pedidoActualizado.getClienteNombre());
        existente.setProductoId(pedidoActualizado.getProductoId());
        existente.setProductoNombre(pedidoActualizado.getProductoNombre());
        existente.setCantidad(pedidoActualizado.getCantidad());
        existente.setPrecioUnitario(pedidoActualizado.getPrecioUnitario());
        existente.setTotal(pedidoActualizado.getCantidad() * pedidoActualizado.getPrecioUnitario());
        existente.setEstado(pedidoActualizado.getEstado());
        return pedidoRepository.save(existente);
    }

    public Pedido cambiarEstado(Long id, EstadoPedido nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
        pedido.setEstado(nuevoEstado);
        return pedidoRepository.save(pedido);
    }

    public void eliminar(Long id) {
        pedidoRepository.deleteById(id);
    }

    public List<Pedido> buscarPorEstado(EstadoPedido estado) {
        return pedidoRepository.findByEstado(estado);
    }

    public List<Pedido> buscarPorCliente(String clienteNombre) {
        return pedidoRepository.findByClienteNombreContainingIgnoreCase(clienteNombre);
    }
}