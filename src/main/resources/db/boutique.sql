/*
SQLyog Community- MySQL GUI v8.3 Beta1
MySQL - 5.0.24-community-nt : Database - boutique
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`boutique` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `boutique`;

/*Table structure for table `cliente` */

DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `id` int(10) NOT NULL auto_increment,
  `ruc` varchar(20) default NULL,
  `email` varchar(20) default NULL,
  `nombre` varchar(20) default NULL,
  `cedula` int(20) unsigned default NULL,
  `estado` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `cliente` */

/*Table structure for table `compra` */

DROP TABLE IF EXISTS `compra`;

CREATE TABLE `compra` (
  `id` int(20) unsigned NOT NULL auto_increment,
  `usuario_id` int(10) unsigned NOT NULL,
  `nrofactura` varchar(20) default NULL,
  `fecha` date default NULL,
  `iva` double default NULL,
  `total` double default NULL,
  PRIMARY KEY  (`id`),
  KEY `compra_FKIndex1` (`usuario_id`),
  CONSTRAINT `compra_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `compra` */

/*Table structure for table `detalle_compra` */

DROP TABLE IF EXISTS `detalle_compra`;

CREATE TABLE `detalle_compra` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `compra_id` int(20) unsigned NOT NULL,
  `producto` varchar(20) default NULL,
  `cantidad` double default NULL,
  `precio` double default NULL,
  PRIMARY KEY  (`id`),
  KEY `detalle_compra_FKIndex1` (`compra_id`),
  CONSTRAINT `detalle_compra_ibfk_1` FOREIGN KEY (`compra_id`) REFERENCES `compra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `detalle_compra` */

/*Table structure for table `detalle_factura` */

DROP TABLE IF EXISTS `detalle_factura`;

CREATE TABLE `detalle_factura` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `factura_id` int(10) NOT NULL,
  `producto_id` int(10) NOT NULL,
  `descuento` double default NULL,
  `totalventa` double default NULL,
  PRIMARY KEY  (`id`),
  KEY `Detalle_FKIndex2` (`producto_id`),
  KEY `Detalle_FKIndex1` (`factura_id`),
  CONSTRAINT `detalle_factura_ibfk_1` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `detalle_factura_ibfk_2` FOREIGN KEY (`factura_id`) REFERENCES `factura` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `detalle_factura` */

/*Table structure for table `factura` */

DROP TABLE IF EXISTS `factura`;

CREATE TABLE `factura` (
  `id` int(10) NOT NULL auto_increment,
  `usuario_id` int(10) unsigned NOT NULL,
  `cliente_id` int(10) NOT NULL,
  `numero_fac` varchar(100) default NULL,
  `estado` varchar(30) default NULL,
  `fecha` date default NULL,
  `subtotal` int(10) default NULL,
  `iva` int(10) default NULL,
  `totalfactura` int(10) default NULL,
  PRIMARY KEY  (`id`),
  KEY `factura_FKIndex1` (`cliente_id`),
  KEY `factura_FKIndex2` (`usuario_id`),
  CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `factura_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `factura` */

/*Table structure for table `marca` */

DROP TABLE IF EXISTS `marca`;

CREATE TABLE `marca` (
  `id` int(10) NOT NULL auto_increment,
  `marca` varchar(30) default NULL,
  `descripcion` varchar(30) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `marca` */

/*Table structure for table `producto` */

DROP TABLE IF EXISTS `producto`;

CREATE TABLE `producto` (
  `id` int(10) NOT NULL auto_increment,
  `marca_id` int(10) NOT NULL,
  `proveedor_id` int(10) NOT NULL,
  `Nombre` varchar(30) default NULL,
  `modelo` varchar(30) default NULL,
  `cantidad` int(30) unsigned default NULL,
  `precio` int(30) unsigned default NULL,
  PRIMARY KEY  (`id`),
  KEY `producto_FKIndex1` (`marca_id`),
  KEY `producto_FKIndex2` (`proveedor_id`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`marca_id`) REFERENCES `marca` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `producto_ibfk_2` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `producto` */

/*Table structure for table `proveedor` */

DROP TABLE IF EXISTS `proveedor`;

CREATE TABLE `proveedor` (
  `id` int(10) NOT NULL auto_increment,
  `descripcion` varchar(20) default NULL,
  `ruc` varchar(20) default NULL,
  `telefono` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `proveedor` */

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `username` varchar(30) default NULL,
  `pass` varchar(30) default NULL,
  `acceso` varchar(30) default NULL,
  `fecha_ingreso` date default NULL,
  `estado` varchar(30) default NULL,
  `nombre` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `usuario` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
