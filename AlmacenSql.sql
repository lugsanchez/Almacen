create database inventario490;
use inventario490;

CREATE TABLE clientes (
  id INT(11) NOT NULL,
  cedula VARCHAR(45) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  apellido VARCHAR(45) NOT NULL,
  genero CHAR(1) NOT NULL,
  estado CHAR(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE productos (
  codigo INT(11) NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL,
  pCompra DOUBLE NOT NULL,
  pVenta DOUBLE NOT NULL,
  cBodega INT(11) NOT NULL,
  cMinima INT(11) NOT NULL,
  cmaxPer INT(11) NOT NULL,
  estado CHAR(1) NOT NULL,
  PRIMARY KEY (`codigo`)
  );

CREATE TABLE user(
  codigo INT(11) NOT NULL AUTO_INCREMENT,
  login VARCHAR(45) NOT NULL,
  clave VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`)
);

CREATE TABLE ventas (
  codigo INT(11) NOT NULL AUTO_INCREMENT,
  cliente INT(11) NOT NULL,
  producto INT(11) NOT NULL,
  cantidad INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  CONSTRAINT `FK_ventas_clientes`
    FOREIGN KEY (`cliente`)
    REFERENCES `inventario490`.`clientes` (`id`),
    
  CONSTRAINT `FK_ventas_productos`
    FOREIGN KEY (`producto`)
    REFERENCES `inventario490`.`productos` (`codigo`)
);


