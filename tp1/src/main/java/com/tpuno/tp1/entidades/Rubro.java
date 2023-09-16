package com.tpuno.tp1.entidades;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Rubro extends BaseEntidad {

    private String denominacion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")
    @Builder.Default //va o tira error
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto (Producto produc){
        productos.add(produc);
    }

    public void mostrarProductos() {
        System.out.println("Dento de este rubro los productos son: ");
        for (Producto producto : productos) { //por cada producto de tipo producto mostrar sus datos
            System.out.println(" Tipo: " + producto.getTipo()
                    + "Denominacion: " + producto.getDenominacion()
                    + " Unidad Medida: " + producto.getUnidadMedida()
                    + " Precio Compra: " + producto.getPrecioCompra()
                    + " Precio Venta: " + producto.getPrecioVta()
                    + " Stock Min: " + producto.getStockMin()
                    + " Stock Actual: " + producto.getStockActual()
                    + " Tiempo Estimado Cocina: " producto.getTiempoEstimadoCocina());
        }
    }
}