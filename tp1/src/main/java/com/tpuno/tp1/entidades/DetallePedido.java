package com.tpuno.tp1.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetallePedido extends BaseEntidad {

    private int cantidad;
    private double subtotal;
    @ManyToOne()
    @JoinColumn(name = "producto-id")
    private Producto producto;

}
