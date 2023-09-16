package com.tpuno.tp1;

import com.tpuno.tp1.entidades.*;
import com.tpuno.tp1.enumeraciones.Estado;
import com.tpuno.tp1.enumeraciones.FormaPago;
import com.tpuno.tp1.enumeraciones.Tipo;
import com.tpuno.tp1.enumeraciones.TipoEnvio;
import com.tpuno.tp1.repositorios.ClienteRepository;
import com.tpuno.tp1.repositorios.RubroRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.tpuno.tp1.entidades.Rubro.*;

@SpringBootApplication
public class Tp1Application {
	@Autowired
	RubroRepository rubroRepository;
	@Autowired
	ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(Tp1Application.class, args);
		System.out.println("---------FUNCIONANDO---------");
	}

	@Bean
	CommandLineRunner init(RubroRepository rubroRepository1, ClienteRepository clienteRepository1){
		return args -> {
			System.out.println("---------FUNCIONANDO-PRIMA---------");
			//primero crear una instancia de rubro, ya que debe ser el primero en crearse debido a la forma del diagrama
			Rubro rubro1 = Rubro.builder()
					.denominacion("Hamburguesas")
					.build();
			//segundo crear la instancia de productos, también debido al diagrama
			Producto producto1 = Producto.builder()
					.tiempoEstimadoCocina(30)
					.denominacion("Cangreburgger")
					.precioVta(1500)
					.precioCompra(1200)
					.stockActual(10)
					.stockMin(5)
					.unidadMedida("unidadm1")
					.receta("recetaN1")
					.tipo(Tipo.insumo)
					.build();
			Producto producto2 = Producto.builder()
					.tiempoEstimadoCocina(30)
					.denominacion("Cangreburgger completa")
					.precioVta(2400)
					.precioCompra(1500)
					.stockActual(20)
					.stockMin(4)
					.unidadMedida("unidad2")
					.receta("recetaN2")
					.tipo(Tipo.insumo)
					.build();
			//agregar productos creado al rubro creado
			rubro1.agregarProducto(producto1);
			rubro1.agregarProducto(producto2);
			//guardar rubro
			rubroRepository.save(rubro1);
			//creo un Cliente, tambien debido a la composicion del diagrama
			Cliente cliente1 = Cliente.builder()
					.nombre("Tomas")
					.apellido("Yanardi")
					.mail("tomasyanardi@mail")
					.telefono("2615351490")
					.build();
			////creo un domicilio, tambien debido a la composicion del diagrama
			Domicilio domicilio1 = Domicilio.builder()
					.calle("Figueroa")
					.numero(332)
					.localidad("Godoy Cruz")
					.build();
			Domicilio domicilio2 = Domicilio.builder()
					.calle("Pavimentada")
					.numero(666)
					.localidad("Hugarteche")
					.build();
			//agregar los domicilios creados al cliente
			cliente1.agregarDomicilio(domicilio1);
			cliente1.agregarDomicilio(domicilio2);

			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(3)
					.subtotal(4800)
					.build();
			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(2400)
					.build();
			DetallePedido detallePedido3 = DetallePedido.builder()
					.cantidad(3)
					.subtotal(5400)
					.build();

			SimpleDateFormat f1 = new SimpleDateFormat ("yyyy-MM-dd"); //configuracion fecha
			String fechaString = "2023-09-09";
			Date fecha = f1.parse(fechaString);

			Pedido pedido1 = Pedido.builder() //creo un Pedido, tambien debido a la composicion del diagrama
					.fecha(fecha)
					.total(4800)
					.estado(Estado.entregado)
					.tipoEnvio(TipoEnvio.delivery)
					.build();
			Pedido pedido2 = Pedido.builder()
					.fecha(fecha)
					.total(6000)
					.estado(Estado.iniciado)
					.tipoEnvio(TipoEnvio.retira)
					.build();

			Factura factura1 = Factura.builder()//creo una instacia de factura, tambien debido a la composicion del diagrama
					.fecha(fecha)
					.total(4800)
					.numero(1)
					.dto(0)
					.formaPago(FormaPago.tc)
					.build();
			Factura factura2 = Factura.builder()
					.fecha(fecha)
					.total(6000)
					.numero(2)
					.dto(500)
					.formaPago(FormaPago.efectivo)
					.build();
			pedido1.agregarDetallePedido(detallePedido1); 	//meter los detallesPedido al pedido
			pedido1.agregarDetallePedido(detallePedido2);
			pedido2.agregarDetallePedido(detallePedido3);

			cliente1.agregarPedido(pedido1);  //Asociar los pedidos al cliente
			cliente1.agregarPedido(pedido2);

			detallePedido1.setProducto(producto1); //Asociar el detallePedido con el producto
			detallePedido2.setProducto(producto2);
			detallePedido3.setProducto(producto1);

			pedido1.setFactura(factura1);	//Asociar factura con pedido
			pedido2.setFactura(factura2);

			clienteRepository.save(cliente1); //guardar cliente

			//recuperar objeto rubro desde la base de datos, es asi de cajon
			Rubro rubroRecuperado = RubroRepository.findById(rubro1.getId()).orElse(null);
			if (rubroRecuperado != null){ //si no es nulo (tiene datos), traerlos y mostrarlos
				System.out.println("Denominacion: " + rubroRecuperado.getDenominacion());
				rubroRecuperado.mostrarProductos();
			}
			//recuperar Cliente desde la base de Datos, igual al anterior con sus propios datos
			Cliente clienteRecuperado = clienteRepository.findById(cliente1.getId()).orElse(null);
			if (clienteRecuperado != null){   //si no es nulo (tiene datos), traerlos y mostrarlos
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Mail: " + clienteRecuperado.getMail());
				System.out.println("Telefono: " + clienteRecuperado.getTelefono());
				clienteRecuperado.mostrarDomicilios();
				clienteRecuperado.mostrarPedidos();

			}
		};
	}
}