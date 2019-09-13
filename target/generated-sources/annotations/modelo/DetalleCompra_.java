package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DetalleCompra.class)
public abstract class DetalleCompra_ {

	public static volatile SingularAttribute<DetalleCompra, Double> precio;
	public static volatile SingularAttribute<DetalleCompra, Integer> id;
	public static volatile SingularAttribute<DetalleCompra, String> producto;
	public static volatile SingularAttribute<DetalleCompra, Double> cantidad;
	public static volatile SingularAttribute<DetalleCompra, Compra> compraId;

}

