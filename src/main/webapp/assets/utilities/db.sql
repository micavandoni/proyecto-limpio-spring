DROP SCHEMA db;
CREATE SCHEMA IF NOT EXISTS db;
USE db;

-- CREACIÓN DE TABLAS

CREATE TABLE Usuario(
						id bigint   auto_increment not  NULL PRIMARY KEY,
						email varchar(40),
                        password varchar(10),
                        rol varchar(20));

insert into Usuario (email,password,rol) value
	("pepe@gmail.com", "pepito","ADMIN"),
    ("juan@gmail.com", "pepito","USER"),
    ("julia@gmail.com", "pepito","USER");
    

CREATE TABLE TipoPropiedad(
						id TINYINT NOT NULL PRIMARY KEY,
                        descripcion varchar(30));

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

    
    CREATE TABLE Propiedad(
		id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
		tipo int, 
        precio double,
        direccion varchar(50),
        localidad varchar(50),
        provincia varchar(50),
        condicion varchar(20),
        detalle varchar(50),
        imagenUrl varchar(100),
        ambiente varchar(20));
    
    insert into Propiedad (tipo,precio,direccion,localidad,provincia,condicion,detalle,imagenUrl,ambiente)value
							(1, 20,"Miro 222","luzuriaga","buenos aires","venta","estrenar","monoambiente.jpg", "monoambiente"),
							(2, 15,"arieta 100","san justo","buenos aires", "alquiler","estrenar","casa1.jpg", "dos ambientes"),
							(3, 12,"santander 44","luzuriaga","buenos aires", "venta","refaccionar","casa4.jpg","dos ambientes"),
							(4, 22,"almafuerte 18","san justo","buenos aires", "alquiler","buen estado","casa2.jpg","tres ambientes"),
							(5, 36,"peron 1234","san justo","buenos aires", "venta","inversion","casa5.jpg","cuatro ambientes"),
							(6, 50,"Miro 345","luzuriaga","buenos aires", "venta","estrenar","casa3.jpg","tres ambientes");