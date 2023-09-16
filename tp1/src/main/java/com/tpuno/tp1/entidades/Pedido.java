package com.tpuno.tp1.entidades;

import com.tpuno.tp1.enumeraciones.Estado;
import com.tpuno.tp1.enumeraciones.TipoEnvio;
import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Pedido extends BaseEntidad {

    private Estado estado;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private TipoEnvio tipoEnvio;
    private double total;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "factura-id")
    private Factura factura;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default //va o tira error
    private List<DetallePedido> detallesPedidos = new ArrayList<>();

    public void agregarDetallePedido(DetallePedido detallep) {
        detallesPedidos.add(detallep);
    }
}