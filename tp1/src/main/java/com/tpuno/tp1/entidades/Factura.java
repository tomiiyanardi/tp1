package com.tpuno.tp1.entidades;

import com.tpuno.tp1.enumeraciones.FormaPago;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Factura extends BaseEntidad {

    private int numero;
    private Date fecha;
    private double descuento;
    private FormaPago formaPago;
    private int total;

}
