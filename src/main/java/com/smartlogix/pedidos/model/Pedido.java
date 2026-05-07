package com.smartlogix.pedidos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Column(name = "cliente_nombre", nullable = false)
    private String clienteNombre;

    @NotNull(message = "El producto ID es obligatorio")
    @Column(name = "producto_id", nullable = false)
    private Long productoId;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Column(name = "producto_nombre", nullable = false)
    private String productoNombre;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    @Column(nullable = false)
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(name = "total")
    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPedido estado;

    @Column(name = "fecha_pedido")
    private LocalDateTime fechaPedido;

    public Pedido() {}

    // Getters
    public Long getId() { return id; }
    public String getClienteNombre() { return clienteNombre; }
    public Long getProductoId() { return productoId; }
    public String getProductoNombre() { return productoNombre; }
    public Integer getCantidad() { return cantidad; }
    public Double getPrecioUnitario() { return precioUnitario; }
    public Double getTotal() { return total; }
    public EstadoPedido getEstado() { return estado; }
    public LocalDateTime getFechaPedido() { return fechaPedido; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }
    public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
    public void setTotal(Double total) { this.total = total; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }
    public void setFechaPedido(LocalDateTime fechaPedido) { this.fechaPedido = fechaPedido; }

    @PrePersist
    public void prePersist() {
        if (this.estado == null) this.estado = EstadoPedido.PENDIENTE;
        if (this.fechaPedido == null) this.fechaPedido = LocalDateTime.now();
        if (this.cantidad != null && this.precioUnitario != null) {
            this.total = this.cantidad * this.precioUnitario;
        }
    }
}