package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DetalleFactura.class)
public abstract class DetalleFactura_ {

	public static volatile SingularAttribute<DetalleFactura, Factura> facturaId;
	public static volatile SingularAttribute<DetalleFactura, Double> descuento;
	public static volatile SingularAttribute<DetalleFactura, Producto> productoId;
	public static volatile SingularAttribute<DetalleFactura, Double> totalventa;
	public static volatile SingularAttribute<DetalleFactura, Integer> id;

}

