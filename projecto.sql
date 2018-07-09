# Host: localhost  (Version 5.7.17-log)
# Date: 2018-06-11 23:56:57
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "categorias"
#

DROP TABLE IF EXISTS `categorias`;
CREATE TABLE `categorias` (
  `idCategoria` tinyint(4) NOT NULL AUTO_INCREMENT,
  `nombreCategoria` varchar(45) NOT NULL,
  `idEstado` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idCategoria`,`idEstado`),
  KEY `fk_categorias_estados_idx` (`idEstado`),
  CONSTRAINT `fk_categorias_estados` FOREIGN KEY (`idEstado`) REFERENCES `estados` (`idEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Data for table "categorias"
#

INSERT INTO `categorias` VALUES (1,'BEBIDAS FRIAS',1),(2,'BDEBIDAS CALIENTES',1),(3,'ENTRADAS',1),(4,'PLATO FUERTE',1),(5,'EXTRA',1);

#
# Structure for table "datosempresa"
#

DROP TABLE IF EXISTS `datosempresa`;
CREATE TABLE `datosempresa` (
  `idEmpresa` tinyint(4) NOT NULL,
  `nombreEmpresa` varchar(100) NOT NULL,
  `nombreApp` varchar(45) NOT NULL,
  PRIMARY KEY (`idEmpresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "datosempresa"
#

INSERT INTO `datosempresa` VALUES (1,'PUPUSERIA','CafeAdmin');

#
# Structure for table "estados"
#

DROP TABLE IF EXISTS `estados`;
CREATE TABLE `estados` (
  `idEstado` tinyint(4) NOT NULL,
  `nombreEstado` varchar(45) NOT NULL,
  `tipo` tinyint(255) DEFAULT NULL,
  PRIMARY KEY (`idEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "estados"
#

INSERT INTO `estados` VALUES (1,'Activo',NULL),(2,'Inactivo',NULL),(3,'En espera',1),(4,'En proceso',1),(5,'Entregada',1);

#
# Structure for table "articulos"
#

DROP TABLE IF EXISTS `articulos`;
CREATE TABLE `articulos` (
  `idArticulo` int(11) NOT NULL AUTO_INCREMENT,
  `idCategoria` tinyint(4) NOT NULL,
  `codigo` varchar(50) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `precioVenta` decimal(10,2) DEFAULT NULL,
  `PrecioCompra` decimal(10,2) DEFAULT NULL,
  `idEstado` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idArticulo`,`idCategoria`,`idEstado`),
  KEY `fk_articulos_categorias_idx` (`idCategoria`),
  KEY `fk_articulos_estados_idx` (`idEstado`),
  CONSTRAINT `fk_articulos_categorias` FOREIGN KEY (`idCategoria`) REFERENCES `categorias` (`idCategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_articulos_estados` FOREIGN KEY (`idEstado`) REFERENCES `estados` (`idEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "articulos"
#

INSERT INTO `articulos` VALUES (1,1,'13151','HORCHATA',1.50,1.20,1),(3,2,'65464556','CAFE',0.25,0.10,1),(4,4,'654564564','PUPUSAS',0.40,0.20,1);

#
# Structure for table "roles"
#

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `idRol` tinyint(4) NOT NULL AUTO_INCREMENT,
  `nombreRol` varchar(45) NOT NULL,
  `idEstado` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idRol`,`idEstado`),
  KEY `fk_roles_estados_idx` (`idEstado`),
  CONSTRAINT `fk_roles_estados` FOREIGN KEY (`idEstado`) REFERENCES `estados` (`idEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "roles"
#

INSERT INTO `roles` VALUES (1,'Administrador',1),(2,'Cajero',1);

#
# Structure for table "tiposordenes"
#

DROP TABLE IF EXISTS `tiposordenes`;
CREATE TABLE `tiposordenes` (
  `idTipoOrden` tinyint(4) NOT NULL AUTO_INCREMENT,
  `nombreTipoOrden` varchar(100) NOT NULL,
  `idEstado` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idTipoOrden`,`idEstado`),
  KEY `fk_tiposOrdenes_estados_idx` (`idEstado`),
  CONSTRAINT `fk_tiposOrdenes_estados` FOREIGN KEY (`idEstado`) REFERENCES `estados` (`idEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "tiposordenes"
#

INSERT INTO `tiposordenes` VALUES (1,'Para llevar',1),(2,'Para comer aqui',1),(3,'Domicilio',1);

#
# Structure for table "usuarios"
#

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `contrasena` text NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `idRol` tinyint(4) NOT NULL,
  `foto` varchar(45) NOT NULL,
  `idEmpresa` tinyint(4) NOT NULL DEFAULT '1',
  `idEstado` tinyint(4) NOT NULL DEFAULT '1',
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idUsuario`,`idRol`,`idEmpresa`,`idEstado`),
  UNIQUE KEY `usuario_UNIQUE` (`usuario`),
  KEY `fk_usuarios_roles_idx` (`idRol`),
  KEY `fk_usuarios_estados_idx` (`idEstado`),
  KEY `fk_usuarios_datosEmpresa_idx` (`idEmpresa`),
  CONSTRAINT `fk_usuarios_datosEmpresa` FOREIGN KEY (`idEmpresa`) REFERENCES `datosempresa` (`idEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarios_estados` FOREIGN KEY (`idEstado`) REFERENCES `estados` (`idEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarios_roles` FOREIGN KEY (`idRol`) REFERENCES `roles` (`idRol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "usuarios"
#

INSERT INTO `usuarios` VALUES (1,'admin','1234','Administrador',1,'user.png',1,1,NULL),(2,'cajero','1234','Ventas',2,'user.png',1,1,'2018-06-05 18:01:18');

#
# Structure for table "ordenes"
#

DROP TABLE IF EXISTS `ordenes`;
CREATE TABLE `ordenes` (
  `idOrden` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `idUsuario` int(11) NOT NULL,
  `idTipoOrden` tinyint(4) NOT NULL,
  `observaciones` text,
  `idEstado` tinyint(3) NOT NULL DEFAULT '3',
  `cliente` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idOrden`,`idUsuario`,`idTipoOrden`,`idEstado`),
  KEY `fk_ordenes_usuarios_idx` (`idUsuario`),
  KEY `fk_ordenes_estados_idx` (`idEstado`),
  KEY `fk_ordenes_tipoOrdenes_idx` (`idTipoOrden`),
  CONSTRAINT `fk_ordenes_estados` FOREIGN KEY (`idEstado`) REFERENCES `estados` (`idEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordenes_tipoOrdenes` FOREIGN KEY (`idTipoOrden`) REFERENCES `tiposordenes` (`idTipoOrden`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordenes_usuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Data for table "ordenes"
#

INSERT INTO `ordenes` VALUES (1,'2018-06-05 02:32:54',2,1,'OBSERVACIONES',3,'Juan'),(2,'2018-06-05 02:58:36',1,1,'Cafe bien caliente',3,'Mario'),(3,'2018-06-05 18:15:14',1,1,'Fresco sin hielo',3,'Pedro'),(4,'2018-06-05 18:40:18',2,3,'Anda preciso',3,'Julio'),(5,'2018-06-05 19:11:43',1,2,'queso/frijol',3,'jose'),(6,'2018-06-05 19:31:59',2,2,'CAFE BIEN CALIENTE',5,'CLIENTE DEMOSTRACION PARCIAL');

#
# Structure for table "detallesordenes"
#

DROP TABLE IF EXISTS `detallesordenes`;
CREATE TABLE `detallesordenes` (
  `idDetalleOrden` int(11) NOT NULL AUTO_INCREMENT,
  `idOrden` int(11) NOT NULL,
  `idArticulo` int(11) NOT NULL,
  `cantidad` tinyint(4) NOT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `idEstado` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idDetalleOrden`,`idOrden`,`idArticulo`,`idEstado`),
  KEY `fk_detallesOrdenes_ordenes_idx` (`idOrden`),
  KEY `fk_detallesOrdenes_articulos_idx` (`idArticulo`),
  KEY `fk_detallesOrdenes_estados_idx` (`idEstado`),
  CONSTRAINT `fk_detallesOrdenes_articulos` FOREIGN KEY (`idArticulo`) REFERENCES `articulos` (`idArticulo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_detallesOrdenes_estados` FOREIGN KEY (`idEstado`) REFERENCES `estados` (`idEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_detallesOrdenes_ordenes` FOREIGN KEY (`idOrden`) REFERENCES `ordenes` (`idOrden`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

#
# Data for table "detallesordenes"
#

INSERT INTO `detallesordenes` VALUES (1,1,1,2,1.50,1),(8,2,1,5,1.50,1),(9,2,1,3,1.50,1),(10,2,1,1,1.50,1),(11,2,1,3,1.50,1),(12,2,1,9,1.50,1),(13,2,1,2,1.50,1),(14,3,1,3,1.50,1),(15,4,4,3,0.40,1),(16,4,3,1,0.25,1),(17,5,3,13,0.25,1),(18,5,1,2,1.50,1),(19,6,3,1,0.25,1),(20,6,4,3,0.40,1);
