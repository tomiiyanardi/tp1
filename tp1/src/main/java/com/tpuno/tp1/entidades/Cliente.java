package com.tpuno.tp1.entidades;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity //califija a la clase (sig instruccion) como entidad manejable x JPA motor de persistencia
@Data   //genera get set, etc
@NoArgsConstructor  //constructores simples
@AllArgsConstructor //sobrecargados
@Builder
public class Cliente extends BaseEntidad {
    private String nombre;
    private String apellido;
    private String telefono;
    private String mail;
                                            //si borro P, se borra D -- Carga temprana
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default //va o tira error
    private List<Domicilio> domicilios = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default //va o tira error
    private List<Pedido> pedidos = new ArrayList<>();

    public void agregarDomicilio(Domicilio domici){
        domicilios.add(domici);
    }
    public void agregarPedido(Pedido pedid){
        pedidos.add(pedid);
    }

    public void mostrarDomicilios() {
        System.out.println("Domicilios de " + nombre + " " + apellido + ":");
        for (Domicilio domicilio : domicilios) {  //por cada domicilio del tipo "domicilio" mostrar sus datos
            System.out.println("Calle: " + domicilio.getCalle() + ", Número: " + domicilio.getNumero());
        }
    }

    public void mostrarPedidos() {
        System.out.println("Pedidos de " + nombre + " " + apellido + ":");
        for (Pedido pedido : pedidos) {
            System.out.println("Fecha: " + pedido.getFecha() + ", Total: " + pedido.getTotal());
            int sumador = 0;
            for (DetallePedido detalle: pedido.getDetallePedidos()){
                sumador ++;
                System.out.println("Producto "+sumador+": "+detalle.getProducto().getDenominacion()+"  cantidad: "+detalle.getCantidad()+"  subtotal: "+detalle.getSubtotal());
            }
        }
    }
}

