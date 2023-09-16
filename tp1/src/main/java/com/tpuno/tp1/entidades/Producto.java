package com.tpuno.tp1.entidades;

import com.tpuno.tp1.enumeraciones.Tipo;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Producto extends BaseEntidad {

    private Tipo tipo;
    private int tiempoEstimadoCocina;
    private String denominacion;
    private double precioventa;
    private double precioCompra;
    private int stockActual;
    private int stockMinimo;
    private String unidaMedida;
    private String receta;

}
