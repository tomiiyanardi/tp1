package com.tpuno.tp1.repositorios;

import com.tpuno.tp1.entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository <Factura,Long> {
}