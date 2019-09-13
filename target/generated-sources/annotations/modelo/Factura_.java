package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Factura.class)
public abstract class Factura_ {

	public static volatile SingularAttribute<Factura, Date> fecha;
	public static volatile CollectionAttribute<Factura, DetalleFactura> detalleFacturaCollection;
	public static volatile SingularAttribute<Factura, String> estado;
	public static volatile SingularAttribute<Factura, Integer> totalfactura;
	public static volatile SingularAttribute<Factura, Cliente> clienteId;
	public static volatile SingularAttribute<Factura, Integer> iva;
	public static volatile SingularAttribute<Factura, Integer> subtotal;
	public static volatile SingularAttribute<Factura, String> numeroFac;
	public static volatile SingularAttribute<Factura, Integer> id;
	public static volatile SingularAttribute<Factura, Usuario> usuarioId;

}

