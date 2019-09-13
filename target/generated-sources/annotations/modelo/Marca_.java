package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Marca.class)
public abstract class Marca_ {

	public static volatile SingularAttribute<Marca, String> descripcion;
	public static volatile SingularAttribute<Marca, String> marca;
	public static volatile SingularAttribute<Marca, Integer> id;
	public static volatile CollectionAttribute<Marca, Producto> productoCollection;

}

