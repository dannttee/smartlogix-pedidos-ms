package com.smartlogix.pedidos.controller;

import com.smartlogix.pedidos.model.EstadoPedido;
import com.smartlogix.pedidos.model.Pedido;
import com.smartlogix.pedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Pedidos", description = "API de gestión de pedidos SmartLogix")
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    @Operation(summary = "Listar todos los pedidos")
    public ResponseEntity<List<Pedido>> listar() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido por ID")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear nuevo pedido")
    public ResponseEntity<Pedido> crear(@Valid @RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.guardar(pedido));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar pedido existente")
    public ResponseEntity<Pedido> actualizar(@PathVariable Long id, @Valid @RequestBody Pedido pedido) {
        try {
            return ResponseEntity.ok(pedidoService.actualizar(id, pedido));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/estado")
    @Operation(summary = "Cambiar estado del pedido")
    public ResponseEntity<Pedido> cambiarEstado(@PathVariable Long id, @RequestParam EstadoPedido estado) {
        try {
            return ResponseEntity.ok(pedidoService.cambiarEstado(id, estado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar pedido")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Filtrar pedidos por estado")
    public ResponseEntity<List<Pedido>> porEstado(@PathVariable EstadoPedido estado) {
        return ResponseEntity.ok(pedidoService.buscarPorEstado(estado));
    }

    @GetMapping("/cliente/{nombre}")
    @Operation(summary = "Buscar pedidos por nombre de cliente")
    public ResponseEntity<List<Pedido>> porCliente(@PathVariable String nombre) {
        return ResponseEntity.ok(pedidoService.buscarPorCliente(nombre));
    }
}