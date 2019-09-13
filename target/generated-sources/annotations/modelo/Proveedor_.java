package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Proveedor.class)
public abstract class Proveedor_ {

	public static volatile SingularAttribute<Proveedor, String> descripcion;
	public static volatile SingularAttribute<Proveedor, String> ruc;
	public static volatile SingularAttribute<Proveedor, Integer> id;
	public static volatile CollectionAttribute<Proveedor, Producto> productoCollection;
	public static volatile SingularAttribute<Proveedor, String> telefono;

}

