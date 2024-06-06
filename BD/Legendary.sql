create database legendary;
use legendary;

select * from usuarios;
select * from autos;
select * from autos where Categoria='Motocicleta';

create table autos(Nombre varchar(30), Precio int unsigned, Categoria varchar(30), Existencias int unsigned, primary key(Nombre), imagen varchar(200));
create table Usuarios (Nombre varchar(30), Apellidos varchar(40), Correo varchar(30), Pais varchar(30), Contrasena varchar(30),primary key(Correo));

drop table autos;
drop table usuarios;

/*INSERCIÓN DE DATOS PARA AUTOS*/
insert into autos values ('Dewbauchee Vagner',1535000,'Auto',10,'./Imagenes/Vagner.WEBP');
insert into autos values ('Grotti X80 Proto',2700000,'Auto',10,'./Imagenes/Proto.WEBP');
insert into autos values ('Rapid FMJ',1750000,'Auto',10,'./Imagenes/FMJ.WEBP');
insert into autos values ('Pegassi Zentorno',725000,'Auto',10,'./Imagenes/Zentorno.WEBP');
insert into autos values ('Overflod Autarch',1955000,'Auto',10,'./Imagenes/Autarch.WEBP');    
insert into autos values ('Devestre Eight',1795000,'Auto',10,'./Imagenes/Devestre.WEBP');
insert into autos values ('Entity XF',477000,'Auto',10,'./Imagenes/Entity.WEBP');
insert into autos values ('Pegassi Torero XO',2355000,'Auto',10,'./Imagenes/ToreroXO.WEBP');
insert into autos values ('Grotti Itali GTO',2380000,'Auto',10,'./Imagenes/ItaliGTO.WEBP');
insert into autos values ('Grotti Itali RSX',3465000,'Auto',10,'./Imagenes/ItaliRSX.WEBP');
insert into autos values ('Oceliot Pariah',1420000,'Auto',10,'./Imagenes/Pariah.WEBP');
insert into autos values ('Ocelot Virtue',2980000,'Auto',10,'./Imagenes/Virtue.WEBP');
/* INSERCIÓN DE DATOS PARA MOTOCICLETAS*/
insert into autos values ('Oppressor MK2', 8000000,'Motocicleta', 10,'./Imagenes/OppressorMk2.WEBP');
insert into autos values ('Oppresor',2750000,'Motocicleta',10,'./Imagenes/Oppressor.WEBP');
insert into autos values ('Powersurge',1605000,'Motocicleta',10,'./Imagenes/Powersurge.WEBP');
insert into autos values ('Shotaro',2225000,'Motocicleta',10,'./Imagenes/Shotaro.WEBP');
insert into autos values ('Reever',1900000,'Motocicleta',10,'./Imagenes/Reever.WEBP');
insert into autos values ('Shinobi',2480500,'Motocicleta',10,'./Imagenes/Shinobi.WEBP');
insert into autos values ('Lectro',997500,'Motocicleta',10,'./Imagenes/Lectro.WEBP');
insert into autos values ('CarbonRS',40000,'Motocicleta',10,'./Imagenes/Carbonrs.WEBP');
insert into autos values ('Vindicator',630000,'Motocicleta',10,'./Imagenes/Vindicator
.WEBP');
delete from autos where nombre="Turismo";

#Tabla de la información del coche para la ventana de compra	
create table autoCompra(NombreAuto varchar(30),
imgEstadisticas varchar(255), 
imgvisualizacion varchar(255),
Descripcion varchar(500),
Primary key(NombreAuto), 
foreign key (NombreAuto) references autos(Nombre));

#Tabla para ingresar las compras de lo usuarios
create table compras(NombreUsuario varchar(30),
NombreAuto varchar(30),
Color varchar(50),
Precio varchar(100));

#Pruebas de insercion para las compras de los usuarios
insert into compras values ('angnusa11@gmail.com','Pegassi Zentorno','Verde','7250000.00');

truncate table usuarios;
