DROP SCHEMA db;
CREATE SCHEMA IF NOT EXISTS db;
USE db;

-- CREACIÓN DE TABLAS

CREATE TABLE Usuario(
						id bigint   auto_increment not  NULL PRIMARY KEY,
						email varchar(40),
                        password varchar(10),
                        rol varchar(20),
                        nombre varchar(20));

insert into Usuario (email,password,rol, nombre) value
	("pepe@gmail.com", "pepito","ADMIN", "Pepe"),
    ("juan@gmail.com", "pepito","USER", "Juan"),
    ("julia@gmail.com", "pepito","USER", "Julia");
    

CREATE TABLE TipoPropiedad(
						id TINYINT NOT NULL PRIMARY KEY,
                        descripcion varchar(30));

CREATE TABLE favorito(
	id bigint   auto_increment not  NULL PRIMARY KEY,
    idPropiedad bigint,
    idUsuario bigint
    );

insert into  TipoPropiedad value
	(1,"Casa"),
    (2,"Departamento"),
    (3,"Duplex"),
    (4,"PH"),
    (5,"Quinta"),
    (6,"Terreno");

CREATE TABLE PROVINCIA(
						COD_PROV TINYINT NOT NULL PRIMARY KEY,
						DESCRIPCION VARCHAR(50));

CREATE TABLE LOCALIDAD(
						COD_LOC TINYINT NOT NULL PRIMARY KEY,
						DESCRIPCION VARCHAR(50),
						COD_PROV TINYINT,
						FOREIGN KEY(COD_PROV)
						REFERENCES PROVINCIA(COD_PROV));


CREATE TABLE PROPIEDAD(
		                id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
		                tipo int,
                        precio double,
                        precioMin double DEFAULT NULL,
                        precioMax double DEFAULT NULL,
                        direccion varchar(50),
                        localidad varchar(50),
                        provincia varchar(50),
                        condicion varchar(20),
                        detalle varchar(50),
                        imagenUrl varchar(100),
                        ambiente varchar(20),
                        fechaPublicada date
                        );

insert into Propiedad (tipo,precio,direccion,localidad,provincia,condicion,detalle,imagenUrl,ambiente,fechaPublicada)value
    (1, 20,"Miro 222","luzuriaga","buenos aires","venta","estrenar","monoambiente.jpg", "monoambiente", date('2020-05-27')),
    (2, 15,"arieta 100","san justo","buenos aires", "alquiler","estrenar","casa1.jpg", "dos ambientes", date('2020-05-23')),
    (3, 12,"santander 44","luzuriaga","buenos aires", "venta","refaccionar","casa4.jpg","dos ambientes", date('2020-05-21')),
    (4, 22,"almafuerte 18","san justo","buenos aires", "alquiler","buen estado","casa2.jpg","tres ambientes", date('2020-05-20')),
    (5, 36,"peron 1234","san justo","buenos aires", "venta","inversion","casa5.jpg","cuatro ambientes", date('2020-05-10')),
    (6, 50,"Miro 345","luzuriaga","buenos aires", "venta","estrenar","casa3.jpg","tres ambientes", date('2020-05-01')),
	(1, 20,"Av.Rivadavia 18000","Moron","buenos aires","venta","estrenar","monoambiente.jpg", "monoambiente", date('2020-06-01')),
    (1, 15,"Brown 345","Moron","buenos aires", "alquiler","estrenar","casa1.jpg", "dos ambientes", date('2020-06-01')),
    (2, 12,"San Juan 321","Ramos Mejia","buenos aires", "venta","refaccionar","casa4.jpg","dos ambientes", date('2020-06-01')),
    (2, 22,"Quiroga 81","Liniers","CABA", "alquiler","buen estado","casa2.jpg","tres ambientes", date('2020-06-01')),
    (6, 36,"Dorrego 765","Ciudadela","buenos aires", "venta","inversion","casa5.jpg","cuatro ambientes", date('2020-06-01')),
    (6, 50,"Peron 900","Castelar","buenos aires", "venta","estrenar","casa3.jpg","tres ambientes", date('2020-06-01'));