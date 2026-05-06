package com.smartlogix.pedidos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @PrePersist
    public void prePersist() {
        if (this.estado == null) this.estado = EstadoPedido.PENDIENTE;
        if (this.fechaPedido == null) this.fechaPedido = LocalDateTime.now();
        if (this.cantidad != null && this.precioUnitario != null) {
            this.total = this.cantidad * this.precioUnitario;
        }
    }
}