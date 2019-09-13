package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Compra.class)
public abstract class Compra_ {

	public static volatile SingularAttribute<Compra, Date> fecha;
	public static volatile SingularAttribute<Compra, Double> total;
	public static volatile CollectionAttribute<Compra, DetalleCompra> detalleCompraCollection;
	public static volatile SingularAttribute<Compra, Double> iva;
	public static volatile SingularAttribute<Compra, String> nrofactura;
	public static volatile SingularAttribute<Compra, Integer> id;
	public static volatile SingularAttribute<Compra, Usuario> usuarioId;

}

