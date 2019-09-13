package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Producto.class)
public abstract class Producto_ {

	public static volatile CollectionAttribute<Producto, DetalleFactura> detalleFacturaCollection;
	public static volatile SingularAttribute<Producto, Integer> precio;
	public static volatile SingularAttribute<Producto, Proveedor> proveedorId;
	public static volatile SingularAttribute<Producto, Marca> marcaId;
	public static volatile SingularAttribute<Producto, Integer> id;
	public static volatile SingularAttribute<Producto, Integer> cantidad;
	public static volatile SingularAttribute<Producto, String> nombre;
	public static volatile SingularAttribute<Producto, String> modelo;

}

